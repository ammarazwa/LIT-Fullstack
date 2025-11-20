package com.kulkultech.microservices;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;

@Service
public class TradeServiceSolution {
    
    @CircuitBreaker(name = "portfolioService", fallbackMethod = "getPortfolioFallback")
    public Portfolio getPortfolio(Long id) {
        // Simulate calling portfolio service
        throw new RuntimeException("Portfolio service unavailable");
    }
    
    private Portfolio getPortfolioFallback(Long id, Exception ex) {
        Portfolio fallback = new Portfolio();
        fallback.setId(id);
        fallback.setName("Portfolio Unavailable");
        fallback.setBalance(0.0);
        return fallback;
    }
}

