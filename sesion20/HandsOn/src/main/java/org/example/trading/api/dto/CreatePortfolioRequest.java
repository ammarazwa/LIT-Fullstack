package org.example.trading.api.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreatePortfolioRequest {

    @NotBlank(message = "Portfolio name is required")
    @Size(min = 3, max = 100, message = "Portfolio name must be between 3 and 100 characters")
    private String name;

    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotNull(message = "Initial balance is required")
    @DecimalMin(value = "100.00", message = "Minimum initial balance is $100")
    @DecimalMax(value = "10000000.00", message = "Maximum initial balance is $10,000,000")
    private BigDecimal initialBalance;

    @NotBlank(message = "Currency is required")
    @Pattern(regexp = "USD|EUR|GBP|JPY", message = "Unsupported currency")
    private String currency;

    @NotNull(message = "Risk tolerance is required")
    @Pattern(regexp = "LOW|MEDIUM|HIGH", message = "Invalid risk tolerance")
    private String riskTolerance;
}
