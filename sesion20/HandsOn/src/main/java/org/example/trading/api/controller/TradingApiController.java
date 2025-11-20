package org.example.trading.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.example.trading.api.service.TradingService;
import org.example.trading.api.model.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TradingApiController {

    @Autowired
    private TradingService tradingService;

    // GET - Retrieve data (idempotent, safe, cacheable)
    @GetMapping("/health")
    public ResponseEntity<HealthStatus> checkHealth() {
        HealthStatus status = new HealthStatus("UP", System.currentTimeMillis());
        return ResponseEntity.ok(status);
    }

    // POST - Create new resource
    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        OrderResponse order = tradingService.placeOrder(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/api/v1/orders/" + order.getOrderId())
                .body(order);
    }
}
