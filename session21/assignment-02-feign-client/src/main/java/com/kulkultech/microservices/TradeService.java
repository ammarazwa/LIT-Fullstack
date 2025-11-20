package com.kulkultech.microservices;

import org.springframework.stereotype.Service;

@Service
public class TradeService {

    private final PortfolioClient portfolioClient;

    public TradeService(PortfolioClient portfolioClient) {
        this.portfolioClient = portfolioClient;
    }

    public Portfolio getPortfolioForTrade(Long portfolioId) {
        return portfolioClient.getPortfolio(portfolioId);
    }
}

