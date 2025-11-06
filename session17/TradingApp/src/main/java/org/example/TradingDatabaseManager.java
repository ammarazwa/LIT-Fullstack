 package org.example;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Comprehensive Trading Database Manager
 * Manages database operations for a trading system including portfolios, stocks,
 * positions, and trades.
 */
public class TradingDatabaseManager {
    private final String dbUrl;
    private final String username;
    private final String password;

    // Constructor
    public TradingDatabaseManager(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }

    // ============================================
    // 1. DATABASE TABLE CREATION
    // ============================================

    /**
     * Creates all required database tables for the trading system
     */
    public void createTables() throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.createStatement();

            // Create portfolios table
            String createPortfolios = """
                CREATE TABLE IF NOT EXISTS portfolios (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    user_id INT NOT NULL,
                    name VARCHAR(100) NOT NULL,
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    INDEX idx_user_id (user_id)
                )
                """;
            stmt.executeUpdate(createPortfolios);
            System.out.println("✓ Portfolios table created");

            // Create stocks table
            String createStocks = """
                CREATE TABLE IF NOT EXISTS stocks (
                    symbol VARCHAR(10) PRIMARY KEY,
                    name VARCHAR(100) NOT NULL,
                    sector VARCHAR(50),
                    industry VARCHAR(100),
                    INDEX idx_sector (sector)
                )
                """;
            stmt.executeUpdate(createStocks);
            System.out.println("✓ Stocks table created");

            // Create positions table
            String createPositions = """
                CREATE TABLE IF NOT EXISTS positions (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    portfolio_id INT NOT NULL,
                    stock_symbol VARCHAR(10) NOT NULL,
                    shares INT NOT NULL CHECK (shares > 0),
                    average_price DECIMAL(10,2) NOT NULL CHECK (average_price > 0),
                    current_price DECIMAL(10,2),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                    FOREIGN KEY (portfolio_id) REFERENCES portfolios(id) ON DELETE CASCADE,
                    FOREIGN KEY (stock_symbol) REFERENCES stocks(symbol),
                    UNIQUE KEY unique_position (portfolio_id, stock_symbol),
                    INDEX idx_portfolio (portfolio_id)
                )
                """;
            stmt.executeUpdate(createPositions);
            System.out.println("✓ Positions table created");

            // Create trades table
            String createTrades = """
                CREATE TABLE IF NOT EXISTS trades (
                    id INT PRIMARY KEY AUTO_INCREMENT,
                    portfolio_id INT NOT NULL,
                    stock_symbol VARCHAR(10) NOT NULL,
                    quantity INT NOT NULL CHECK (quantity > 0),
                    price DECIMAL(10,2) NOT NULL CHECK (price > 0),
                    trade_type ENUM('BUY', 'SELL') NOT NULL,
                    trade_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    FOREIGN KEY (portfolio_id) REFERENCES portfolios(id) ON DELETE CASCADE,
                    FOREIGN KEY (stock_symbol) REFERENCES stocks(symbol),
                    INDEX idx_portfolio_date (portfolio_id, trade_date),
                    INDEX idx_symbol (stock_symbol)
                )
                """;
            stmt.executeUpdate(createTrades);
            System.out.println("✓ Trades table created");

