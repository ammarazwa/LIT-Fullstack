package com.kulkultech.restapi;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {
    
    private Map<Long, PortfolioResponse> portfolios = new HashMap<>();
    private Long nextId = 1L;
    
    // TODO: Add @PostMapping
    // TODO: Add @Valid annotation before @RequestBody
    // TODO: Change parameter type to PortfolioRequest
    // TODO: Change return type to PortfolioResponse
    public PortfolioResponse createPortfolio(/* TODO: Add @Valid @RequestBody PortfolioRequest request */) {
        // TODO: Convert PortfolioRequest to PortfolioResponse
        // TODO: Set ID, save, and return
        return null;
    }
}

