# Assignment 02: HTTP Methods and Request Mapping

## Learning Objectives
- Understand all HTTP methods (GET, POST, PUT, DELETE)
- Learn to use @PostMapping, @PutMapping, @DeleteMapping
- Understand @PathVariable for URL parameters
- Learn @RequestBody for request body parsing
- Practice implementing CRUD operations

## The Challenge

Create a `TradeController` class that implements full CRUD operations using all HTTP methods.

### Key Concepts

1. **@PostMapping**: 
   - Handles HTTP POST requests
   - Used for creating new resources
   - Request body contains data to create

2. **@PutMapping**: 
   - Handles HTTP PUT requests
   - Used for updating entire resources
   - Idempotent operation

3. **@DeleteMapping**: 
   - Handles HTTP DELETE requests
   - Used for deleting resources
   - Idempotent operation

4. **@PathVariable**: 
   - Extracts values from URL path
   - Example: `/api/trades/{id}` → `@PathVariable Long id`

5. **@RequestBody**: 
   - Binds HTTP request body to method parameter
   - Automatically converts JSON to Java object
   - Requires Content-Type: application/json

### The Solution Pattern

```java
@RestController
@RequestMapping("/api/trades")
public class TradeController {
    
    @GetMapping("/{id}")
    public Trade getTrade(@PathVariable Long id) {
        // Get trade by ID
    }
    
    @PostMapping
    public Trade createTrade(@RequestBody Trade trade) {
        // Create new trade
    }
    
    @PutMapping("/{id}")
    public Trade updateTrade(@PathVariable Long id, @RequestBody Trade trade) {
        // Update trade
    }
    
    @DeleteMapping("/{id}")
    public void deleteTrade(@PathVariable Long id) {
        // Delete trade
    }
}
```

## Running Tests

```bash
mvn test                    # Problem file (should fail)
mvn test -Dtest.solution=true  # Solution file (should pass)
```

## Test Cases

- ✅ GET /api/trades/{id} - Retrieve trade by ID
- ✅ POST /api/trades - Create new trade
- ✅ PUT /api/trades/{id} - Update trade
- ✅ DELETE /api/trades/{id} - Delete trade

## Key Takeaways

- POST creates new resources
- PUT updates entire resources (idempotent)
- DELETE removes resources (idempotent)
- @PathVariable extracts URL path variables
- @RequestBody binds JSON request body to objects