            System.out.println("\n✓ All tables created successfully!");

        } finally {
            closeResources(conn, stmt, null);
        }
    }

    // ============================================
    // 2. CRUD OPERATIONS - PORTFOLIOS
    // ============================================

    /**
     * Creates a new portfolio with validation
     */
    public int createPortfolio(int userId, String name) throws SQLException {
        if (userId <= 0) {
            throw new IllegalArgumentException("User ID must be positive");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Portfolio name cannot be empty");
        }

        String sql = "INSERT INTO portfolios (user_id, name) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, userId);
            stmt.setString(2, name.trim());

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int portfolioId = rs.getInt(1);
                    System.out.println("✓ Portfolio created with ID: " + portfolioId);
                    return portfolioId;
                }
            }

            throw new SQLException("Failed to create portfolio");
        }
    }

    /**
     * Retrieves a portfolio by ID
     */
    public Optional<Portfolio> getPortfolio(int portfolioId) throws SQLException {
        String sql = "SELECT id, user_id, name, created_at, updated_at FROM portfolios WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, portfolioId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Portfolio portfolio = new Portfolio(
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getTimestamp("created_at").toLocalDateTime(),
                        rs.getTimestamp("updated_at").toLocalDateTime()
                );
                return Optional.of(portfolio);
            }

            return Optional.empty();
        }
    }

    /**
     * Updates portfolio name
     */
    public boolean updatePortfolioName(int portfolioId, String newName) throws SQLException {
        if (newName == null || newName.trim().isEmpty()) {
            throw new IllegalArgumentException("Portfolio name cannot be empty");
        }

        String sql = "UPDATE portfolios SET name = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newName.trim());
            stmt.setInt(2, portfolioId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Deletes a portfolio (cascades to positions and trades)
     */
    public boolean deletePortfolio(int portfolioId) throws SQLException {
        String sql = "DELETE FROM portfolios WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, portfolioId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✓ Portfolio deleted: " + portfolioId);
                return true;
            }
            return false;
        }
    }

    // ============================================
    // 3. CRUD OPERATIONS - STOCKS
    // ============================================

    /**
     * Creates a new stock entry
     */
    public boolean createStock(String symbol, String name, String sector, String industry)
            throws SQLException {
        if (symbol == null || symbol.trim().isEmpty()) {
            throw new IllegalArgumentException("Stock symbol cannot be empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Stock name cannot be empty");
        }

        String sql = "INSERT INTO stocks (symbol, name, sector, industry) VALUES (?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, symbol.toUpperCase().trim());
            stmt.setString(2, name.trim());
            stmt.setString(3, sector != null ? sector.trim() : null);
            stmt.setString(4, industry != null ? industry.trim() : null);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        }
    }

    /**
     * Retrieves stock information
     */
    public Optional<Stock> getStock(String symbol) throws SQLException {
        String sql = "SELECT symbol, name, sector, industry FROM stocks WHERE symbol = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, symbol.toUpperCase());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Stock stock = new Stock(
                        rs.getString("symbol"),
                        rs.getString("name"),
                        rs.getString("sector"),
                        rs.getString("industry")
                );
                return Optional.of(stock);
            }

            return Optional.empty();
        }
    }

    // ============================================
    // 4. TRANSACTION SUPPORT - EXECUTE TRADE
    // ============================================

    /**
     * Executes a trade with full transaction support
     * Updates position and records trade in a single transaction
     */
    public boolean executeTrade(int portfolioId, String stockSymbol, int quantity,
                                double price, TradeType tradeType) throws SQLException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }

        Connection conn = null;
        PreparedStatement checkStmt = null;
        PreparedStatement tradeStmt = null;
        PreparedStatement positionStmt = null;

        try {
            conn = getConnection();
            conn.setAutoCommit(false); // Start transaction

            // 1. Verify stock exists
            String checkSql = "SELECT symbol FROM stocks WHERE symbol = ?";
            checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setString(1, stockSymbol.toUpperCase());
            ResultSet rs = checkStmt.executeQuery();

            if (!rs.next()) {
                throw new SQLException("Stock " + stockSymbol + " does not exist");
            }

            // 2. Record the trade
            String tradeSql = "INSERT INTO trades (portfolio_id, stock_symbol, quantity, price, trade_type) " +
                    "VALUES (?, ?, ?, ?, ?)";
            tradeStmt = conn.prepareStatement(tradeSql);
            tradeStmt.setInt(1, portfolioId);
            tradeStmt.setString(2, stockSymbol.toUpperCase());
            tradeStmt.setInt(3, quantity);
            tradeStmt.setDouble(4, price);
            tradeStmt.setString(5, tradeType.name());
            tradeStmt.executeUpdate();

            // 3. Update or create position
            if (tradeType == TradeType.BUY) {
                // For BUY, add to position or create new one
                String positionSql = """
                    INSERT INTO positions (portfolio_id, stock_symbol, shares, average_price, current_price)
                    VALUES (?, ?, ?, ?, ?)
                    ON DUPLICATE KEY UPDATE
                        shares = shares + VALUES(shares),
                        average_price = ((shares * average_price) + (VALUES(shares) * VALUES(average_price))) / (shares + VALUES(shares)),
                        current_price = VALUES(current_price)
                    """;
                positionStmt = conn.prepareStatement(positionSql);
                positionStmt.setInt(1, portfolioId);
                positionStmt.setString(2, stockSymbol.toUpperCase());
                positionStmt.setInt(3, quantity);
                positionStmt.setDouble(4, price);
                positionStmt.setDouble(5, price);
            } else {
                // For SELL, reduce position
                String positionSql = """
                    UPDATE positions
                    SET shares = shares - ?, current_price = ?
                    WHERE portfolio_id = ? AND stock_symbol = ? AND shares >= ?
                    """;
                positionStmt = conn.prepareStatement(positionSql);
                positionStmt.setInt(1, quantity);
                positionStmt.setDouble(2, price);
                positionStmt.setInt(3, portfolioId);
                positionStmt.setString(4, stockSymbol.toUpperCase());
                positionStmt.setInt(5, quantity);

                int updated = positionStmt.executeUpdate();
                if (updated == 0) {
                    throw new SQLException("Insufficient shares to sell");
                }
            }

            positionStmt.executeUpdate();

            // 4. Delete position if shares reach zero
            if (tradeType == TradeType.SELL) {
                String deleteSql = "DELETE FROM positions WHERE portfolio_id = ? AND stock_symbol = ? AND shares = 0";
                try (PreparedStatement deleteStmt = conn.prepareStatement(deleteSql)) {
                    deleteStmt.setInt(1, portfolioId);
                    deleteStmt.setString(2, stockSymbol.toUpperCase());
                    deleteStmt.executeUpdate();
                }
            }

            conn.commit(); // Commit transaction
            System.out.println("✓ Trade executed successfully: " + tradeType + " " + quantity + " shares of " + stockSymbol);
            return true;

        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback on error
                    System.err.println("✗ Transaction rolled back: " + e.getMessage());
                } catch (SQLException rollbackEx) {
                    System.err.println("✗ Rollback failed: " + rollbackEx.getMessage());
                }
            }
            throw e;
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true); // Restore auto-commit
            }
            closeResources(conn, tradeStmt, null);
            closeResources(null, positionStmt, null);
            closeResources(null, checkStmt, null);
        }
    }

    // ============================================
    // 5. REPORTING QUERIES
    // ============================================

    /**
     * Gets portfolio summary with all positions
     */
    public PortfolioSummary getPortfolioSummary(int portfolioId) throws SQLException {
        String sql = """
            SELECT 
                p.stock_symbol,
                s.name as stock_name,
                p.shares,
                p.average_price,
                p.current_price,
                (p.shares * p.current_price) as current_value,
                (p.shares * (p.current_price - p.average_price)) as profit_loss,
                ((p.current_price - p.average_price) / p.average_price * 100) as return_percent
            FROM positions p
            JOIN stocks s ON p.stock_symbol = s.symbol
            WHERE p.portfolio_id = ?
            ORDER BY current_value DESC
            """;

        List<PositionSummary> positions = new ArrayList<>();
        double totalValue = 0.0;
        double totalCost = 0.0;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, portfolioId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PositionSummary pos = new PositionSummary(
                        rs.getString("stock_symbol"),
                        rs.getString("stock_name"),
                        rs.getInt("shares"),
                        rs.getDouble("average_price"),
                        rs.getDouble("current_price"),
                        rs.getDouble("current_value"),
                        rs.getDouble("profit_loss"),
                        rs.getDouble("return_percent")
                );
                positions.add(pos);
                totalValue += pos.currentValue;
                totalCost += pos.shares * pos.averagePrice;
            }
        }

        double totalProfitLoss = totalValue - totalCost;
        double totalReturnPercent = totalCost > 0 ? (totalProfitLoss / totalCost * 100) : 0;

        return new PortfolioSummary(positions, totalValue, totalProfitLoss, totalReturnPercent);
    }

    /**
     * Gets trade history with pagination
     */
    public List<TradeRecord> getTradeHistory(int portfolioId, int limit, int offset)
            throws SQLException {
        String sql = """
            SELECT 
                t.id, t.stock_symbol, s.name as stock_name,
                t.quantity, t.price, t.trade_type, t.trade_date
            FROM trades t
            JOIN stocks s ON t.stock_symbol = s.symbol
            WHERE t.portfolio_id = ?
            ORDER BY t.trade_date DESC
            LIMIT ? OFFSET ?
            """;

        List<TradeRecord> trades = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, portfolioId);
            stmt.setInt(2, limit);
            stmt.setInt(3, offset);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TradeRecord trade = new TradeRecord(
                        rs.getInt("id"),
                        rs.getString("stock_symbol"),
                        rs.getString("stock_name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price"),
                        TradeType.valueOf(rs.getString("trade_type")),
                        rs.getTimestamp("trade_date").toLocalDateTime()
                );
                trades.add(trade);
            }
        }

        return trades;
    }

    /**
     * Gets top performing stocks in portfolio
     */
    public List<PositionSummary> getTopPerformers(int portfolioId, int limit) throws SQLException {
        String sql = """
            SELECT 
                p.stock_symbol,
                s.name as stock_name,
                p.shares,
                p.average_price,
                p.current_price,
                (p.shares * p.current_price) as current_value,
                (p.shares * (p.current_price - p.average_price)) as profit_loss,
                ((p.current_price - p.average_price) / p.average_price * 100) as return_percent
            FROM positions p
            JOIN stocks s ON p.stock_symbol = s.symbol
            WHERE p.portfolio_id = ?
            ORDER BY return_percent DESC
            LIMIT ?
            """;

        List<PositionSummary> topPerformers = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, portfolioId);
            stmt.setInt(2, limit);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                PositionSummary pos = new PositionSummary(
                        rs.getString("stock_symbol"),
                        rs.getString("stock_name"),
                        rs.getInt("shares"),
                        rs.getDouble("average_price"),
                        rs.getDouble("current_price"),
                        rs.getDouble("current_value"),
                        rs.getDouble("profit_loss"),
                        rs.getDouble("return_percent")
                );
                topPerformers.add(pos);
            }
        }

        return topPerformers;
    }

    // ============================================
    // UTILITY METHODS
    // ============================================

    /**
     * Gets database connection
     */
    private Connection getConnection() throws SQLException {
        // Tambahkan parameter jika perlu: ?useSSL=false&serverTimezone=UTC
        return DriverManager.getConnection(dbUrl, username, password);
    }

    /**
     * Closes database resources safely
     */
    private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Error closing ResultSet: " + e.getMessage());
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing Statement: " + e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Error closing Connection: " + e.getMessage());
            }
        }
    }
}

