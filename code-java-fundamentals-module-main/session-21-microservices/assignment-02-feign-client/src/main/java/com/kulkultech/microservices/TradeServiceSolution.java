package com.kulkultech.microservices;

import org.springframework.stereotype.Service;

@Service
public class TradeServiceSolution {
    
    private final PortfolioClientSolution portfolioClient;
    
    public TradeServiceSolution(PortfolioClientSolution portfolioClient) {
        this.portfolioClient = portfolioClient;
    }
    
    public Portfolio getPortfolioForTrade(Long portfolioId) {
        return portfolioClient.getPortfolio(portfolioId);
    }
}

