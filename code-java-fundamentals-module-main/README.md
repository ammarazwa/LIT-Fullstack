# Java Fundamentals Module 2

Workshop assignments covering **Multithreading**, **JDBC**, and **Hibernate** in Java.

## 📚 Sessions

### Session 16: Multithreading (4 assignments)
- Basic Threads - Extending Thread class
- Thread Synchronization - Using synchronized methods/blocks
- Thread Coordination - Wait/Notify pattern
- ExecutorService - Managing thread pools

### Session 17: JDBC (5 assignments)
- Database Connection - Establishing JDBC connections
- CRUD Operations - Create, Read, Update, Delete
- PreparedStatement - Parameterized queries for safety
- Transactions - ACID properties and rollback
- Connection Pooling - Efficient connection management

### Session 18: Hibernate (4 assignments)
- Entity Mapping - JPA annotations and table mapping
- Relationships - One-to-Many, Many-to-One
- HQL Queries - Hibernate Query Language
- Performance Optimization - Batch processing, fetch joins

## 🚀 Getting Started

### Prerequisites
- Java 11+
- Maven 3.6+

### How to Run Tests

**Test Problem File (Expected to Fail)**:
```bash
cd session-16-multithreading/assignment-01-basic-threads
mvn test
```

**Test Solution File (Expected to Pass)**:
```bash
mvn test -Dtest.solution=true
```

## 📖 Learning Workflow

1. **Navigate** to an assignment directory
2. **Read** the `README.md` for learning objectives
3. **Run** tests to see what's expected: `mvn test` (should fail)
4. **Implement** the problem file to make tests pass
5. **Verify** your solution: `mvn test` (should pass)
6. **Compare** with solution if needed: `mvn test -Dtest.solution=true`

## 📁 Project Structure

```
session-XX-topic/
  assignment-XX-description/
    pom.xml                          # Maven configuration
    src/
      main/java/.../Problem.java     # Your implementation (incomplete)
      main/java/.../ProblemSolution.java  # Reference solution
      test/java/.../ProblemTest.java # Tests
    README.md                         # Assignment details
    RUN.md                           # How to run instructions
```

## ✅ QA Process

Each assignment follows TDD (Test-Driven Development):
- ❌ Problem file tests **fail** (expected)
- ✅ Solution file tests **pass** (expected)
- Tests automatically switch via `-Dtest.solution=true` flag

## 💡 Tips

- Start with Session 16, then progress through 17 and 18
- Read the `README.md` in each assignment for specific guidance
- Use `mvn test -Dtest.solution=true` to see the expected solution
- Check `RUN.md` for detailed execution instructions

## 🔧 Troubleshooting

**Tests fail even with solution?**
- Ensure you're using `-Dtest.solution=true` flag
- Check Java version: `java -version` (should be 11+)
- Verify Maven: `mvn -version`

**Compilation errors?**
- Run `mvn clean compile` first
- Check that all required fields/methods are implemented

---

**Happy Learning! 🎓**
