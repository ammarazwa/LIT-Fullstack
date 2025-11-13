/**
 * Portfolio Controller - Uses Service Layer
 * 
 * Challenge: Inject PortfolioService using constructor injection
 */


package com.kulkultech.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping("/{id}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable Long id) {
        Portfolio portfolio = portfolioService.findById(id);
        if (portfolio != null) {
            return ResponseEntity.ok(portfolio);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Portfolio> createPortfolio(@RequestBody Portfolio portfolio) {
        Portfolio createdPortfolio = portfolioService.save(portfolio);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPortfolio);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable Long id, @RequestBody Portfolio portfolio) {
        portfolio.setId(id);
        Portfolio updatedPortfolio = portfolioService.save(portfolio);
        return ResponseEntity.ok(updatedPortfolio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long id) {
        Portfolio portfolio = portfolioService.findById(id);
        if (portfolio != null) {
            portfolioService.delete(id); // Ensure this method exists in PortfolioService
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
