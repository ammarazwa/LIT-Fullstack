/**
 * Portfolio Controller - Basic Spring Boot REST Controller
 * 
 * Challenge: Create a REST controller that handles HTTP GET requests
 * 
 * Your task: Complete this class to create a working REST controller
 * 
 * Concepts covered:
 * - @RestController annotation
 * - @RequestMapping for base URL mapping
 * - @GetMapping for HTTP GET requests
 * - Spring Boot auto-configuration
 */

package com.kulkultech.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {

    @GetMapping
    public String getAllPortfolios() {
        return "Welcome to Trading Portfolio API!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Portfolio Controller!";
    }
}

