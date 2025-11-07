/**
 * Performance Optimization - Hibernate Performance
 * 
 * Challenge: Optimize Hibernate queries for better performance
 * 
 * TODO: Implement performance optimizations
 */

package com.kulkultech.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class PerformanceOptimization {

    public void batchInsert(Session session, List<?> entities) {
        session.setJdbcBatchSize(20);
        for (Object entity : entities) {
            session.persist(entity);
        }
    }

    public List<?> findAllWithTrades(Session session) {
        Query<?> query = session.createQuery(
                "SELECT p FROM PortfolioStub p JOIN FETCH p.trades",
                Object.class
        );
        return query.getResultList();
    }

    public List<Object[]> findAccountNamesOnly(Session session) {
        Query<Object[]> query = session.createQuery(
                "SELECT id, accountName FROM TradingAccountStub",
                Object[].class
        );
        return query.getResultList();
    }
}

