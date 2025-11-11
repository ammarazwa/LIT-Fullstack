package org.example.trading.controller;

import org.example.trading.dto.ApiResponse;
import org.example.trading.dto.MarketBuyOrderRequest;
import org.example.trading.model.Order;
import org.example.trading.model.OrderStatus;
import org.example.trading.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/market-buy")
    public ResponseEntity<ApiResponse<Order>> placeMarketBuyOrder(@RequestBody @Valid MarketBuyOrderRequest request) {
        Order order = orderService.executeMarketBuy(request.getAccountId(), request.getSymbol(), request.getQuantity());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Market buy order executed", order));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<ApiResponse<List<Order>>> getAccountOrders(@PathVariable Long accountId) {
        List<Order> orders = orderService.getOrdersByAccount(accountId);
        return ResponseEntity.ok(ApiResponse.success("Orders retrieved", orders));
    }

    @GetMapping("/{orderId}/status")
    public ResponseEntity<ApiResponse<OrderStatus>> getOrderStatus(@PathVariable Long orderId) {
        OrderStatus status = orderService.getOrderStatus(orderId);
        return ResponseEntity.ok(ApiResponse.success("Order status retrieved", status));
    }
}
