/**
 * HQL Query Service - SOLUTION
 */

package com.kulkultech.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class HQLQueryServiceSolution {
    
    public List<?> findAllAccounts(Session session) {
        Query<?> query = session.createQuery("FROM TradingAccountStub", Object.class);
        return query.getResultList();
    }
    
    public List<?> findAccountsByType(Session session, String accountType) {
        Query<?> query = session.createQuery("FROM TradingAccountStub WHERE accountType = :type", Object.class);
        query.setParameter("type", accountType);
        return query.getResultList();
    }
    
    public Long countAccounts(Session session) {
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM TradingAccountStub", Long.class);
        return query.getSingleResult();
    }
}

