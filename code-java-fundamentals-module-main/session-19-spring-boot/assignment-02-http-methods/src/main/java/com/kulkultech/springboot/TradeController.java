/**
 * Trade Controller - HTTP Methods and Request Mapping
 * 
 * Challenge: Implement CRUD operations using all HTTP methods
 * 
 * Your task: Complete this controller to handle GET, POST, PUT, DELETE requests
 * 
 * Concepts covered:
 * - @GetMapping, @PostMapping, @PutMapping, @DeleteMapping
 * - @PathVariable for URL parameters
 * - @RequestBody for request body
 */

package com.kulkultech.springboot;

import java.util.HashMap;
import java.util.Map;

// TODO: Add @RestController and @RequestMapping("/api/trades")

public class TradeController {
    
    // In-memory storage for trades
    private Map<Long, Trade> trades = new HashMap<>();
    private Long nextId = 1L;
    
    // TODO: Add @GetMapping("/{id}") to handle GET /api/trades/{id}
    // TODO: Add @PathVariable Long id parameter
    public Trade getTrade(/* TODO: Add @PathVariable Long id */) {
        // TODO: Retrieve trade from trades map by id
        // TODO: Return the trade or null if not found
        return null;
    }
    
    // TODO: Add @PostMapping to handle POST /api/trades
    // TODO: Add @RequestBody Trade trade parameter
    public Trade createTrade(/* TODO: Add @RequestBody Trade trade */) {
        // TODO: Set trade id to nextId
        // TODO: Increment nextId
        // TODO: Store trade in trades map
        // TODO: Return the created trade
        return null;
    }
    
    // TODO: Add @PutMapping("/{id}") to handle PUT /api/trades/{id}
    // TODO: Add @PathVariable Long id and @RequestBody Trade trade parameters
    public Trade updateTrade(/* TODO: Add parameters */) {
        // TODO: Check if trade exists in trades map
        // TODO: Update trade properties
        // TODO: Store updated trade back in map
        // TODO: Return updated trade
        return null;
    }
    
    // TODO: Add @DeleteMapping("/{id}") to handle DELETE /api/trades/{id}
    // TODO: Add @PathVariable Long id parameter
    public void deleteTrade(/* TODO: Add @PathVariable Long id */) {
        // TODO: Remove trade from trades map by id
    }
}

