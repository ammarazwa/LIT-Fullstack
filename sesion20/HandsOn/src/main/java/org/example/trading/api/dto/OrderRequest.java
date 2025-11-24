package org.example.trading.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderRequest {

    @NotNull(message = "Portfolio ID is required")
    @Positive(message = "Portfolio ID must be positive")
    private Long portfolioId;

    @NotBlank(message = "Stock symbol is required")
    @Pattern(regexp = "^[A-Z]{1,5}$", message = "Invalid stock symbol format")
    private String symbol;

    @NotNull(message = "Order type is required")
    @Pattern(regexp = "MARKET|LIMIT|STOP|STOP_LIMIT", message = "Invalid order type")
    private String orderType;

    @NotNull(message = "Action is required")
    @Pattern(regexp = "BUY|SELL", message = "Action must be BUY or SELL")
    private String action;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be positive")
    @Max(value = 1000000, message = "Quantity cannot exceed 1,000,000 shares")
    private Integer quantity;

    @DecimalMin(value = "0.01", message = "Price must be at least 0.01")
    @DecimalMax(value = "1000000.00", message = "Price cannot exceed 1,000,000")
    private BigDecimal limitPrice; // Required for LIMIT orders

    @DecimalMin(value = "0.01", message = "Stop price must be at least 0.01")
    private BigDecimal stopPrice; // Required for STOP orders

    @NotNull(message = "Time in force is required")
    @Pattern(regexp = "DAY|GTC|IOC|FOK", message = "Invalid time in force")
    private String timeInForce;
}
