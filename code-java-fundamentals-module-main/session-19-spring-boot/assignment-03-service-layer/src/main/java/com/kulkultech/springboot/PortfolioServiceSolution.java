/**
 * Portfolio Service - SOLUTION
 */

package com.kulkultech.springboot;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PortfolioServiceSolution {
    
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
}

