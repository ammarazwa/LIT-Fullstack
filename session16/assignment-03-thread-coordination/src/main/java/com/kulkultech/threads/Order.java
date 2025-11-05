package com.kulkultech.threads;

public class Order {
    private final String stockSymbol;  
    private final int quantity;       
    private final String action;       

    // Konstruktor dengan empat parameter
    public Order(String stockSymbol, int quantity, String action) {
        this.stockSymbol = stockSymbol; // Menyimpan simbol saham (misalnya AAPL)
        this.quantity = quantity;       // Menyimpan jumlah saham
        this.action = action;           // Menyimpan aksi: BUY atau SELL
    }

    // Getter untuk stockSymbol
    public String getSymbol() {
        return stockSymbol;
    }

    // Getter untuk quantity
    public int getQuantity() {
        return quantity;
    }

    // Getter untuk action (BUY/SELL)
    public String getAction() {
        return action;
    }

    // toString method untuk representasi yang lebih mudah dibaca
    @Override
    public String toString() {
        return "Symbol: " + stockSymbol + ", Quantity: " + quantity + ", Action: " + action;
    }
}
