/**
 * Portfolio Controller - SOLUTION
 */

package com.kulkultech.springboot;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioControllerSolution {
    
    private final PortfolioServiceSolution portfolioService;

    public PortfolioControllerSolution(PortfolioServiceSolution portfolioService) {
        this.portfolioService = portfolioService;
    }

    @GetMapping("/{id}")
    public Portfolio getPortfolio(@PathVariable Long id) {
        return portfolioService.findById(id);
    }

    @PostMapping
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio) {
        return portfolioService.save(portfolio);
    }

    @GetMapping("/{id}/total-value")
    public Double getTotalValue(@PathVariable Long id) {
        return portfolioService.calculateTotalValue(id);
    }
}

