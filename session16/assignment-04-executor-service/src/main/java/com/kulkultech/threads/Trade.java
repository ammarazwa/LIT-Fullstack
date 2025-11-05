package com.kulkultech.threads;

public class Trade {
    private final String symbol;  
    private final int quantity;   
    private final double price;  

    // Konstruktor untuk inisialisasi Trade
    public Trade(String symbol, int quantity, double price) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
    }

    // Getter untuk simbol saham
    public String getSymbol() {
        return symbol;
    }

    // Getter untuk jumlah saham
    public int getQuantity() {
        return quantity;
    }

    // Getter untuk harga saham
    public double getPrice() {
        return price;
    }

    // Representasi string dari transaksi perdagangan
    @Override
    public String toString() {
        return "Trade{" +
               "symbol='" + symbol + '\'' +
               ", quantity=" + quantity +
               ", price=" + price +
               '}';
    }
}
