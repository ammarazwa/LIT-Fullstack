package org.example.trading.model;

import lombok.*;

import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Portfolio {
    private Long accountId;
    private Double totalValue;
    private Map<String, Integer> positions; // symbol -> qty
}
