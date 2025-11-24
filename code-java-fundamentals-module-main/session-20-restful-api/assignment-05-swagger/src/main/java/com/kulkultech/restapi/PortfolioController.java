package com.kulkultech.restapi;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

// TODO: Add @Tag(name = "Portfolio", description = "Portfolio management APIs")
// TODO: Add @RestController and @RequestMapping("/api/portfolios")

public class PortfolioController {
    
    private Map<Long, Portfolio> portfolios = new HashMap<>();
    private Long nextId = 1L;
    
    // TODO: Add @Operation(summary = "Get portfolio by ID", description = "Returns a portfolio by its ID")
    // TODO: Add @ApiResponse(responseCode = "200", description = "Portfolio found")
    // TODO: Add @ApiResponse(responseCode = "404", description = "Portfolio not found")
    // TODO: Add @GetMapping("/{id}")
    public Portfolio getPortfolio(
            // TODO: Add @Parameter(description = "Portfolio ID", required = true)
            @PathVariable Long id) {
        return portfolios.get(id);
    }
    
    // TODO: Add @Operation(summary = "Create new portfolio")
    // TODO: Add @ApiResponse(responseCode = "201", description = "Portfolio created")
    // TODO: Add @PostMapping
    public Portfolio createPortfolio(@RequestBody Portfolio portfolio) {
        portfolio.setId(nextId++);
        portfolios.put(portfolio.getId(), portfolio);
        return portfolio;
    }
}

