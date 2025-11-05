/**
 * Connection Pool Manager - Connection Pooling with HikariCP
 * 
 * Challenge: Implement connection pooling for efficient database connections
 * 
 * Your task: Use HikariCP to create and manage a connection pool
 * 
 * Concepts covered:
 * - HikariDataSource
 * - Connection pool configuration
 * - Pool size management
 * - Connection lifecycle
 */

package com.kulkultech.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPoolManager {
    private HikariDataSource dataSource;
    
    /**
     * Create connection pool with HikariCP
     * TODO: Configure HikariDataSource
     */
    public void createConnectionPool(String dbUrl, String username, String password, int maxPoolSize) {
        // TODO: Create HikariConfig
        // TODO: Set JDBC URL, username, password
        // TODO: Set maximum pool size
        // TODO: Set minimum idle connections
        // TODO: Set connection timeout
        // TODO: Create HikariDataSource from config
    }
    
    /**
     * Get connection from pool
     * TODO: Return connection from DataSource
     */
    public Connection getConnection() throws SQLException {
        // TODO: Return connection from dataSource.getConnection()
        return null;
    }
    
    /**
     * Get DataSource
     */
    public DataSource getDataSource() {
        return dataSource;
    }
    
    /**
     * Close connection pool
     * TODO: Close the DataSource
     */
    public void closePool() {
        // TODO: Call dataSource.close()
    }
    
    /**
     * Get pool statistics
     */
    public String getPoolStats() {
        if (dataSource == null) {
            return "Pool not initialized";
        }
        
        // TODO: Get and return pool statistics
        // Hint: Use dataSource.getHikariPoolMXBean() or dataSource.getHikariConfig()
        return "Pool stats: " + dataSource.getHikariPoolMXBean().getActiveConnections() + 
               " active, " + dataSource.getHikariPoolMXBean().getIdleConnections() + " idle";
    }
}

