package org.example.trading.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockPrice {
    private String symbol;
    private double price;
    private String currency;
}
