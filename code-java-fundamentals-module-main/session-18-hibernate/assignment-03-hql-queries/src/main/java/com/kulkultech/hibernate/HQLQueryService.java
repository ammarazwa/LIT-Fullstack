/**
 * HQL Query Service - Hibernate Query Language
 * 
 * Challenge: Use HQL to query entities instead of SQL
 * 
 * TODO: Implement HQL queries
 */

package com.kulkultech.hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class HQLQueryService {
    
    /**
     * Find all accounts using HQL
     * TODO: Use session.createQuery("FROM TradingAccountStub")
     */
    public List<?> findAllAccounts(Session session) {
        // TODO: Create HQL query: "FROM TradingAccountStub"
        // TODO: Return list of results
        return null;
    }
    
    /**
     * Find accounts by type using HQL with parameter
     * TODO: Use parameterized HQL query
     */
    public List<?> findAccountsByType(Session session, String accountType) {
        // TODO: Create HQL query: "FROM TradingAccountStub WHERE accountType = :type"
        // TODO: Set parameter: query.setParameter("type", accountType)
        // TODO: Return list of results
        return null;
    }
    
    /**
     * Count accounts using HQL aggregate function
     * TODO: Use SELECT COUNT(*) FROM TradingAccountStub
     */
    public Long countAccounts(Session session) {
        // TODO: Create HQL query: "SELECT COUNT(*) FROM TradingAccountStub"
        // TODO: Return single result
        return null;
    }
}

