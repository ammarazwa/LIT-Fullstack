package com.kulkultech.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Trading Account Entity Tests
 */
public class TradingAccountTest {
    
    private static final boolean USE_SOLUTION = 
        Boolean.parseBoolean(System.getProperty("test.solution", "false"));
    
    private static SessionFactory sessionFactory;
    private Session session;
    
    @BeforeAll
    public static void setUpSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        
        if (USE_SOLUTION) {
            configuration.addAnnotatedClass(TradingAccountSolution.class);
        } else {
            configuration.addAnnotatedClass(TradingAccount.class);
        }
        
        sessionFactory = configuration.buildSessionFactory();
    }
    
    @BeforeEach
    public void setUp() {
        session = sessionFactory.openSession();
        session.beginTransaction();
    }
    
    @AfterAll
    public static void tearDown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
    
    @SuppressWarnings("unchecked")
    private <T> T createAccount(String name, String type, BigDecimal balance) {
        if (USE_SOLUTION) {
            return (T) new TradingAccountSolution(name, type, balance);
        } else {
            return (T) new TradingAccount(name, type, balance);
        }
    }
    
    @SuppressWarnings("unchecked")
    private <T> Class<T> getAccountClass() {
        if (USE_SOLUTION) {
            return (Class<T>) TradingAccountSolution.class;
        } else {
            return (Class<T>) TradingAccount.class;
        }
    }
    
    @Test
    public void testEntityPersist() {
        Object account = createAccount("Test Account", "INDIVIDUAL", new BigDecimal("1000.00"));
        
        session.persist(account);
        session.getTransaction().commit();
        
        try {
            Long id = (Long) account.getClass().getMethod("getId").invoke(account);
            assertNotNull(id, "Entity should have an ID after persist");
        } catch (Exception e) {
            fail("Failed to get ID: " + e.getMessage());
        }
    }
    
    @Test
    public void testEntityRetrieve() {
        Object account = createAccount("Retrieve Test", "CORPORATE", new BigDecimal("5000.00"));
        
        session.persist(account);
        session.getTransaction().commit();
        Long accountId = null;
        try {
            accountId = (Long) account.getClass().getMethod("getId").invoke(account);
        } catch (Exception e) {
            fail("Failed to get ID: " + e.getMessage());
        }
        session.close();
        
        session = sessionFactory.openSession();
        Object retrieved = session.get(getAccountClass(), accountId);
        
        assertNotNull(retrieved, "Entity should be retrievable");
        try {
            String name = (String) retrieved.getClass().getMethod("getAccountName").invoke(retrieved);
            BigDecimal balance = (BigDecimal) retrieved.getClass().getMethod("getBalance").invoke(retrieved);
            assertEquals("Retrieve Test", name);
            assertEquals(new BigDecimal("5000.00"), balance);
        } catch (Exception e) {
            fail("Failed to get properties: " + e.getMessage());
        }
    }
    
    @Test
    public void testEntityUpdate() {
        Object account = createAccount("Update Test", "INDIVIDUAL", new BigDecimal("2000.00"));
        
        session.persist(account);
        session.getTransaction().commit();
        Long accountId = null;
        try {
            accountId = (Long) account.getClass().getMethod("getId").invoke(account);
        } catch (Exception e) {
            fail("Failed to get ID: " + e.getMessage());
        }
        session.close();
        
        session = sessionFactory.openSession();
        session.beginTransaction();
        
        Object toUpdate = session.get(getAccountClass(), accountId);
        try {
            toUpdate.getClass().getMethod("setBalance", BigDecimal.class).invoke(toUpdate, new BigDecimal("3000.00"));
        } catch (Exception e) {
            fail("Failed to set balance: " + e.getMessage());
        }
        
        session.getTransaction().commit();
        session.close();
        
        session = sessionFactory.openSession();
        Object updated = session.get(getAccountClass(), accountId);
        
        try {
            BigDecimal balance = (BigDecimal) updated.getClass().getMethod("getBalance").invoke(updated);
            assertEquals(new BigDecimal("3000.00"), balance);
        } catch (Exception e) {
            fail("Failed to get balance: " + e.getMessage());
        }
    }
    
    @Test
    public void testLifecycleCallbacks() {
        Object account = createAccount("Callback Test", "INDIVIDUAL", new BigDecimal("1000.00"));
        
        session.persist(account);
        session.getTransaction().commit();
        
        try {
            LocalDateTime createdAt = (LocalDateTime) account.getClass().getMethod("getCreatedAt").invoke(account);
            LocalDateTime updatedAt = (LocalDateTime) account.getClass().getMethod("getUpdatedAt").invoke(account);
            assertNotNull(createdAt, "CreatedAt should be set by @PrePersist");
            assertNotNull(updatedAt, "UpdatedAt should be set by @PrePersist");
        } catch (Exception e) {
            fail("Failed to get timestamps: " + e.getMessage());
        }
    }
}

