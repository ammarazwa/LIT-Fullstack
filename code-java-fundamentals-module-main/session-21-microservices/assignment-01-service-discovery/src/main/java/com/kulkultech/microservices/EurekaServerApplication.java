/**
 * Eureka Server Application - Service Discovery
 * 
 * Challenge: Set up Eureka Server for service discovery
 * 
 * Your task: Complete this class to enable Eureka Server
 * 
 * Concepts covered:
 * - @EnableEurekaServer annotation
 * - Service discovery server setup
 */

package com.kulkultech.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Add @EnableEurekaServer annotation
// This enables Eureka server functionality

@SpringBootApplication
public class EurekaServerApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}

