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

@FeignClient(name = "portfolio-service")
public interface PortfolioClient {

    @GetMapping("/api/portfolios/{id}")
    Portfolio getPortfolio(@PathVariable("id") Long id);
}
