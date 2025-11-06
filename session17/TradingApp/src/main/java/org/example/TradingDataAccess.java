package org.example;

import java.sql.*;
import java.util.*;

public class TradingDataAccess {
    private final TradingDatabase db;

    public TradingDataAccess(TradingDatabase db) {
        this.db = db;
    }

    // Create — Add new trade
    public boolean addTrade(int portfolioId, String symbol, int quantity,
                            double price, String tradeType) {
        // Optional: validasi sederhana untuk enum di DB ('BUY','SELL')
        String tt = tradeType == null ? "" : tradeType.trim().toUpperCase(Locale.ROOT);
        if (!tt.equals("BUY") && !tt.equals("SELL")) {
            System.err.println("Error adding trade: tradeType must be BUY or SELL");
            return false;
        }

        String sql = "INSERT INTO trades (portfolio_id, stock_symbol, quantity, price, trade_type) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, portfolioId);
            stmt.setString(2, symbol);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);
            stmt.setString(5, tt);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error adding trade: " + e.getMessage());
            return false;
        }
    }

    // Read — Get portfolio positions
    public List<Position> getPortfolioPositions(int portfolioId) {
        String sql = "SELECT p.stock_symbol, p.shares, p.average_price, p.current_price " +
                "FROM positions p WHERE p.portfolio_id = ?";

        List<Position> positions = new ArrayList<>();

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, portfolioId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Position position = new Position(
                            rs.getString("stock_symbol"),
                            rs.getInt("shares"),
                            rs.getDouble("average_price"),
                            rs.getDouble("current_price")
                    );
                    positions.add(position);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving positions: " + e.getMessage());
        }

        return positions;
    }

    // Update — Update stock prices in batch (transactional)
    public boolean updateStockPrices(Map<String, Double> priceUpdates) {
        if (priceUpdates == null || priceUpdates.isEmpty()) return true; // nothing to do

        String sql = "UPDATE positions " +
                "SET current_price = ?, updated_at = CURRENT_TIMESTAMP " +
                "WHERE stock_symbol = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            boolean oldAutoCommit = conn.getAutoCommit();
            conn.setAutoCommit(false);

            for (Map.Entry<String, Double> entry : priceUpdates.entrySet()) {
                stmt.setDouble(1, entry.getValue());
                stmt.setString(2, entry.getKey());
                stmt.addBatch();
            }

            int[] results = stmt.executeBatch();
            conn.commit();
            conn.setAutoCommit(oldAutoCommit);

            // SUCCESS_NO_INFO can be -2 depending on driver; treat as success
            for (int r : results) {
                if (r == 0) return false; // some row not updated (symbol tidak ada)
            }
            return true;

        } catch (SQLException e) {
            System.err.println("Error updating prices: " + e.getMessage());
            // Attempt rollback
            try (Connection conn2 = db.getConnection()) {
                if (!conn2.getAutoCommit()) conn2.rollback();
            } catch (Exception ignore) {}
            return false;
        }
    }

    // Delete — Remove position
    public boolean removePosition(int portfolioId, String symbol) {
        String sql = "DELETE FROM positions WHERE portfolio_id = ? AND stock_symbol = ?";

        try (Connection conn = db.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, portfolioId);
            stmt.setString(2, symbol);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error removing position: " + e.getMessage());
            return false;
        }
    }

    // --- Optional helper: hitung total nilai portofolio (berdasarkan positions) ---
    public double getPortfolioMarketValue(int portfolioId) {
        String sql = "SELECT SUM(shares * IFNULL(current_price, average_price)) AS mv " +
                "FROM positions WHERE portfolio_id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, portfolioId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return rs.getDouble("mv");
            }
        } catch (SQLException e) {
            System.err.println("Error computing market value: " + e.getMessage());
        }
        return 0.0;
    }

    // --- Data class ---
    public static class Position {
        private final String symbol;
        private final int shares;
        private final double averagePrice;
        private final double currentPrice;

        public Position(String symbol, int shares, double averagePrice, double currentPrice) {
            this.symbol = symbol;
            this.shares = shares;
            this.averagePrice = averagePrice;
            this.currentPrice = currentPrice;
        }

        public String getSymbol() { return symbol; }
        public int getShares() { return shares; }
        public double getAveragePrice() { return averagePrice; }
        public double getCurrentPrice() { return currentPrice; }

        public double getCurrentValue() {
            return shares * currentPrice;
        }

        public double getProfitLoss() {
            return shares * (currentPrice - averagePrice);
        }
    }
}
