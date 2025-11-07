package org.example.trading.model;


import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "positions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"portfolio_id", "stock_symbol"})
)
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "shares", nullable = false)
    private Integer shares;

    @Column(name = "average_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal averagePrice;

    @Column(name = "current_price", precision = 10, scale = 2)
    private BigDecimal currentPrice;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "portfolio_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_position_portfolio"))
    private Portfolio portfolio;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "stock_symbol", referencedColumnName = "symbol",
            nullable = false, foreignKey = @ForeignKey(name = "fk_position_stock"))
    private Stock stock;

    public Position() {}

    public Position(Stock stock, Integer shares, BigDecimal averagePrice) {
        this.stock = stock;
        this.shares = shares;
        this.averagePrice = averagePrice;
        this.currentPrice = averagePrice;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public BigDecimal getCurrentValue() {
        if (currentPrice != null && shares != null) {
            return currentPrice.multiply(new BigDecimal(shares));
        }
        return BigDecimal.ZERO;
    }

    public BigDecimal getTotalCost() {
        return averagePrice.multiply(new BigDecimal(shares));
    }

    public BigDecimal getProfitLoss() {
        return getCurrentValue().subtract(getTotalCost());
    }

    public Double getReturnPercent() {
        BigDecimal totalCost = getTotalCost();
        if (totalCost.compareTo(BigDecimal.ZERO) > 0) {
            return getProfitLoss()
                    .divide(totalCost, 4, RoundingMode.HALF_UP)
                    .multiply(new BigDecimal(100))
                    .doubleValue();
        }
        return 0.0;
    }

    public void updateAfterBuy(Integer newShares, BigDecimal buyPrice) {
        BigDecimal totalCost = getTotalCost();
        BigDecimal newCost = buyPrice.multiply(new BigDecimal(newShares));
        int totalShares = this.shares + newShares;

        this.averagePrice = totalCost.add(newCost)
                .divide(new BigDecimal(totalShares), 2, RoundingMode.HALF_UP);
        this.shares = totalShares;
    }

    public void updateAfterSell(Integer soldShares) {
        if (soldShares > this.shares) {
            throw new IllegalArgumentException("Cannot sell more shares than owned");
        }
        this.shares -= soldShares;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getShares() { return shares; }
    public void setShares(Integer shares) { this.shares = shares; }

    public BigDecimal getAveragePrice() { return averagePrice; }
    public void setAveragePrice(BigDecimal averagePrice) { this.averagePrice = averagePrice; }

    public BigDecimal getCurrentPrice() { return currentPrice; }
    public void setCurrentPrice(BigDecimal currentPrice) { this.currentPrice = currentPrice; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }

    public Portfolio getPortfolio() { return portfolio; }
    public void setPortfolio(Portfolio portfolio) { this.portfolio = portfolio; }

    public Stock getStock() { return stock; }
    public void setStock(Stock stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", symbol='" + (stock != null ? stock.getSymbol() : "N/A") + '\'' +
                ", shares=" + shares +
                ", avgPrice=" + averagePrice +
                ", currentPrice=" + currentPrice +
                ", P/L=" + getProfitLoss() +
                '}';
    }
}
