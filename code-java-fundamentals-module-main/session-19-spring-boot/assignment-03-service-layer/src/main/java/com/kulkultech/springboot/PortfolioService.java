/**
 * Portfolio Service - Dependency Injection and Service Layer
 * 
 * Challenge: Create a service class with business logic
 * 
 * Your task: Complete this service class and inject it into the controller
 * 
 * Concepts covered:
 * - @Service annotation
 * - Business logic separation
 * - Dependency injection
 */

package com.kulkultech.springboot;

import java.util.HashMap;
import java.util.Map;

// TODO: Add @Service annotation

public class PortfolioService {
    
    // In-memory storage
    private Map<Long, Portfolio> portfolios = new HashMap<>();
    private Long nextId = 1L;
    
    // TODO: Implement method to find portfolio by ID
    public Portfolio findById(Long id) {
        // TODO: Return portfolio from portfolios map
        return null;
    }
    
    // TODO: Implement method to save portfolio
    public Portfolio save(Portfolio portfolio) {
        // TODO: If portfolio has no ID, set it to nextId and increment
        // TODO: Store portfolio in portfolios map
        // TODO: Return saved portfolio
        return null;
    }
    
    // TODO: Implement method to calculate total value
    // This method should add a 10% bonus to the balance (business logic)
    public Double calculateTotalValue(Long id) {
        // TODO: Get portfolio by ID
        // TODO: If portfolio exists, return balance * 1.1 (10% bonus)
        // TODO: Return null if portfolio doesn't exist
        return null;
    }
}

