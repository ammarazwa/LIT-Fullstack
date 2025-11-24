# Assignment 01: Basic Spring Boot Controller

## Learning Objectives
- Understand how to create a Spring Boot REST controller
- Learn to use @RestController annotation
- Understand @RequestMapping and @GetMapping
- Practice creating simple REST endpoints
- Learn Spring Boot application structure

## The Challenge

Create a `PortfolioController` class that handles HTTP GET requests for a trading portfolio API.

### What You'll Learn

1. **@RestController**: 
   - Marks a class as a REST controller
   - Combines @Controller and @ResponseBody
   - Automatically converts return values to JSON

2. **@RequestMapping**: 
   - Maps HTTP requests to controller methods
   - Can be used at class or method level
   - Defines base URL path for all methods in controller

3. **@GetMapping**: 
   - Handles HTTP GET requests
   - Shorthand for @RequestMapping(method = RequestMethod.GET)
   - Maps to specific URL path

4. **Spring Boot Auto-Configuration**:
   - Spring Boot automatically configures embedded Tomcat server
   - No XML configuration needed
   - Just add @SpringBootApplication and run!

### The Solution Pattern

```java
@RestController
@RequestMapping("/api/portfolios")
public class PortfolioController {
    
    @GetMapping
    public String getAllPortfolios() {
        return "Welcome to Trading Portfolio API!";
    }
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello from Portfolio Controller!";
    }
}
```

### Why Spring Boot Controllers?

In modern web applications, REST controllers provide:
- Clean separation of concerns
- Easy JSON serialization
- Built-in HTTP method mapping
- Automatic content negotiation
- Simple request/response handling

## Running the Tests

### Step 1: Run Tests (Will Fail Initially)
```bash
mvn test
```

### Step 2: Implement Solution
Complete the `PortfolioController.java` file to make tests pass.

### Step 3: Verify Solution
```bash
# Test your implementation
mvn test

# Test with solution (should pass)
mvn test -Dtest.solution=true
```

## Test Cases

Your implementation should:
- ✅ Create a REST controller with @RestController annotation
- ✅ Map requests to /api/portfolios base path
- ✅ Handle GET requests to return a welcome message
- ✅ Handle GET requests to /api/portfolios/hello endpoint

## Key Takeaways

- @RestController combines @Controller and @ResponseBody
- @RequestMapping defines the base URL path
- @GetMapping handles HTTP GET requests
- Spring Boot auto-configures the web server
- No XML configuration needed - convention over configuration

## Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring MVC @RestController](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/RestController.html)
- [Spring Request Mapping](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html#mvc-ann-requestmapping)

