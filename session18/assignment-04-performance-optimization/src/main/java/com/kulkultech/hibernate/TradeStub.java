/**
 * Trade Stub - For Performance Optimization Testing
 */

package com.kulkultech.hibernate;

import jakarta.persistence.*;

@Entity
@Table(name = "trades")
public class TradeStub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "portfolio_id")
    private PortfolioStub portfolio;
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public PortfolioStub getPortfolio() { return portfolio; }
    public void setPortfolio(PortfolioStub portfolio) { this.portfolio = portfolio; }
}

