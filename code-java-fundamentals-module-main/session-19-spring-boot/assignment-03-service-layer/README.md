# Assignment 03: Dependency Injection and Service Layer

## Learning Objectives
- Understand the Service layer pattern
- Learn to use @Service annotation
- Understand dependency injection with @Autowired
- Practice constructor injection (recommended approach)
- Learn separation of concerns (Controller → Service)

## The Challenge

Create a `PortfolioService` class that contains business logic and is injected into a controller using dependency injection.

### Key Concepts

1. **@Service**: 
   - Marks a class as a service component
   - Spring automatically detects and manages it
   - Contains business logic

2. **Dependency Injection**: 
   - Spring creates and manages object dependencies
   - Reduces coupling between components
   - Makes code testable

3. **Constructor Injection (Recommended)**: 
   - Dependencies provided via constructor
   - Immutable dependencies
   - Easy to test
   - Spring automatically injects

4. **Service Layer Pattern**: 
   - Controllers handle HTTP requests
   - Services contain business logic
   - Repositories handle data access
   - Clear separation of concerns

### The Solution Pattern

```java
@Service
public class PortfolioService {
    
    public Portfolio findById(Long id) {
        // Business logic here
        return portfolio;
    }
    
    public Portfolio save(Portfolio portfolio) {
        // Business logic here
        return savedPortfolio;
    }
}

@RestController
public class PortfolioController {
    
    private final PortfolioService portfolioService;
    
    // Constructor injection
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }
    
    @GetMapping("/{id}")
    public Portfolio getPortfolio(@PathVariable Long id) {
        return portfolioService.findById(id);
    }
}
```

## Running Tests

```bash
mvn test                    # Problem file (should fail)
mvn test -Dtest.solution=true  # Solution file (should pass)
```

## Test Cases

- ✅ Service class has @Service annotation
- ✅ Controller uses constructor injection
- ✅ Service methods contain business logic
- ✅ Controller delegates to service

## Key Takeaways

- @Service marks business logic components
- Constructor injection is preferred over field injection
- Service layer separates business logic from HTTP handling
- Dependency injection makes code testable and maintainable

