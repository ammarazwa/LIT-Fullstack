package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.DataSource;
import java.sql.*;
import java.util.Properties;

public class MySQLConnectionManager {

    // Method 1: Basic DriverManager connection
    public static Connection getBasicConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/trading_db";
        String user = "trading_user";
        String password = "secure_password";
        return DriverManager.getConnection(url, user, password);
    }

    // Method 2: Connection with MySQL-specific parameters
    public static Connection getOptimizedConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/trading_db?" +
                "useSSL=false&" +                          // SSL configuration
                "serverTimezone=UTC&" +                    // Timezone setting
                "allowPublicKeyRetrieval=true&" +          // Security setting
                "rewriteBatchedStatements=true&" +         // Batch optimization
                "cachePrepStmts=true&" +                   // Cache prepared statements
                "prepStmtCacheSize=250&" +                 // Cache size
                "prepStmtCacheSqlLimit=2048&" +            // SQL length limit
                "useServerPrepStmts=true&" +               // Server-side prepared statements
                "useLocalSessionState=true&" +             // Optimize session state
                "useLocalTransactionState=true";           // Optimize transactions

        Properties props = new Properties();
        props.setProperty("user", "trading_user");
        props.setProperty("password", "secure_password");

        return DriverManager.getConnection(url, props);
    }

    // Method 3: Using DataSource (recommended for production)
    public static DataSource createDataSource() throws SQLException {
        MysqlDataSource dataSource = new MysqlDataSource();

        // Basic connection properties
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setDatabaseName("trading_db");
        dataSource.setUser("trading_user");
        dataSource.setPassword("secure_password");

        // Performance optimizations
        dataSource.setCachePrepStmts(true);
        dataSource.setPrepStmtCacheSize(250);
        dataSource.setPrepStmtCacheSqlLimit(2048);
        dataSource.setUseServerPrepStmts(true);

        // Connection behavior
        dataSource.setAutoReconnect(true);
        dataSource.setMaxReconnects(3);
        dataSource.setInitialTimeout(10);

        return dataSource;
    }

    // Test different connection methods
    public static void main(String[] args) {
        try {
            // Test basic connection
            System.out.println("Testing basic connection...");
            try (Connection conn = getBasicConnection()) {
                System.out.println("✓ Connected to: " + conn.getCatalog());
                System.out.println("✓ MySQL version: " +
                        conn.getMetaData().getDatabaseProductVersion());
            }

            // Test optimized connection
            System.out.println("\nTesting optimized connection...");
            try (Connection conn = getOptimizedConnection()) {
                System.out.println("✓ Optimized connection successful");
                System.out.println("✓ Auto-commit: " + conn.getAutoCommit());
                System.out.println("✓ Transaction isolation: " +
                        conn.getTransactionIsolation());
            }

            // Test DataSource
            System.out.println("\nTesting DataSource connection...");
            DataSource ds = createDataSource();
            try (Connection conn = ds.getConnection()) {
                System.out.println("✓ DataSource connection successful");
            }

        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
