/**
 * Portfolio Service Application - Microservice Client
 * 
 * Challenge: Register this microservice with Eureka
 * 
 * Your task: Complete this class to enable service discovery client
 * 
 * Concepts covered:
 * - @EnableDiscoveryClient annotation
 * - Service registration with Eureka
 */

package com.kulkultech.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Add @EnableDiscoveryClient annotation
// This registers the service with Eureka

@SpringBootApplication
public class PortfolioServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(PortfolioServiceApplication.class, args);
    }
}

