/**
 * Trading Account Entity - Entity Mapping
 * 
 * Challenge: Map this Java class to a database table using JPA annotations
 * 
 * Your task: Add JPA annotations to map this class to the "trading_accounts" table
 * 
 * Concepts covered:
 * - @Entity annotation
 * - @Table annotation
 * - @Id and @GeneratedValue
 * - @Column annotation
 * - @Temporal for date/time fields
 */

package com.kulkultech.hibernate;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

// TODO: Add @Entity annotation
// TODO: Add @Table(name = "trading_accounts")
public class TradingAccount {
    
    // TODO: Add @Id and @GeneratedValue(strategy = GenerationType.IDENTITY)
    // TODO: Add @Column(name = "id")
    private Long id;
    
    // TODO: Add @Column(name = "account_name", nullable = false, length = 100)
    private String accountName;
    
    // TODO: Add @Column(name = "account_type", nullable = false, length = 20)
    private String accountType;
    
    // TODO: Add @Column(name = "balance", nullable = false, precision = 15, scale = 2)
    private BigDecimal balance;
    
    // TODO: Add @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    
    // TODO: Add @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
    
    // TODO: Add @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    
    // Default constructor (required by Hibernate)
    public TradingAccount() {
    }
    
    public TradingAccount(String accountName, String accountType, BigDecimal balance) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
        this.isActive = true;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
    
    // TODO: Add @PrePersist method to set createdAt and updatedAt
    // Hint: @PrePersist
    // protected void onCreate() { ... }
    
    // TODO: Add @PreUpdate method to set updatedAt
    // Hint: @PreUpdate
    // protected void onUpdate() { ... }
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getAccountName() { return accountName; }
    public void setAccountName(String accountName) { this.accountName = accountName; }
    
    public String getAccountType() { return accountType; }
    public void setAccountType(String accountType) { this.accountType = accountType; }
    
    public BigDecimal getBalance() { return balance; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
    
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
}

