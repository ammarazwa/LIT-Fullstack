package org.example.trading.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Stock {
    private String symbol;
    private String name;
    private String exchange;
}

