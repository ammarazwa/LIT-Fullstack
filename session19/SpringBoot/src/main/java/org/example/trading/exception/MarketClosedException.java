package org.example.trading.exception;

public class MarketClosedException extends RuntimeException {
    public MarketClosedException(String message) {
        super(message);
    }
}
