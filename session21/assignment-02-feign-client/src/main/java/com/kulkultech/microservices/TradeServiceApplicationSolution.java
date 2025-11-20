package com.kulkultech.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class TradeServiceApplicationSolution {
    
    public static void main(String[] args) {
        SpringApplication.run(TradeServiceApplicationSolution.class, args);
    }
}

