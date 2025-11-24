package org.example.trading.api.controller;

import jakarta.validation.Valid;
import org.example.trading.api.model.ApiResponse;
import org.example.trading.api.model.PagedResponse;
import org.example.trading.api.model.Trade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/trades")
public class TradeController {

    @Autowired
    private TradeService tradeService;
    private Long tradeId;

    @GetMapping
    public ResponseEntity<ApiResponse<PagedResponse<Trade>>> getAllTrades(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {

        PagedResponse<Trade> trades = tradeService.getTrades(page, size);

        ApiResponse<PagedResponse<Trade>> response = ApiResponse.success(trades)
                .withMetadata("cached", false)
                .withMetadata("query_time_ms", 45);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{tradeId}")
    public ResponseEntity<ApiResponse<Trade>> getTrade(@PathVariable Long tradeId) {
        this.tradeId = tradeId;
        Trade trade = tradeService.findById(tradeId);
        return ResponseEntity.ok(ApiResponse.success(trade, "Trade retrieved successfully"));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Trade>> executeTrade(@Valid @RequestBody TradeRequest request) {
        Trade trade = tradeService.executeTrade(request);

        ApiResponse<Trade> response = ApiResponse.success(
                        trade,
                        "Trade executed successfully"
                ).withMetadata("execution_time_ms", 125)
                .withMetadata("market_price", trade.getExecutionPrice());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("Location", "/api/v1/trades/" + trade.getId())
                .body(response);
    }

    private class TradeService {
        public PagedResponse<Trade> getTrades(int page, int size) {
        }

        public Trade findById(Long tradeId) {
            return null;
        }

        public Trade executeTrade(@Valid TradeRequest request) {
            return null;
        }
    }

    private class TradeRequest {
    }
}
