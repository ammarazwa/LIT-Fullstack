package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TradingDatabase {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/trading_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "trading_user";
    private static final String PASSWORD = "secure_password";

    // Method untuk mendapatkan koneksi
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    // Method untuk mengetes koneksi
    public boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn.isValid(5); // timeout 5 detik
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            return false;
        }
    }

    // Main method untuk uji coba
    public static void main(String[] args) {
        TradingDatabase db = new TradingDatabase();
        if (db.testConnection()) {
            System.out.println("Connected successfully to database!");
        } else {
            System.out.println("Failed to connect to database.");
        }
    }
}
