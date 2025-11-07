/**
 * Trading Account Stub - For Performance Optimization Testing
 */

package com.kulkultech.hibernate;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "trading_accounts")
public class TradingAccountStub {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "account_name")
    private String accountName;
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }
}

