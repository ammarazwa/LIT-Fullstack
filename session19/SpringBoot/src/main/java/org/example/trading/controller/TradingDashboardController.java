package org.example.trading.controller;

import org.example.trading.model.Portfolio;
import org.example.trading.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trading/dashboard")
public class TradingDashboardController {

    @Autowired
    private PortfolioService portfolioService;

    @GetMapping
    public String showDashboard(Model model) {
        List<Portfolio> portfolios = portfolioService.getAllPortfolios();
        model.addAttribute("portfolios", portfolios);
        return "dashboard";
    }

    @GetMapping("/portfolio/{id}")
    public String viewPortfolio(@PathVariable Long id, Model model) {
        Portfolio portfolio = portfolioService.getPortfolio(id);
        model.addAttribute("portfolio", portfolio);
        return "portfolio-detail";
    }
}
