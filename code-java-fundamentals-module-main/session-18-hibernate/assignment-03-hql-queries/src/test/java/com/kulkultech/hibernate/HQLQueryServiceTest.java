package com.kulkultech.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

/**
 * HQL Query Service Tests
 */
public class HQLQueryServiceTest {
    
    private static final boolean USE_SOLUTION = 
        Boolean.parseBoolean(System.getProperty("test.solution", "false"));
    
    private static SessionFactory sessionFactory;
    private Session session;
    private HQLQueryService service;
    
    @BeforeAll
    public static void setUpSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        // Register TradingAccount stub entity for HQL testing
        configuration.addAnnotatedClass(TradingAccountStub.class);
        
        sessionFactory = configuration.buildSessionFactory();
    }
    
    @BeforeEach
    public void setUp() {
        session = sessionFactory.openSession();
        if (USE_SOLUTION) {
            service = new HQLQueryServiceWrapper(true);
        } else {
            service = new HQLQueryServiceWrapper(false);
        }
    }
    
    private static class HQLQueryServiceWrapper extends HQLQueryService {
        private final HQLQueryServiceSolution solution;
        private final boolean useSolution;
        
        public HQLQueryServiceWrapper(boolean useSolution) {
            this.useSolution = useSolution;
            this.solution = useSolution ? new HQLQueryServiceSolution() : null;
        }
        
        @Override
        public List<?> findAllAccounts(Session session) {
            if (useSolution && solution != null) {
                return solution.findAllAccounts(session);
            } else {
                return super.findAllAccounts(session);
            }
        }
        
        @Override
        public List<?> findAccountsByType(Session session, String accountType) {
            if (useSolution && solution != null) {
                return solution.findAccountsByType(session, accountType);
            } else {
                return super.findAccountsByType(session, accountType);
            }
        }
        
        @Override
        public Long countAccounts(Session session) {
            if (useSolution && solution != null) {
                return solution.countAccounts(session);
            } else {
                return super.countAccounts(session);
            }
        }
    }
    
    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
    
    @Test
    public void testFindAllAccounts() {
        List<?> accounts = service.findAllAccounts(session);
        assertNotNull(accounts, "Should return list of accounts");
    }
    
    @Test
    public void testFindAccountsByType() {
        List<?> accounts = service.findAccountsByType(session, "INDIVIDUAL");
        assertNotNull(accounts, "Should return filtered accounts");
    }
    
    @Test
    public void testCountAccounts() {
        Long count = service.countAccounts(session);
        assertNotNull(count, "Should return count");
        assertTrue(count >= 0, "Count should be non-negative");
    }
}

