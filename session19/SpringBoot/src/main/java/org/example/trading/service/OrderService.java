package org.example.trading.service;

import org.example.trading.dto.BuyOrderRequest;
import org.example.trading.dto.SellOrderRequest;
import org.example.trading.exception.AccountNotFoundException;
import org.example.trading.exception.InsufficientFundsException;
import org.example.trading.model.Order;
import org.example.trading.model.OrderStatus;
import org.example.trading.model.TradingAccount;
import org.example.trading.repository.OrderRepository;
import org.example.trading.repository.TradingAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TradingAccountRepository accountRepository;

    @Autowired
    private TradingService tradingService;

    public Order placeBuyOrder(BuyOrderRequest req) {
        TradingAccount acc = accountRepository.findById(req.getAccountId())
                .orElseThrow(() -> new AccountNotFoundException("Account not found: " + req.getAccountId()));

        double price = "MARKET".equals(req.getOrderType())
                ? tradingService.getCurrentPrice(req.getSymbol()).getPrice()
                : (req.getLimitPrice() != null ? req.getLimitPrice() : tradingService.getCurrentPrice(req.getSymbol()).getPrice());

        double cost = price * req.getQuantity();
        if (acc.getBalance() < cost) {
            throw new InsufficientFundsException("Insufficient balance to place buy order");
        }
        acc.setBalance(acc.getBalance() - cost);
        accountRepository.save(acc);

        Order order = Order.builder()
                .accountId(req.getAccountId())
                .symbol(req.getSymbol().toUpperCase())
                .quantity(req.getQuantity())
                .side("BUY")
                .type(req.getOrderType())
                .limitPrice(req.getLimitPrice())
                .status(OrderStatus.FILLED)
                .build();

        return orderRepository.save(order);
    }

    public Order placeSellOrder(SellOrderRequest req) {
        // For demo: mark as filled directly
        Order order = Order.builder()
                .accountId(req.getAccountId())
                .symbol(req.getSymbol().toUpperCase())
                .quantity(req.getQuantity())
                .side("SELL")
                .type(req.getOrderType())
                .limitPrice(req.getLimitPrice())
                .status(OrderStatus.FILLED)
                .build();
        return orderRepository.save(order);
    }

    public List<Order> getOrderHistory(Long accountId, int page, int size) {
        return orderRepository.findByAccountIdOrderByCreatedAtDesc(accountId);
    }

    public Order executeMarketBuy(Long accountId, String symbol, Integer qty) {
        BuyOrderRequest req = new BuyOrderRequest();
        req.setAccountId(accountId);
        req.setSymbol(symbol);
        req.setQuantity(qty);
        req.setOrderType("MARKET");
        return placeBuyOrder(req);
    }

    public List<Order> getOrdersByAccount(Long accountId) {
        return orderRepository.findByAccountIdOrderByCreatedAtDesc(accountId);
    }

    public org.example.trading.model.OrderStatus getOrderStatus(Long orderId) {
        return orderRepository.findById(orderId)
                .map(Order::getStatus)
                .orElse(OrderStatus.REJECTED);
    }
}

