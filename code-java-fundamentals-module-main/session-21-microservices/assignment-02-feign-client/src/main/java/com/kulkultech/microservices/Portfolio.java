package com.kulkultech.microservices;

public class Portfolio {
    private Long id;
    private String name;
    private Double balance;
    
    public Portfolio() {}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Double getBalance() { return balance; }
    public void setBalance(Double balance) { this.balance = balance; }
}

