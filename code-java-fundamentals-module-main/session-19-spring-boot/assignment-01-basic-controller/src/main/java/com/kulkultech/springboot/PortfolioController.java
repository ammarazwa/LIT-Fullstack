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

// TODO: Add necessary Spring annotations
// Hint: Use @RestController and @RequestMapping("/api/portfolios")

public class PortfolioController {
    
    // TODO: Add @GetMapping annotation to map GET requests
    // Hint: This method should handle GET /api/portfolios
    public String getAllPortfolios() {
        // TODO: Return a welcome message
        // Example: "Welcome to Trading Portfolio API!"
        return null;
    }
    
    // TODO: Add @GetMapping("/hello") to map GET /api/portfolios/hello
    public String hello() {
        // TODO: Return a hello message
        // Example: "Hello from Portfolio Controller!"
        return null;
    }
}

