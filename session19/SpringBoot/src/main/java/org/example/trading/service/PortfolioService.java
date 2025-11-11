package org.example.trading.service;

import org.example.trading.model.Portfolio;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PortfolioService {

    public Portfolio getPortfolioByAccountId(Long accountId) {
        Map<String, Integer> positions = new HashMap<>();
        positions.put("AAPL", 10);
        positions.put("MSFT", 5);
        return Portfolio.builder()
                .accountId(accountId)
                .totalValue(4500.0)
                .positions(positions)
                .build();
    }

    public List<Portfolio> getAllPortfolios() {
        return List.of(getPortfolioByAccountId(1L), getPortfolioByAccountId(2L));
    }

    public Portfolio getPortfolio(Long id) {
        return getPortfolioByAccountId(id);
    }
}

