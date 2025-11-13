/**
 * Portfolio Repository - SOLUTION
 */

package com.kulkultech.springboot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepositorySolution extends JpaRepository<Portfolio, Long> {
    
    List<Portfolio> findByName(String name);

    List<Portfolio> findByBalanceGreaterThan(Double balance);

    @Query("SELECT p FROM Portfolio p WHERE p.balance > :minBalance")
    List<Portfolio> findWealthyPortfolios(@Param("minBalance") Double minBalance);
}