// ============================================
// SUPPORTING CLASSES
// ============================================

enum TradeType {
    BUY, SELL
}

class Portfolio {
    final int id;
    final int userId;
    final String name;
    final LocalDateTime createdAt;
    final LocalDateTime updatedAt;

    Portfolio(int id, int userId, String name, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

class Stock {
    final String symbol;
    final String name;
    final String sector;
    final String industry;

    Stock(String symbol, String name, String sector, String industry) {
        this.symbol = symbol;
        this.name = name;
        this.sector = sector;
        this.industry = industry;
    }
}

class PositionSummary {
    final String symbol;
    final String stockName;
    final int shares;
    final double averagePrice;
    final double currentPrice;
    final double currentValue;
    final double profitLoss;
    final double returnPercent;

    PositionSummary(String symbol, String stockName, int shares, double averagePrice,
                    double currentPrice, double currentValue, double profitLoss, double returnPercent) {
        this.symbol = symbol;
        this.stockName = stockName;
        this.shares = shares;
        this.averagePrice = averagePrice;
        this.currentPrice = currentPrice;
        this.currentValue = currentValue;
        this.profitLoss = profitLoss;
        this.returnPercent = returnPercent;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %d shares @ $%.2f | Current: $%.2f | P/L: $%.2f (%.2f%%)",
                symbol, stockName, shares, averagePrice, currentPrice, profitLoss, returnPercent);
    }
}

class PortfolioSummary {
    final List<PositionSummary> positions;
    final double totalValue;
    final double totalProfitLoss;
    final double totalReturnPercent;

