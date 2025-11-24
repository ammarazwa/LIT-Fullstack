package com.kulkultech.microservices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ServiceDiscoveryTest {
    
    private static final boolean USE_SOLUTION = 
        Boolean.parseBoolean(System.getProperty("test.solution", "false"));
    
    @Test
    public void testEurekaServerHasAnnotation() {
        Class<?> serverClass = USE_SOLUTION ? 
            EurekaServerApplicationSolution.class : EurekaServerApplication.class;
        
        assertTrue(serverClass.isAnnotationPresent(
            org.springframework.cloud.netflix.eureka.server.EnableEurekaServer.class),
            "Eureka Server should have @EnableEurekaServer annotation");
    }
    
    @Test
    public void testServiceHasDiscoveryClient() {
        Class<?> serviceClass = USE_SOLUTION ? 
            PortfolioServiceApplicationSolution.class : PortfolioServiceApplication.class;
        
        assertTrue(serviceClass.isAnnotationPresent(
            org.springframework.cloud.client.discovery.EnableDiscoveryClient.class),
            "Service should have @EnableDiscoveryClient annotation");
    }
}

