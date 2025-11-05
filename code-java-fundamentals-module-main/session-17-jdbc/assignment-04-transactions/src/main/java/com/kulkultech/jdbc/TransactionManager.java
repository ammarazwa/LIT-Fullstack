/**
 * Transaction Manager - JDBC Transaction Management
 * 
 * Challenge: Implement transaction management with commit and rollback
 * 
 * Your task: Use Connection transaction methods to ensure ACID properties
 * 
 * Concepts covered:
 * - setAutoCommit(false)
 * - commit()
 * - rollback()
 * - Savepoints
 * - Transaction isolation levels
 */

package com.kulkultech.jdbc;

import java.sql.*;

public class TransactionManager {
    private final String dbUrl;
    private final String username;
    private final String password;
    
    public TransactionManager(String dbUrl, String username, String password) {
        this.dbUrl = dbUrl;
        this.username = username;
        this.password = password;
    }
    
    /**
     * Transfer funds between accounts atomically
     * TODO: Implement transaction with commit/rollback
     */
    public boolean transferFunds(int fromAccountId, int toAccountId, double amount) {
        // TODO: Get connection
        // TODO: Set autoCommit to false
        // TODO: Execute UPDATE for fromAccount (subtract amount)
        // TODO: Execute UPDATE for toAccount (add amount)
        // TODO: Commit transaction
        // TODO: Handle exceptions with rollback
        
        return false;
    }
    
    /**
     * Batch update with transaction
     * TODO: Update multiple prices atomically
     */
    public boolean updateMultiplePrices(java.util.Map<String, Double> priceUpdates) {
        String sql = "UPDATE positions SET current_price = ? WHERE stock_symbol = ?";
        
        // TODO: Get connection
        // TODO: Set autoCommit to false
        // TODO: Use PreparedStatement with batch updates
        // TODO: Execute batch
        // TODO: Commit if all succeed, rollback on error
        
        return false;
    }
    
    /**
     * Transaction with savepoint
     * TODO: Use savepoint for partial rollback
     */
    public boolean processTradeWithSavepoint(int tradeId, double newPrice) {
        // TODO: Get connection
        // TODO: Set autoCommit to false
        // TODO: Create savepoint
        // TODO: Update trade price
        // TODO: If validation fails, rollback to savepoint
        // TODO: Otherwise commit
        
        return false;
    }
    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbUrl, username, password);
    }
}