    PortfolioSummary(List<PositionSummary> positions, double totalValue,
                     double totalProfitLoss, double totalReturnPercent) {
        this.positions = positions;
        this.totalValue = totalValue;
        this.totalProfitLoss = totalProfitLoss;
        this.totalReturnPercent = totalReturnPercent;
    }

    public void printSummary() {
        System.out.println("\n=== Portfolio Summary ===");
        System.out.printf("Total Value: $%.2f%n", totalValue);
        System.out.printf("Total P/L: $%.2f (%.2f%%)%n", totalProfitLoss, totalReturnPercent);
        System.out.println("\nPositions:");
        positions.forEach(System.out::println);
    }
}

class TradeRecord {
    final int id;
    final String symbol;
    final String stockName;
    final int quantity;
    final double price;
    final TradeType tradeType;
    final LocalDateTime tradeDate;

    TradeRecord(int id, String symbol, String stockName, int quantity,
                double price, TradeType tradeType, LocalDateTime tradeDate) {
        this.id = id;
        this.symbol = symbol;
        this.stockName = stockName;
        this.quantity = quantity;
        this.price = price;
        this.tradeType = tradeType;
        this.tradeDate = tradeDate;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s: %s %d shares of %s (%s) @ $%.2f",
                id, tradeDate, tradeType, quantity, symbol, stockName, price);
    }
}

