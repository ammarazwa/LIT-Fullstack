package com.kulkultech.jdbc;

public class Trade {
    private int id;
    private String stockSymbol;
    private int quantity;
    private double price;
    private String tradeType;

    // Constructor
    public Trade(int id, String stockSymbol, int quantity, double price, String tradeType) {
        this.id = id;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.tradeType = tradeType;
    }

    // Getter & Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return stockSymbol;
    }

    public void setSymbol(String stockSymbol) {
        this.stockSymbol = stockSymbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", stockSymbol='" + stockSymbol + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", tradeType='" + tradeType + '\'' +
                '}';
    }
}

