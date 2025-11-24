# Assignment 01: Service Discovery with Eureka

## Learning Objectives
- Understand service discovery in microservices
- Learn to set up Eureka Server
- Understand @EnableEurekaServer annotation
- Learn to register services with Eureka
- Understand @EnableDiscoveryClient annotation

## The Challenge

Set up Eureka Server for service discovery and register a microservice with it.

### Key Concepts

1. **Service Discovery**: 
   - Services register themselves with discovery server
   - Services can find other services by name
   - Dynamic service location

2. **Eureka Server**: 
   - Service discovery server
   - Maintains registry of all services
   - Provides dashboard for monitoring

3. **@EnableEurekaServer**: 
   - Enables Eureka server functionality
   - Server doesn't register itself

4. **@EnableDiscoveryClient**: 
   - Registers service with Eureka
   - Enables service discovery client

### The Solution Pattern

```java
// Eureka Server
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}

// Microservice
@SpringBootApplication
@EnableDiscoveryClient
public class PortfolioServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PortfolioServiceApplication.class, args);
    }
}
```

## Running Tests

```bash
mvn test                    # Problem file (should fail)
mvn test -Dtest.solution=true  # Solution file (should pass)
```

## Test Cases

- ✅ Eureka Server has @EnableEurekaServer
- ✅ Microservice has @EnableDiscoveryClient
- ✅ Configuration is correct
- ✅ Services can register

## Key Takeaways

- Service discovery enables dynamic service location
- Eureka Server maintains service registry
- @EnableEurekaServer creates discovery server
- @EnableDiscoveryClient registers services
- Services find each other by name, not hardcoded URLs

