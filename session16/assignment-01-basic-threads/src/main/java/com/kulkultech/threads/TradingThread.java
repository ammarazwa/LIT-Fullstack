/**
 * Trading Thread - Basic Thread Creation
 * 
 * Challenge: Create a thread that processes trading operations
 * 
 * Your task: Complete this class to extend Thread and implement the run() method
 * to make the tests pass.
 * 
 * Concepts covered:
 * - Extending Thread class
 * - Implementing run() method
 * - Thread lifecycle (start, run, termination)
 * - Thread.sleep() for simulation
 */

package com.kulkultech.threads;

public class TradingThread extends Thread {
    protected String stockSymbol;
    protected double price;
    protected boolean processed = false;
    
    public TradingThread(String stockSymbol, double price) {
        super("TradingThread-" + stockSymbol); 
        this.stockSymbol = stockSymbol;
        this.price = price;
    }
    
    protected TradingThread(String name) {
        super(name);
    }
    
    @Override
    public void run() {
                System.out.println("Processing trade for " + stockSymbol + " at $" + price);
        
        try {
            Thread.sleep(100); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        
        processed = true;
        System.out.println("Trade completed for " + stockSymbol);
    }
    
    public boolean isProcessed() {
        return processed;
    }
    
    public String getStockSymbol() {
        return stockSymbol;
    }
    
    public double getPrice() {
        return price;
    }
}
