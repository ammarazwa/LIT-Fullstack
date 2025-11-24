/**
 * Trade Controller - SOLUTION
 */

package com.kulkultech.springboot;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/trades")
public class TradeControllerSolution {
    
    private Map<Long, Trade> trades = new HashMap<>();
    private Long nextId = 1L;
    
    @GetMapping("/{id}")
    public Trade getTrade(@PathVariable Long id) {
        return trades.get(id);
    }
    
    @PostMapping
    public Trade createTrade(@RequestBody Trade trade) {
        trade.setId(nextId++);
        trades.put(trade.getId(), trade);
        return trade;
    }
    
    @PutMapping("/{id}")
    public Trade updateTrade(@PathVariable Long id, @RequestBody Trade trade) {
        if (trades.containsKey(id)) {
            trade.setId(id);
            trades.put(id, trade);
            return trade;
        }
        return null;
    }
    
    @DeleteMapping("/{id}")
    public void deleteTrade(@PathVariable Long id) {
        trades.remove(id);
    }
}

