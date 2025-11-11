package org.example.trading.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CreateAccountRequest {

    @NotBlank(message = "Account name is required")
    @Size(min = 3, max = 100, message = "Account name must be 3-100 characters")
    private String accountName;

    @NotBlank(message = "Account type is required")
    @Pattern(regexp = "INDIVIDUAL|CORPORATE|JOINT", message = "Invalid account type")
    private String accountType;

    @NotNull(message = "Initial deposit is required")
    @DecimalMin(value = "1000.0", message = "Minimum initial deposit is $1,000")
    private Double initialDeposit;

    @Email(message = "Invalid email format")
    private String email;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Invalid phone number")
    private String phoneNumber;
}