// ============================================
// USAGE EXAMPLE
// ============================================

class TradingDatabaseExample {
    public static void main(String[] args) {
        // Database connection parameters
        String dbUrl = "jdbc:mysql://localhost:3306/trading_db?useSSL=false&serverTimezone=UTC";
        String username = "trading_user";
        String password = "secure_password";

        TradingDatabaseManager manager = new TradingDatabaseManager(dbUrl, username, password);

        try {
            // 1. Create tables
            System.out.println("Creating database tables...");
            manager.createTables();

            // 2. Create sample stocks
            System.out.println("\nAdding stocks...");
            manager.createStock("AAPL", "Apple Inc.", "Technology", "Consumer Electronics");
            manager.createStock("GOOGL", "Alphabet Inc.", "Technology", "Internet Services");
            manager.createStock("MSFT", "Microsoft Corporation", "Technology", "Software");

            // 3. Create portfolio
            System.out.println("\nCreating portfolio...");
            int portfolioId = manager.createPortfolio(1, "My Investment Portfolio");

            // 4. Execute trades
            System.out.println("\nExecuting trades...");
            manager.executeTrade(portfolioId, "AAPL", 10, 150.00, TradeType.BUY);
            manager.executeTrade(portfolioId, "GOOGL", 5, 2800.00, TradeType.BUY);
            manager.executeTrade(portfolioId, "MSFT", 15, 310.00, TradeType.BUY);

            // Simulate price changes
            manager.executeTrade(portfolioId, "AAPL", 5, 155.00, TradeType.BUY);
            manager.executeTrade(portfolioId, "MSFT", 5, 320.00, TradeType.SELL);

            // 5. Get portfolio summary
            System.out.println("\nRetrieving portfolio summary...");
            PortfolioSummary summary = manager.getPortfolioSummary(portfolioId);
            summary.printSummary();

            // 6. Get trade history
            System.out.println("\n=== Trade History ===");
            List<TradeRecord> trades = manager.getTradeHistory(portfolioId, 10, 0);
            trades.forEach(System.out::println);

            // 7. Get top performers
            System.out.println("\n=== Top Performers ===");
            List<PositionSummary> topPerformers = manager.getTopPerformers(portfolioId, 3);
            topPerformers.forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.err.println("Validation error: " + e.getMessage());
        }
    }
}
