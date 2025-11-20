package com.kulkultech.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PortfolioServiceApplicationSolution {
    
    public static void main(String[] args) {
        SpringApplication.run(PortfolioServiceApplicationSolution.class, args);
    }
}

