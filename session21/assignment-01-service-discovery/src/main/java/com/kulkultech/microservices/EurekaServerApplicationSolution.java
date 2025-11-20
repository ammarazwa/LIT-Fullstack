package com.kulkultech.microservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplicationSolution {
    
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplicationSolution.class, args);
    }
}

