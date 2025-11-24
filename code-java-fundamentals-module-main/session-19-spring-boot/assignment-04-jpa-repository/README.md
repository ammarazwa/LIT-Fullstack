# Assignment 04: Spring Data JPA Repository

## Learning Objectives
- Understand Spring Data JPA repositories
- Learn to use JpaRepository interface
- Understand custom query methods
- Practice repository pattern
- Learn automatic query generation from method names

## The Challenge

Create a `PortfolioRepository` interface that extends JpaRepository and provides custom query methods.

### Key Concepts

1. **JpaRepository**: 
   - Provides CRUD operations automatically
   - No implementation needed
   - Spring generates implementation at runtime

2. **Custom Query Methods**: 
   - Method names define queries
   - Spring generates SQL from method names
   - Example: `findByName` → `SELECT * FROM portfolio WHERE name = ?`

3. **@Query Annotation**: 
   - Define custom JPQL queries
   - More control over query logic
   - Can use native SQL

4. **Repository Pattern**: 
   - Abstracts data access layer
   - Makes code testable
   - Separates persistence logic

### The Solution Pattern

```java
@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    
    // Automatic query generation
    List<Portfolio> findByName(String name);
    
    List<Portfolio> findByBalanceGreaterThan(Double balance);
    
    // Custom JPQL query
    @Query("SELECT p FROM Portfolio p WHERE p.balance > :minBalance")
    List<Portfolio> findWealthyPortfolios(@Param("minBalance") Double minBalance);
}
```

## Running Tests

```bash
mvn test                    # Problem file (should fail)
mvn test -Dtest.solution=true  # Solution file (should pass)
```

## Test Cases

- ✅ Repository extends JpaRepository
- ✅ Custom query methods work correctly
- ✅ @Query annotation works
- ✅ Repository methods are accessible

## Key Takeaways

- JpaRepository provides CRUD operations automatically
- Method names generate queries automatically
- @Query allows custom queries
- No implementation needed - Spring generates it

