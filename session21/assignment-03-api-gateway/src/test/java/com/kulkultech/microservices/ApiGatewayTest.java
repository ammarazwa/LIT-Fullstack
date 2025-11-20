package com.kulkultech.microservices;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ApiGatewayTest {
    
    private static final boolean USE_SOLUTION = 
        Boolean.parseBoolean(System.getProperty("test.solution", "false"));
    
    @Test
    public void testGatewayApplicationExists() {
        if (USE_SOLUTION) {
            Class<?> gatewayClass = ApiGatewayApplicationSolution.class;
            assertNotNull(gatewayClass, "API Gateway application should exist");
            assertTrue(gatewayClass.isAnnotationPresent(
                org.springframework.boot.autoconfigure.SpringBootApplication.class),
                "Gateway should have @SpringBootApplication");
        } else {
            // Problem tests should fail - check if problem class has required annotation
            Class<?> gatewayClass = ApiGatewayApplication.class;
            assertNotNull(gatewayClass, "API Gateway application should exist");
            assertTrue(gatewayClass.isAnnotationPresent(
                org.springframework.boot.autoconfigure.SpringBootApplication.class),
                "Problem gateway should have @SpringBootApplication - test should fail until implementation is complete");
        }
    }
}

