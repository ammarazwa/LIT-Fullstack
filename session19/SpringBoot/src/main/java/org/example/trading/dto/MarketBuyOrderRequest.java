package org.example.trading.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class MarketBuyOrderRequest {

    @NotNull
    private Long accountId;

    @NotBlank
    private String symbol;

    @NotNull
    @Min(1)
    private Integer quantity;
}
