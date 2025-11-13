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
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    List<Portfolio> findByName(String name);

    List<Portfolio> findByBalanceGreaterThan(Double balance);

    @Query("SELECT p FROM Portfolio p WHERE p.balance > :minBalance")
    List<Portfolio> findWealthyPortfolios(@Param("minBalance") Double minBalance);
}

