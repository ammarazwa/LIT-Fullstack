package com.kulkultech.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: Add @EnableFeignClients annotation
// This enables Feign client scanning

@SpringBootApplication
public class TradeServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(TradeServiceApplication.class, args);
    }
}

