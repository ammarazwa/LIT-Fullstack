package org.example.trading.api.controller;

import jakarta.validation.ValidationException;
import org.example.trading.api.dto.OrderRequest;
import org.example.trading.api.service.OrderService;
import org.example.trading.api.service.MarketDataService;
import org.example.trading.api.model.OrderResponse;
import org.example.trading.api.model.ValidationResult;
import org.example.trading.api.exception.MarketClosedException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private MarketDataService marketDataService;

    @PostMapping
    public ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest request) throws MarketClosedException {
        // Validation passed, process order

        // Additional business validation
        if (request.getOrderType().equals("LIMIT") && request.getLimitPrice() == null) {
            throw new ValidationException("Limit price is required for LIMIT orders");
        }

        if (request.getOrderType().equals("STOP") && request.getStopPrice() == null) {
            throw new ValidationException("Stop price is required for STOP orders");
        }

        // Check if market is open
        if (!marketDataService.isMarketOpen()) {
            throw new MarketClosedException();
        }

        // Execute order
        OrderResponse response = orderService.placeOrder(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/api/v1/orders/" + response.getOrderId())
                .body(response);
    }

    // Custom validation method
    @PostMapping("/validate")
    public ResponseEntity<ValidationResult> validateOrder(@RequestBody OrderRequest request) {
        ValidationResult result = orderService.validateOrder(request);
        return ResponseEntity.ok(result);
    }
}
