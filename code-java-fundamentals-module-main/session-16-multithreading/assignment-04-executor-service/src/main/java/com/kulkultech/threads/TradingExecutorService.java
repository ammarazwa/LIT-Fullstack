/**
 * Trading Executor Service - ExecutorService Pattern
 * 
 * Challenge: Use ExecutorService to manage concurrent trading operations
 * 
 * Your task: Implement methods using ExecutorService to execute trades concurrently
 * 
 * Concepts covered:
 * - ExecutorService interface
 * - Thread pools (FixedThreadPool, CachedThreadPool)
 * - Future and CompletableFuture
 * - Shutdown and await termination
 */

package com.kulkultech.threads;

import java.util.List;
import java.util.concurrent.*;

public class TradingExecutorService {
    private ExecutorService executor;
    
    public TradingExecutorService(int threadPoolSize) {
        // TODO: Initialize ExecutorService with fixed thread pool
        // Hint: Executors.newFixedThreadPool(threadPoolSize)
    }
    
    /**
     * Execute a single trade asynchronously
     * TODO: Submit trade processing to executor and return Future
     */
    public Future<Boolean> executeTradeAsync(Trade trade) {
        // TODO: Use executor.submit() to execute trade processing
        // Hint: return executor.submit(() -> processTrade(trade));
        return null;
    }
    
    /**
     * Execute multiple trades concurrently
     * TODO: Submit all trades and return list of futures
     */
    public List<Future<Boolean>> executeTradesAsync(List<Trade> trades) {
        // TODO: Submit all trades to executor
        // Hint: Use trades.stream().map(t -> executor.submit(...)).collect(...)
        return null;
    }
    
    /**
     * Process a trade (simulate)
     */
    private Boolean processTrade(Trade trade) {
        try {
            Thread.sleep(100); // Simulate processing
            System.out.println("Processed trade: " + trade.getSymbol() + " - " + trade.getQuantity() + " shares");
            return true;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    /**
     * Shutdown executor gracefully
     * TODO: Implement shutdown with await termination
     */
    public void shutdown() throws InterruptedException {
        // TODO: Shutdown executor
        // Hint: executor.shutdown();
        // TODO: Wait for termination with timeout
        // Hint: executor.awaitTermination(5, TimeUnit.SECONDS);
    }
    
    public ExecutorService getExecutor() {
        return executor;
    }
}

class Trade {
    private String symbol;
    private int quantity;
    private double price;
    
    public Trade(String symbol, int quantity, double price) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
    }
    
    public String getSymbol() { return symbol; }
    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}

