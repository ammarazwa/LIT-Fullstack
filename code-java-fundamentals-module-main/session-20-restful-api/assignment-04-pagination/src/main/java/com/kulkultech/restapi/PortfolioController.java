package com.kulkultech.restapi;

import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

// TODO: Add @RestController and @RequestMapping("/api/portfolios")

public class PortfolioController {
    
    // TODO: Inject PortfolioRepository using constructor injection
    
    // TODO: Add @GetMapping
    // TODO: Add @RequestParam parameters with defaults:
    //   - int page (defaultValue = "0")
    //   - int size (defaultValue = "10")
    //   - String sortBy (defaultValue = "id")
    // TODO: Return type should be Page<Portfolio>
    public Page<Portfolio> getPortfolios(/* TODO: Add parameters */) {
        // TODO: Create Pageable using PageRequest.of(page, size, Sort.by(sortBy))
        // TODO: Call repository.findAll(pageable)
        // TODO: Return Page result
        return null;
    }
}

