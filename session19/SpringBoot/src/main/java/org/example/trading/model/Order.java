package org.example.trading.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long accountId;

    private String symbol;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private String side; // BUY/SELL

    private String type; // MARKET/LIMIT/STOP

    private Double limitPrice; // nullable for MARKET

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) status = OrderStatus.NEW;
    }
}
