# Assignment 04: Circuit Breaker with Resilience4j

## Learning Objectives
- Understand circuit breaker pattern
- Learn to use Resilience4j for fault tolerance
- Understand @CircuitBreaker annotation
- Practice fallback methods
- Learn resilience patterns

## The Challenge

Implement circuit breaker pattern to handle service failures gracefully.

### Key Concepts

1. **Circuit Breaker**: 
   - Prevents cascading failures
   - Fast failure when service is down
   - Automatic recovery testing

2. **Resilience4j**: 
   - Lightweight fault tolerance library
   - Circuit breaker implementation
   - Spring Boot integration

3. **@CircuitBreaker**: 
   - Applies circuit breaker to method
   - Configurable failure thresholds
   - Fallback method support

4. **Fallback Methods**: 
   - Executed when circuit is open
   - Provides default response
   - Prevents complete failure

### The Solution Pattern

```java
@CircuitBreaker(name = "portfolioService", fallbackMethod = "getPortfolioFallback")
public Portfolio getPortfolio(Long id) {
    return portfolioClient.getPortfolio(id);
}

private Portfolio getPortfolioFallback(Long id, Exception ex) {
    // Return cached or default portfolio
    return defaultPortfolio;
}
```

## Running Tests

```bash
mvn test                    # Problem file (should fail)
mvn test -Dtest.solution=true  # Solution file (should pass)
```

## Test Cases

- ✅ Circuit breaker annotation is present
- ✅ Fallback method exists
- ✅ Circuit breaker configuration works
- ✅ Fault tolerance is implemented

## Key Takeaways

- Circuit breaker prevents cascading failures
- @CircuitBreaker applies resilience pattern
- Fallback methods provide graceful degradation
- Resilience4j provides fault tolerance

