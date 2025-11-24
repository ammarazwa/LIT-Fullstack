# Assignment 02: Inter-Service Communication with Feign

## Learning Objectives
- Understand Feign client for service-to-service communication
- Learn to use @FeignClient annotation
- Understand @EnableFeignClients
- Practice declarative REST clients
- Learn service discovery integration

## The Challenge

Implement a Feign client to communicate between microservices.

### Key Concepts

1. **Feign Client**: 
   - Declarative REST client
   - Simplifies HTTP calls between services
   - Integrates with service discovery

2. **@FeignClient**: 
   - Marks interface as Feign client
   - Specifies service name
   - Defines endpoints

3. **@EnableFeignClients**: 
   - Enables Feign client scanning
   - Scans for @FeignClient interfaces

4. **Service Communication**: 
   - Services call each other by name
   - Feign handles load balancing
   - Automatic service discovery

### The Solution Pattern

```java
@FeignClient(name = "portfolio-service")
public interface PortfolioClient {
    @GetMapping("/api/portfolios/{id}")
    Portfolio getPortfolio(@PathVariable("id") Long id);
}

@SpringBootApplication
@EnableFeignClients
public class TradeServiceApplication {
    // ...
}
```

## Running Tests

```bash
mvn test                    # Problem file (should fail)
mvn test -Dtest.solution=true  # Solution file (should pass)
```

## Test Cases

- ✅ Feign client interface exists
- ✅ @FeignClient annotation is present
- ✅ Application has @EnableFeignClients
- ✅ Service can use Feign client

## Key Takeaways

- Feign provides declarative REST clients
- @FeignClient defines service communication
- @EnableFeignClients enables Feign scanning
- Services communicate by name, not URLs
- Feign integrates with service discovery

