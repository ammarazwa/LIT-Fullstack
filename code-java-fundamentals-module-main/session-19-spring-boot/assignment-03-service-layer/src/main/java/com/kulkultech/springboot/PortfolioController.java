/**
 * Portfolio Controller - Uses Service Layer
 * 
 * Challenge: Inject PortfolioService using constructor injection
 */

package com.kulkultech.springboot;

import org.springframework.web.bind.annotation.*;

// TODO: Add @RestController and @RequestMapping("/api/portfolios")
@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {
    
    // TODO: Add private final PortfolioService field
    // TODO: Add constructor that takes PortfolioService as parameter
    // Hint: Spring will automatically inject the service
    // For now, this is null to allow Spring context to load
    private PortfolioService portfolioService;
    
    // TODO: Replace this with constructor injection
    // Constructor that takes PortfolioService - students should implement this
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }
    
    // TODO: Add @GetMapping("/{id}")
    public Portfolio getPortfolio(/* TODO: Add @PathVariable Long id */) {
        // TODO: Delegate to portfolioService.findById(id)
        return null;
    }
    
    // TODO: Add @PostMapping
    public Portfolio createPortfolio(/* TODO: Add @RequestBody Portfolio portfolio */) {
        // TODO: Delegate to portfolioService.save(portfolio)
        return null;
    }
    
    // TODO: Add @GetMapping("/{id}/total-value")
    public Double getTotalValue(/* TODO: Add @PathVariable Long id */) {
        // TODO: Delegate to portfolioService.calculateTotalValue(id)
        return null;
    }
}

