package com.kulkultech.springboot;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@DataJpaTest
public class PortfolioRepositoryTest {
    
    private static final boolean USE_SOLUTION = 
        Boolean.parseBoolean(System.getProperty("test.solution", "false"));
    
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired(required = false)
    private PortfolioRepository portfolioRepository;
    
    @Autowired(required = false)
    private PortfolioRepositorySolution portfolioRepositorySolution;
    
    @Test
    public void testRepositoryExists() {
        if (USE_SOLUTION) {
            assertNotNull(portfolioRepositorySolution, "Repository should be injected");
        } else {
            // Problem tests should fail - repository interface is incomplete
            assertNull(portfolioRepository, "Problem repository should not be injectable when incomplete");
            fail("Problem repository interface should extend JpaRepository - test should fail");
        }
    }
    
    @Test
    public void testFindByName() {
        if (USE_SOLUTION && portfolioRepositorySolution != null) {
            Portfolio portfolio1 = new Portfolio("Tech Portfolio", 50000.0);
            Portfolio portfolio2 = new Portfolio("Tech Portfolio", 30000.0);
            Portfolio portfolio3 = new Portfolio("Value Portfolio", 40000.0);
            
            entityManager.persist(portfolio1);
            entityManager.persist(portfolio2);
            entityManager.persist(portfolio3);
            entityManager.flush();
            
            List<Portfolio> found = portfolioRepositorySolution.findByName("Tech Portfolio");
            assertEquals(2, found.size(), "Should find 2 portfolios with name 'Tech Portfolio'");
        } else {
            // Problem tests should fail - repository methods are not implemented
            assertNull(portfolioRepository, "Problem repository should not be injectable when incomplete");
            fail("Problem repository should extend JpaRepository with methods - test should fail");
        }
    }
    
    @Test
    public void testFindByBalanceGreaterThan() {
        if (USE_SOLUTION && portfolioRepositorySolution != null) {
            Portfolio portfolio1 = new Portfolio("Portfolio 1", 50000.0);
            Portfolio portfolio2 = new Portfolio("Portfolio 2", 30000.0);
            Portfolio portfolio3 = new Portfolio("Portfolio 3", 60000.0);
            
            entityManager.persist(portfolio1);
            entityManager.persist(portfolio2);
            entityManager.persist(portfolio3);
            entityManager.flush();
            
            List<Portfolio> found = portfolioRepositorySolution.findByBalanceGreaterThan(40000.0);
            assertEquals(2, found.size(), "Should find 2 portfolios with balance > 40000");
        } else {
            // Problem tests should fail - repository methods are not implemented
            assertNull(portfolioRepository, "Problem repository should not be injectable when incomplete");
            fail("Problem repository should extend JpaRepository with methods - test should fail");
        }
    }
    
    @Test
    public void testFindWealthyPortfolios() {
        if (USE_SOLUTION && portfolioRepositorySolution != null) {
            Portfolio portfolio1 = new Portfolio("Portfolio 1", 50000.0);
            Portfolio portfolio2 = new Portfolio("Portfolio 2", 30000.0);
            Portfolio portfolio3 = new Portfolio("Portfolio 3", 60000.0);
            
            entityManager.persist(portfolio1);
            entityManager.persist(portfolio2);
            entityManager.persist(portfolio3);
            entityManager.flush();
            
            List<Portfolio> found = portfolioRepositorySolution.findWealthyPortfolios(45000.0);
            assertEquals(2, found.size(), "Should find 2 wealthy portfolios");
        } else {
            // Problem tests should fail - repository methods are not implemented
            assertNull(portfolioRepository, "Problem repository should not be injectable when incomplete");
            fail("Problem repository should extend JpaRepository with methods - test should fail");
        }
    }
}

