package org.example.trading.service;

import org.example.trading.model.OrderRequest;
import org.example.trading.model.OrderResponse;
import org.springframework.stereotype.Service;

@Service
public class TradingService {

    public OrderResponse placeOrder(OrderRequest request) {
        // Logic to place an order
        OrderResponse order = new OrderResponse();
        order.setOrderId("12345");
        order.setStatus("Completed");
        return order;
    }
}
