/**
 * Database Connection - JDBC Connection Management
 * 
 * Challenge: Create and manage database connections using JDBC
 * 
 * Your task: Implement methods to establish and test database connections
 * 
 * Concepts covered:
 * - DriverManager.getConnection()
 * - Connection properties
 * - Connection validation
 * - Try-with-resources for resource management
 */

package com.kulkultech.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String dbUrl;
    private String username;
    private String password;
    
    public DatabaseConnection(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }
    
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, username, password);
    }
    
    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn.isValid(5);
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return false;
        }
    }
    
    public String getDatabaseProductName() throws SQLException {
        try (Connection conn = getConnection()) {
            return conn.getMetaData().getDatabaseProductName();
        }
    }
}

