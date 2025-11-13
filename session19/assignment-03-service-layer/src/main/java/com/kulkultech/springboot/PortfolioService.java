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

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class PortfolioService {

    private Map<Long, Portfolio> portfolios = new HashMap<>();
    private Long nextId = 1L;

    public Portfolio findById(Long id) {
        return portfolios.get(id);
    }

    public Portfolio save(Portfolio portfolio) {
        if (portfolio.getId() == null) {
            portfolio.setId(nextId++);
        }
        portfolios.put(portfolio.getId(), portfolio);
        return portfolio;
    }

    public Double calculateTotalValue(Long id) {
        Portfolio portfolio = portfolios.get(id);
        if (portfolio != null) {
            return portfolio.getBalance() * 1.1; // 10% bonus
        }
        return null;
    }

    // Optionally add delete method
    public void delete(Long id) {
        portfolios.remove(id);
    }
}
