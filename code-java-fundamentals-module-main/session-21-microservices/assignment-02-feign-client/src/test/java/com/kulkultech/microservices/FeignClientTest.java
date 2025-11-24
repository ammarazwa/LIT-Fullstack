package com.kulkultech.microservices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class FeignClientTest {
    
    private static final boolean USE_SOLUTION = 
        Boolean.parseBoolean(System.getProperty("test.solution", "false"));
    
    @Test
    public void testFeignClientHasAnnotation() {
        Class<?> clientClass = USE_SOLUTION ? 
            PortfolioClientSolution.class : PortfolioClient.class;
        
        assertTrue(clientClass.isAnnotationPresent(
            org.springframework.cloud.openfeign.FeignClient.class),
            "Feign client should have @FeignClient annotation");
    }
    
    @Test
    public void testApplicationHasEnableFeignClients() {
        Class<?> appClass = USE_SOLUTION ? 
            TradeServiceApplicationSolution.class : TradeServiceApplication.class;
        
        assertTrue(appClass.isAnnotationPresent(
            org.springframework.cloud.openfeign.EnableFeignClients.class),
            "Application should have @EnableFeignClients annotation");
    }
}

