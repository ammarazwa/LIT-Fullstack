# Assignment 03: API Gateway with Spring Cloud Gateway

## Learning Objectives
- Understand API Gateway pattern
- Learn to configure Spring Cloud Gateway
- Understand route configuration
- Practice load balancing with gateway
- Learn single entry point pattern

## The Challenge

Set up Spring Cloud Gateway to route requests to microservices.

### Key Concepts

1. **API Gateway**: 
   - Single entry point for all clients
   - Routes requests to appropriate services
   - Centralized cross-cutting concerns

2. **Route Configuration**: 
   - Defines URL patterns
   - Maps to service names
   - Supports load balancing

3. **Spring Cloud Gateway**: 
   - Reactive gateway implementation
   - Route predicates and filters
   - Integrates with service discovery

### The Solution Pattern

```yaml
spring:
  cloud:
    gateway:
      routes:
        - id: portfolio-service
          uri: lb://portfolio-service
          predicates:
            - Path=/api/portfolios/**
```

## Running Tests

```bash
mvn test                    # Problem file (should fail)
mvn test -Dtest.solution=true  # Solution file (should pass)
```

## Test Cases

- ✅ Gateway application exists
- ✅ Route configuration is correct
- ✅ Routes map to services
- ✅ Load balancing is configured

## Key Takeaways

- API Gateway provides single entry point
- Routes map URLs to services
- lb:// enables load balancing
- Gateway centralizes routing logic

