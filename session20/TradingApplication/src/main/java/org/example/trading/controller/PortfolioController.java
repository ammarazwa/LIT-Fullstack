package org.example.trading.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.trading.api.service.PortfolioService;
import com.trading.api.model.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/portfolios")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping
    public ResponseEntity<PagedResponse<Portfolio>> getAllPortfolios(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        PagedResponse<Portfolio> portfolios = portfolioService.getPortfolios(page, size, sortBy);
        return ResponseEntity.ok(portfolios);
    }

    @GetMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> getPortfolio(@PathVariable Long portfolioId) {
        Portfolio portfolio = portfolioService.findById(portfolioId);
        return ResponseEntity.ok(portfolio);
    }

    @PostMapping
    public ResponseEntity<Portfolio> createPortfolio(@Valid @RequestBody CreatePortfolioRequest request) {
        Portfolio portfolio = portfolioService.createPortfolio(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/api/v1/portfolios/" + portfolio.getId())
                .body(portfolio);
    }

    @PutMapping("/{portfolioId}")
    public ResponseEntity<Portfolio> updatePortfolio(
            @PathVariable Long portfolioId, @Valid @RequestBody Portfolio portfolio) {
        Portfolio updated = portfolioService.replacePortfolio(portfolioId, portfolio);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{portfolioId}")
    public ResponseEntity<Void> deletePortfolio(@PathVariable Long portfolioId) {
        portfolioService.deletePortfolio(portfolioId);
        return ResponseEntity.noContent().build();
    }
}
