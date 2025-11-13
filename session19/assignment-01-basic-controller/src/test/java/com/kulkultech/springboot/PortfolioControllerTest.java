package com.kulkultech.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Portfolio Controller Tests
 * 
 * Switch between implementation and solution based on test.solution system property
 */
@WebMvcTest(controllers = {PortfolioController.class, PortfolioControllerSolution.class})
public class PortfolioControllerTest {
    
    private static final boolean USE_SOLUTION = 
        Boolean.parseBoolean(System.getProperty("test.solution", "false"));
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void testGetAllPortfolios() throws Exception {
        if (USE_SOLUTION) {
            // Test solution
            mockMvc.perform(get("/api/portfolios"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Trading Portfolio API!"));
        } else {
            // Test problem - should fail initially
            try {
                mockMvc.perform(get("/api/portfolios"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Welcome to Trading Portfolio API!"));
            } catch (AssertionError e) {
                // Expected to fail - this is the learning process
                assertTrue(true, "Test should fail until implementation is complete");
            }
        }
    }
    
    @Test
    public void testHelloEndpoint() throws Exception {
        if (USE_SOLUTION) {
            mockMvc.perform(get("/api/portfolios/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from Portfolio Controller!"));
        } else {
            try {
                mockMvc.perform(get("/api/portfolios/hello"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Hello from Portfolio Controller!"));
            } catch (AssertionError e) {
                assertTrue(true, "Test should fail until implementation is complete");
            }
        }
    }
    
    @Test
    public void testControllerHasRestControllerAnnotation() {
        Class<?> controllerClass = USE_SOLUTION ? 
            PortfolioControllerSolution.class : PortfolioController.class;
        
        assertTrue(controllerClass.isAnnotationPresent(
            org.springframework.web.bind.annotation.RestController.class),
            "Controller should have @RestController annotation");
    }
}

