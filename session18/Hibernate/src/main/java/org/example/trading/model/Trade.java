package org.example.trading.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "trades",
        indexes = {
                @Index(name = "idx_trade_date", columnList = "trade_date"),
                @Index(name = "idx_symbol", columnList = "symbol")
        }
)
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol", nullable = false, length = 10)
    private String symbol;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(name = "trade_type", nullable = false, length = 10)
    private TradeType tradeType;

    @Column(name = "trade_date", nullable = false)
    private LocalDateTime tradeDate;

    @Column(name = "commission", precision = 10, scale = 2)
    private BigDecimal commission = BigDecimal.ZERO;

    @Column(name = "notes", length = 500)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_trade_account"))
    private TradingAccount account;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stock_symbol", referencedColumnName = "symbol",
            foreignKey = @ForeignKey(name = "fk_trade_stock"))
    private Stock stock;

    @Transient
    private BigDecimal totalAmount;

    public Trade() {}

    public Trade(String symbol, Integer quantity, BigDecimal price, TradeType tradeType) {
        this.symbol = symbol != null ? symbol.toUpperCase() : null;
        this.quantity = quantity;
        this.price = price;
        this.tradeType = tradeType;
        this.tradeDate = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        if (tradeDate == null) {
            tradeDate = LocalDateTime.now();
        }
    }

    public BigDecimal getTotalAmount() {
        if (totalAmount == null) {
            totalAmount = price.multiply(new BigDecimal(quantity)).add(commission);
        }
        return totalAmount;
    }

    public BigDecimal getNetAmount() {
        BigDecimal base = price.multiply(new BigDecimal(quantity));
        return tradeType == TradeType.BUY ? base.add(commission) : base.subtract(commission);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSymbol() { return symbol; }
    public void setSymbol(String symbol) { this.symbol = symbol != null ? symbol.toUpperCase() : null; }

    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public TradeType getTradeType() { return tradeType; }
    public void setTradeType(TradeType tradeType) { this.tradeType = tradeType; }

    public LocalDateTime getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDateTime tradeDate) { this.tradeDate = tradeDate; }

    public BigDecimal getCommission() { return commission; }
    public void setCommission(BigDecimal commission) { this.commission = commission; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public TradingAccount getAccount() { return account; }
    public void setAccount(TradingAccount account) { this.account = account; }

    public Stock getStock() { return stock; }
    public void setStock(Stock stock) { this.stock = stock; }

    @Override
    public String toString() {
        return "Trade{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", type=" + tradeType +
                ", qty=" + quantity +
                ", price=" + price +
                ", date=" + tradeDate +
                '}';
    }
}
