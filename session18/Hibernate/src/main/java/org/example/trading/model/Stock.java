package org.example.trading.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
        name = "stocks",
        uniqueConstraints = @UniqueConstraint(columnNames = "symbol")
)
public class Stock {

    @Id
    @Column(name = "symbol", length = 10)
    private String symbol;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "sector", length = 50)
    private String sector;

    @Column(name = "industry", length = 100)
    private String industry;

    @Column(name = "current_price", precision = 10, scale = 2)
    private BigDecimal currentPrice;

    @Column(name = "market_cap", precision = 20, scale = 2)
    private BigDecimal marketCap;

    @Column(name = "pe_ratio")
    private Double peRatio;

    @Column(name = "dividend_yield")
    private Float dividendYield;

    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
    private List<Position> positions = new ArrayList<>();

    public Stock() {}

    public Stock(String symbol, String name, String sector, String industry) {
        this.symbol = symbol != null ? symbol.toUpperCase() : null;
        this.name = name;
        this.sector = sector;
        this.industry = industry;
        this.lastUpdated = LocalDateTime.now();
    }

    public void updatePrice(BigDecimal newPrice) {
        this.currentPrice = newPrice;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol != null ? symbol.toUpperCase() : null; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSector() { return sector; }
    public void setSector(String sector) { this.sector = sector; }

    public String getIndustry() { return industry; }
    public void setIndustry(String industry) { this.industry = industry; }

    public BigDecimal getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
        this.lastUpdated = LocalDateTime.now();
    }

    public BigDecimal getMarketCap() { return marketCap; }
    public void setMarketCap(BigDecimal marketCap) { this.marketCap = marketCap; }

    public Double getPeRatio() { return peRatio; }
    public void setPeRatio(Double peRatio) { this.peRatio = peRatio; }

    public Float getDividendYield() { return dividendYield; }
    public void setDividendYield(Float dividendYield) { this.dividendYield = dividendYield; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }

    public List<Position> getPositions() { return positions; }
    public void setPositions(List<Position> positions) { this.positions = positions; }

    @Override
    public String toString() {
        return "Stock{" +
                "symbol='" + symbol + '\'' +
                ", name='" + name + '\'' +
                ", price=" + (currentPrice != null ? currentPrice : BigDecimal.ZERO) +
                '}';
    }
}

