package org.example.trading.model;

import lombok.Data;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "positions")
@Data
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "portfolio_id", nullable = false)
    private Portfolio portfolio;

    @Column(nullable = false, length = 10)
    private String symbol;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false, precision = 15, scale = 4)
    private BigDecimal averageCost;

    @Column(precision = 15, scale = 4)
    private BigDecimal currentPrice;

    @Column(precision = 15, scale = 2)
    private BigDecimal totalValue;

    @Column(precision = 15, scale = 2)
    private BigDecimal unrealizedGainLoss;

    @Column(nullable = false)
    private LocalDateTime openedAt;

    @Column
    private LocalDateTime updatedAt;
}
