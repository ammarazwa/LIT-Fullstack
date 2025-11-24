/**
 * Portfolio Repository - Spring Data JPA Repository
 * 
 * Challenge: Create a repository interface with custom query methods
 * 
 * Your task: Complete this repository interface
 * 
 * Concepts covered:
 * - JpaRepository interface
 * - Custom query methods
 * - @Query annotation
 */

package com.kulkultech.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// TODO: Add @Repository annotation (optional but recommended)
// TODO: Extend JpaRepository<Portfolio, Long>
// Hint: JpaRepository provides save, findById, findAll, delete, etc.

public interface PortfolioRepository {
    
    // TODO: Add method to find portfolios by name
    // Hint: Spring generates: SELECT * FROM portfolios WHERE name = ?
    // Method signature: List<Portfolio> findByName(String name);
    
    // TODO: Add method to find portfolios with balance greater than a value
    // Hint: Method name: findByBalanceGreaterThan
    // Method signature: List<Portfolio> findByBalanceGreaterThan(Double balance);
    
    // TODO: Add custom query using @Query annotation
    // Hint: Use @Query("SELECT p FROM Portfolio p WHERE p.balance > :minBalance")
    // Method signature: List<Portfolio> findWealthyPortfolios(@Param("minBalance") Double minBalance);
}

