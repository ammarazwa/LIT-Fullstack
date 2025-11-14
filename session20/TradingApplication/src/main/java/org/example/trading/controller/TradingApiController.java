package org.example.trading.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.trading.api.service.TradingService;
import com.trading.api.model.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TradingApiController {

    @Autowired
    private TradingService tradingService;

    @GetMapping("/health")
    public ResponseEntity<HealthStatus> checkHealth() {
        HealthStatus status = new HealthStatus("UP", System.currentTimeMillis());
        return ResponseEntity.ok(status);
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        OrderResponse order = tradingService.placeOrder(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/api/v1/orders/" + order.getOrderId())
                .body(order);
    }
}
