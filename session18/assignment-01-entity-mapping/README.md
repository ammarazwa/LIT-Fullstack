# Assignment 01: Entity Mapping

## Learning Objectives
- Understand JPA annotations (@Entity, @Table, @Id)
- Map Java classes to database tables
- Use @Column for field mapping
- Implement lifecycle callbacks (@PrePersist, @PreUpdate)

## The Challenge

Add JPA annotations to map TradingAccount class to database table.

### Key Annotations

- `@Entity`: Marks class as JPA entity
- `@Table`: Maps to database table
- `@Id` + `@GeneratedValue`: Primary key
- `@Column`: Field-to-column mapping
- `@PrePersist` / `@PreUpdate`: Lifecycle hooks

## Running Tests

```bash
mvn test                    # Problem file (should fail)
mvn test -Dtest.solution=true  # Solution file (should pass)
```

