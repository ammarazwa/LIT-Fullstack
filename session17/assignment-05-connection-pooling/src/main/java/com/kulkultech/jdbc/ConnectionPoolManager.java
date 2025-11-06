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
    
    public void createConnectionPool(String dbUrl, String username, String password, int maxPoolSize) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(dbUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setMaximumPoolSize(maxPoolSize);
        config.setMinimumIdle(2);
        config.setConnectionTimeout(30000); // 30 seconds
        config.setIdleTimeout(600000); // 10 minutes
        config.setMaxLifetime(1800000); // 30 minutes
        
        this.dataSource = new HikariDataSource(config);
    }
    
    public Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("Connection pool not initialized");
        }
        return dataSource.getConnection();
    }
    
    public DataSource getDataSource() {
        return dataSource;
    }
    
    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
    
    public String getPoolStats() {
        if (dataSource == null || dataSource.isClosed()) {
            return "Pool not initialized or closed";
        }
        
        try {
            return "Pool stats: " + dataSource.getHikariPoolMXBean().getActiveConnections() + 
                   " active, " + dataSource.getHikariPoolMXBean().getIdleConnections() + " idle, " +
                   "total: " + dataSource.getHikariPoolMXBean().getTotalConnections();
        } catch (Exception e) {
            return "Error getting pool stats: " + e.getMessage();
        }
    }
}

