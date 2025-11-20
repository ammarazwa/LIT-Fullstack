package com.kulkultech.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "portfolio-service")
public interface PortfolioClientSolution {
    
    @GetMapping("/api/portfolios/{id}")
    Portfolio getPortfolio(@PathVariable("id") Long id);
}

