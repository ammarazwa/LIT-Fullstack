package org.example.trading.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class OrderRequest {

    @NotNull
    private Long portfolioId;

    @NotNull
    private String symbol;

    @NotNull
    private String orderType;

    @NotNull
    private String action;

    @NotNull
    private Integer quantity;

    private String limitPrice;
    private String stopPrice;
}
