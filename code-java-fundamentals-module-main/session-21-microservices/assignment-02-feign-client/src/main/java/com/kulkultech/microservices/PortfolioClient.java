/**
 * Portfolio Feign Client - Inter-Service Communication
 * 
 * Challenge: Create a Feign client to call portfolio service
 * 
 * Your task: Complete this Feign client interface
 * 
 * Concepts covered:
 * - @FeignClient annotation
 * - Service-to-service communication
 * - Declarative REST clients
 */

package com.kulkultech.microservices;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// TODO: Add @FeignClient(name = "portfolio-service")
// This creates a Feign client for the portfolio-service

public interface PortfolioClient {
    
    // TODO: Add @GetMapping("/api/portfolios/{id}")
    // TODO: Add @PathVariable("id") Long id parameter
    // This method should call GET /api/portfolios/{id} on portfolio-service
    Portfolio getPortfolio(/* TODO: Add @PathVariable("id") Long id */);
}

