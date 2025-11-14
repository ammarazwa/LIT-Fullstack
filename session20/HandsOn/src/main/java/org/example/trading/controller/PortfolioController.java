package org.example.trading.controller;

import org.example.trading.model.Portfolio;
import org.example.trading.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping
    public ResponseEntity<?> getAllPortfolios(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(portfolioService.getAllPortfolios(page, size));
    }

    @GetMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable Long portfolioId) {
        return ResponseEntity.ok(portfolioService.getPortfolioById(portfolioId));
    }

    @PostMapping
    public ResponseEntity<Portfolio> createPortfolio(@Valid @RequestBody Portfolio portfolio) {
        Portfolio createdPortfolio = portfolioService.createPortfolio(portfolio);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/api/v1/portfolios/" + createdPortfolio.getId())
                .body(createdPortfolio);
    }

    @PutMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> updatePortfolio(@PathVariable Long portfolioId,
                                                     @Valid @RequestBody Portfolio portfolio) {
        return ResponseEntity.ok(portfolioService.updatePortfolio(portfolioId, portfolio));
    }

    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long portfolioId) {
        portfolioService.deletePortfolio(portfolioId);
        return ResponseEntity.noContent().build();
    }
}
