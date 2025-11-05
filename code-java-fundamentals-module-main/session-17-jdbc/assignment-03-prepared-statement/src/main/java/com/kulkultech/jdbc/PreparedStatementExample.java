/**
 * PreparedStatement Example - SQL Injection Prevention
 * 
 * Challenge: Use PreparedStatement to prevent SQL injection attacks
 * 
 * Your task: Replace Statement with PreparedStatement for safe parameterized queries
 * 
 * Concepts covered:
 * - PreparedStatement vs Statement
 * - Parameterized queries
 * - SQL injection prevention
 * - Setting parameters with setInt, setString, setDouble
 */

package com.kulkultech.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PreparedStatementExample {
    private final String dbUrl;
    private final String username;
    private final String password;
    
    public PreparedStatementExample(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Add trade using PreparedStatement
     * TODO: Replace Statement with PreparedStatement
     * Hint: Use conn.prepareStatement(sql) instead of conn.createStatement()
     */
    public boolean addTrade(int portfolioId, String symbol, int quantity, double price, String tradeType) {
        String sql = "INSERT INTO trades (portfolio_id, stock_symbol, quantity, price, trade_type) VALUES (?, ?, ?, ?, ?)";
        
        // TODO: Use PreparedStatement instead of Statement
        // TODO: Use setInt(1, portfolioId), setString(2, symbol), etc.
        // TODO: Use executeUpdate() instead of executeUpdate(sql)
        
        return false;
    }
    
    /**
     * Find trades by symbol using PreparedStatement
     * TODO: Use PreparedStatement with parameter
     */
    public List<Trade> findTradesBySymbol(String symbol) {
        String sql = "SELECT id, stock_symbol, quantity, price, trade_type FROM trades WHERE stock_symbol = ?";
        
        List<Trade> trades = new ArrayList<>();
        
        // TODO: Use PreparedStatement
        // TODO: Set parameter with setString(1, symbol)
        // TODO: Execute query and process ResultSet
        
        return trades;
    }
    
    /**
     * Update trade price using PreparedStatement
     * TODO: Use PreparedStatement with two parameters
     */
    public boolean updateTradePrice(int tradeId, double newPrice) {
        String sql = "UPDATE trades SET price = ? WHERE id = ?";
        
        // TODO: Use PreparedStatement
        // TODO: Set price with setDouble(1, newPrice)
        // TODO: Set tradeId with setInt(2, tradeId)
        
        return false;
    }
    
    /**
     * Test SQL injection vulnerability (should be safe with PreparedStatement)
     * This demonstrates why PreparedStatement is important
     */
    public boolean testSqlInjectionSafe(String userInput) {
        String sql = "SELECT id FROM trades WHERE stock_symbol = ?";
        
        // TODO: Use PreparedStatement - this will be safe even with malicious input
        // Example malicious input: "'; DROP TABLE trades; --"
        // With PreparedStatement, this will be treated as a literal string, not SQL
        
        return false;
    }
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, username, password);
    }
}

class Trade {
    private int id;
    private String symbol;
    private int quantity;
    private double price;
    private String tradeType;
    
    public Trade(int id, String symbol, int quantity, double price, String tradeType) {
        this.id = id;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeType = tradeType;
    }
    
    // Getters
    public int getId() { return id; }
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
    public String getTradeType() { return tradeType; }
}

