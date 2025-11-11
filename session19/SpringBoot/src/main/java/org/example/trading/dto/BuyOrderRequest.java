package org.example.trading.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class BuyOrderRequest {

    @NotNull(message = "Account ID is required")
    private Long accountId;

    @NotBlank(message = "Stock symbol is required")
    @Size(min = 1, max = 10, message = "Symbol must be 1-10 characters")
    private String symbol;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Minimum quantity is 1")
    private Integer quantity;

    @NotNull(message = "Order type is required")
    @Pattern(regexp = "MARKET|LIMIT|STOP", message = "Invalid order type")
    private String orderType;

    @DecimalMin(value = "0.01", message = "Limit price must be greater than 0")
    private Double limitPrice; // optional for LIMIT orders
}
