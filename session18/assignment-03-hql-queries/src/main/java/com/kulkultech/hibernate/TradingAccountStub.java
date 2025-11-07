/**
 * Trading Account Stub - For HQL Query Testing
 * 
 * This is a minimal stub entity for testing HQL queries
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
    
    @Column(name = "account_type")
    private String accountType;
    
    @Column(name = "balance")
    private BigDecimal balance;
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }
    
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}

