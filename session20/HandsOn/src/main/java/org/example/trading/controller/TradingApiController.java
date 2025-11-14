package org.example.trading.controller;

import org.example.trading.model.OrderRequest;
import org.example.trading.model.OrderResponse;
import org.example.trading.service.TradingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TradingApiController {

    @Autowired
    private TradingService tradingService;

    @GetMapping("/health")
    public ResponseEntity<String> checkHealth() {
        return ResponseEntity.ok("API is up and running!");
    }

    @PostMapping("/orders")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        OrderResponse order = tradingService.placeOrder(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/api/v1/orders/" + order.getOrderId())
                .body(order);
    }
}
