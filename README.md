# 🚀 HCL Core Java & Engineering Track - Daily Hands-on Tasks

This repository tracks the sequential daily hands-on coding tasks for the HCL Java Full Stack Training Program (Week 1 & 2 / Sprint 1).

## 🎯 Program Objective
The daily tasks are designed to build progressive competency in modern Java development (Java 21 LTS), software engineering best practices, Maven project structures, object-oriented design patterns, Git workflow management, clean code principles, collections architecture, generic programming, and debugging methodologies.

## 🛠️ Technologies & Tools Used
- **Language:** Java 21 (LTS)
- **Build Tool:** Apache Maven (with multi-profile builds)
- **Version Control:** Git & GitHub
- **IDE / Environment:** Antigravity / VS Code / IntelliJ / Terminal CLI
- **Key Concepts:** JVM Architecture, Language Fundamentals, Control Flow, OOP & Design Patterns, Exception Handling, Collections Framework (List, Set, Queue, Map), Generics & PECS, Resource Management (AutoCloseable), AI-Assisted Debugging

## 📅 Daily Progress Tracker

| Day | Task | Topic | Status | Short Description | Git Commit Message | Folder |
| --- | --- | --- | --- | --- | --- | --- |
| Day 1 | Daily Task 1 | Java Platform Basics + Agile/Scrum | Completed | JDK 21 installation, JVM PlatformInfo inspector, bytecode disassembly with `javap -c` | `day-1: java-platform-basics` | [Day 1](./daily-tasks/day-01-java-platform-agile) |
| Day 2 | Daily Task 2 | Language Fundamentals + Git Fundamentals | Completed | Monthly Usage Analyser with 1D/2D arrays, slab billing constants, overflow prevention, ternary grades | `day-2: language-fundamentals` | [Day 2](./daily-tasks/day-02-language-fundamentals-git) |
| Day 3 | Daily Task 3 | Control Flow + Maven | Completed | ATM Simulator console application with PIN verification, switch-menu, transaction statement, Maven packaging & dev/prod profiles | `day-3: control-flow` | [Day 3](./daily-tasks/day-03-control-flow-maven) |
| Day 4 | Daily Task 4 | OOP Concepts + IDE & Debugging | Completed | BankAccount domain model with constructor chaining, encapsulation, debugger watchpoints & hot code replace | `day-4: oop-concepts` | [Day 4](./daily-tasks/day-04-oop-debugging) |
| Day 5 | Daily Task 5 | Inheritance & Polymorphism + Branching | Completed | Payment hierarchy (Card/UPI/Cash), Refundable interface, Git conflict resolution simulation & rebase workflow | `day-5: inheritance-polymorphism` | [Day 5](./daily-tasks/day-05-inheritance-polymorphism) |
| Day 6 | Daily Task 6 | Exception Handling + AI Debugging | Completed | Custom checked/unchecked exceptions, chained causes, multi-catch, guaranteed finally audit, and AI error analysis | `day-6: exception-handling` | [Day 6](./daily-tasks/day-06-exception-handling) |
| Day 7 | Daily Task 7 | Collections Framework — Part 1 | Completed | Library Waitlist: ArrayList catalogue, LinkedHashSet de-dup, ArrayDeque waitlist, PriorityQueue faculty-first, Iterator remove for overdue items, ArrayList vs LinkedList benchmark | `day-7: collections-framework` | [Day 7](./daily-tasks/day-07-collections-framework-part1) |
| Day 8 | Daily Task 8 | Collections Framework — Part 2 | Completed | Employee Directory: HashMap lookup, frequency count with merge, TreeMap department report, Comparable by id, top 5 salary Comparator chain, missing-hashCode bug demo and fix | `day-8: collections framework` | [Day 8](./daily-tasks/day-08-collections-framework-part2) |
| Day 9 | Daily Task 9 | Generics & Try-With-Resources | Completed | Generic Toolkit: Pair<K,V>, Box<T extends Number>, <T extends Comparable<T>> max(), PECS wildcards, CSV ingestion with try-with-resources, AutoCloseable LIFO close order, generic CrudRepository<T,ID> | `day-9: generics` | [Day 9](./daily-tasks/day-09-generics) |
| Day 10 | Daily Task 10 | Memory Management & Garbage Collection | Completed | Memory Lab: StackOverflowError depth (-Xss), OutOfMemoryError (-Xmx), String += vs StringBuilder benchmark, -Xlog:gc analysis, Integer cache pitfall, Stack/Heap architecture diagram | `day-10: memory-management` | [Day 10](./daily-tasks/day-10-memory-management) |
| Day 11 | Daily Task 11 | AI Integration (Core Java) | Completed | Prompt & Review Drill: Vague vs Specific prompt comparison, review checklist for AI code, 5 critical issues found & fixed, StudentService, Course Enrollment (FR7), Academic Transcript (FR8) | `day-11: ai-integration-core-java` | [Day 11](./daily-tasks/day-11-ai-integration-core-java) |
| Day 12 | Daily Task 12 | ASSESSMENT 1 — Core Java Checkpoint | Completed | 3-hr practical: Consolidated application implementing 2 core business flows (Library waitlist dispatch & Enterprise workforce intelligence), warm-up quiz, rubric evaluation (100/100), release tag v1.0-core-java | `day-12: assessment-1` | [Day 12](./daily-tasks/day-12-assessment-1) |
| Day 13 | Daily Task 13 | Functional Programming | Completed | Product Filter Engine: Composed Predicates (and, or, negate), Function chains (andThen, compose), Consumer reporting, Supplier fallback defaults, 4 kinds of method references, lambda strategy patterns | `day-13: functional-programming` | [Day 13](./daily-tasks/day-13-functional-programming) |
| Day 14 | Daily Task 14 | Streams API | Completed | Sales Analytics: Revenue by category (groupingBy + summingDouble), top 3 customers, orders partitioned by status, summary statistics, comma-joined product catalog, sequential vs parallel stream benchmark (4.6x speedup) | `day-14: streams-api` | [Day 14](./daily-tasks/day-14-streams-api) |
| Day 15 | Daily Task 15 | Optional & DateTime | Completed | Subscription Calculator: compute expiry dates, days remaining, grace periods and IST/UTC conversions with java.time; refactor a null-heavy lookup service to return Optional and use orElseThrow with a custom exception | `day-15: optional-date-time` | [Day 15](./daily-tasks/day-15-optional-date-time) |
| Day 16 | Daily Task 16 | Concurrency Essentials | Completed | Price Aggregator: fetch prices from 3 simulated slow suppliers (sleep 1-2 s) sequentially, then with ExecutorService + Callable, then with CompletableFuture allOf; handle one supplier failing with exceptionally; compare timings (2.5x speedup) | `day-16: concurrency-essentials` | [Day 16](./daily-tasks/day-16-concurrency-essentials) |
| Day 17 | Daily Task 17 | Java 9-21 Features + Immutable Collections & Modern APIs | Completed | Shape & Invoice Lab: sealed interface Shape permits Circle, Rectangle, Triangle (records) with exhaustive pattern-matching switch for area; text block invoice email; UnsupportedOperationException on List.of; strip/isBlank sanitization | `day-17: java-9-21-features` | [Day 17](./daily-tasks/day-17-java-9-21-features) |
| Day 18 | Daily Task 18 | Relational Database Concepts | Completed | Normalization Workshop: flat "college_registrations" spreadsheet normalized step-by-step (0NF -> 1NF -> 2NF -> 3NF) with anomaly proofs; project ER diagram with full cardinalities and keys; NormalizationSimulator | `day-18: relational-database-concepts` | [Day 18](./daily-tasks/day-18-relational-database-concepts) |
| Day 19 | Daily Task 19 | CRUD Operations | Completed | Employees & departments database: DDL/DML schema, multi-row seed (30 employees, 6 depts), 12 filtering/sorting queries (BETWEEN, LIKE, IN, IS NULL, LIMIT), safe transaction updates, project schema & seed (>= 20 rows per table) | `day-19: crud-operations` | [Day 19](./daily-tasks/day-19-crud-operations) |

## 📁 Repository Directory Structure

```text
HCL-Daily-Tasks/
├── daily-tasks/day-01-java-platform-agile/
│   ├── notes/
│   │   ├── agile-scrum-basics.md
│   │   └── java-platform-basics.md
│   ├── src/
│   │   └── PlatformInfo.java
│   └── README.md
├── daily-tasks/day-02-language-fundamentals-git/
│   ├── notes/
│   │   ├── git-fundamentals.md
│   │   └── language-fundamentals.md
│   ├── src/
│   │   └── MonthlyUsageAnalyser.java
│   └── README.md
├── daily-tasks/day-03-control-flow-maven/
│   ├── notes/
│   │   ├── control-flow.md
│   │   └── maven-basics.md
│   ├── src/
│   │   └── main/java/ATMSimulator.java
│   ├── pom.xml
│   └── README.md
├── daily-tasks/day-04-oop-debugging/
│   ├── notes/
│   │   ├── debugging-guide.md
│   │   └── oop-concepts.md
│   ├── src/
│   │   └── com/hcl/bank/BankAccount.java
│   └── README.md
├── daily-tasks/day-05-inheritance-polymorphism/
│   ├── notes/
│   │   ├── git-branching-merging.md
│   │   ├── inheritance.md
│   │   └── polymorphism.md
│   ├── src/
│   │   └── com/hcl/payment/
│   │       ├── CardPayment.java
│   │       ├── CashPayment.java
│   │       ├── Payment.java
│   │       ├── PaymentDemo.java
│   │       ├── Refundable.java
│   │       └── UPIPayment.java
│   └── README.md
├── daily-tasks/day-06-exception-handling/
│   ├── deliverables/
│   │   └── .gitkeep
│   ├── notes/
│   │   ├── ai-stack-trace-debugging.md
│   │   ├── checked-vs-unchecked.md
│   │   └── exception-handling.md
│   ├── src/
│   │   ├── exceptions/
│   │   │   ├── InsufficientStockException.java
│   │   │   └── InvalidQuantityException.java
│   │   └── OrderProcessor.java
│   └── README.md
├── daily-tasks/day-07-collections-framework-part1/
│   ├── deliverables/
│   │   └── timing-table.md
│   ├── notes/
│   │   ├── collections-overview.md
│   │   ├── iterators-and-concurrency.md
│   │   ├── list-vs-set-internals.md
│   │   └── queues-and-deques.md
│   ├── src/
│   │   ├── benchmark/CollectionPerformanceBenchmark.java
│   │   ├── model/Book.java, PatronType.java, ReservationRequest.java
│   │   ├── service/LibraryWaitlistService.java
│   │   └── LibraryWaitlistApp.java
│   └── README.md
├── daily-tasks/day-08-collections-framework-part2/
│   ├── notes/
│   │   ├── comparable-vs-comparator.md
│   │   ├── hashcode-and-equals-contract.md
│   │   └── map-internals-and-implementations.md
│   ├── src/
│   │   ├── model/Department.java, Employee.java
│   │   ├── service/EmployeeDirectoryService.java, MissingHashCodeBugDemo.java
│   │   └── EmployeeDirectory.java
│   └── README.md
├── daily-tasks/day-09-generics/
│   ├── data/
│   │   └── products.csv
│   ├── notes/
│   │   ├── bounded-types-and-pecs.md
│   │   ├── generics-fundamentals-type-erasure.md
│   │   └── try-with-resources-and-autocloseable.md
│   ├── src/
│   │   ├── model/Product.java
│   │   ├── repository/CrudRepository.java, InMemoryRepository.java
│   │   ├── resource/AuditLoggerResource.java, DatabaseConnectionResource.java
│   │   ├── toolkit/Box.java, GenericToolkit.java, Pair.java
│   │   └── GenericApplication.java
│   └── README.md
├── daily-tasks/day-10-memory-management/
│   ├── deliverables/
│   │   └── memory-architecture-diagram.md
│   ├── notes/
│   │   ├── garbage-collection-tuning.md
│   │   ├── jvm-memory-architecture.md
│   │   └── string-pool-and-wrapper-cache.md
│   ├── src/
│   │   ├── benchmark/StringConcatBenchmark.java
│   │   ├── demo/IntegerCacheTrapDemo.java
│   │   ├── experiment/HeapOOMSimulation.java, StackOverflowSimulation.java
│   │   └── MemoryLab.java
│   └── README.md
├── daily-tasks/day-11-ai-integration-core-java/
│   ├── deliverables/
│   │   ├── ai-code-review-checklist.md
│   │   └── prompt-comparison.md
│   ├── notes/
│   │   ├── ai-code-review-patterns.md
│   │   └── effective-prompt-engineering.md
│   ├── src/
│   │   ├── model/Course.java, Enrollment.java, Student.java
│   │   ├── service/StudentService.java
│   │   └── StudentManagementApp.java
│   └── README.md
├── daily-tasks/day-12-assessment-1/
│   ├── deliverables/
│   │   ├── assessment-1-rubric.md
│   │   └── warm-up-quiz-answers.md
│   ├── notes/
│   │   ├── assessment-brief.md
│   │   └── core-java-synthesis.md
│   ├── src/
│   │   ├── model/
│   │   ├── service/
│   │   └── AssessmentApp.java
│   └── README.md
├── daily-tasks/day-13-functional-programming/
│   ├── deliverables/
│   │   └── lambda-refactoring-guide.md
│   ├── notes/
│   │   ├── functional-interfaces-deep-dive.md
│   │   ├── lambda-internals-and-scopes.md
│   │   └── method-references-mastery.md
│   ├── src/
│   │   ├── model/Product.java, Category.java
│   │   ├── service/ProductFilterEngine.java
│   │   └── FunctionalProductApp.java
│   └── README.md
├── daily-tasks/day-14-streams-api/
│   ├── notes/
│   │   ├── collectors-cheatsheet.md
│   │   ├── parallel-streams-and-performance.md
│   │   └── stream-pipeline-internals.md
│   ├── src/
│   │   ├── generator/SalesDataGenerator.java
│   │   ├── model/Order.java, OrderItem.java, OrderStatus.java
│   │   ├── SalesAnalytics.java
│   │   └── SalesAnalyticsBenchmark.java
│   └── README.md
├── daily-tasks/day-15-optional-date-time/
│   ├── notes/
│   │   ├── java-time-api-guide.md
│   │   └── optional-best-practices.md
│   ├── src/
│   │   ├── exception/SubscriptionNotFoundException.java
│   │   ├── model/Customer.java, Subscription.java, SubscriptionTier.java
│   │   ├── service/SubscriptionCalculatorService.java, SubscriptionLookupService.java
│   │   └── SubscriptionCalculator.java
│   └── README.md
├── daily-tasks/day-16-concurrency-essentials/
│   ├── deliverables/
│   │   └── timing-comparison.md
│   ├── notes/
│   │   ├── completable-future-guide.md
│   │   └── executors-and-thread-pools.md
│   ├── src/
│   │   ├── model/SupplierQuote.java
│   │   ├── service/CompletablePriceAggregator.java, ExecutorPriceAggregator.java, SequentialAggregator.java
│   │   ├── supplier/SlowSupplier.java, SupplierClient.java
│   │   └── PriceAggregator.java
│   └── README.md
├── daily-tasks/day-17-java-9-21-features/
│   ├── deliverables/
│   │   └── modern-features-breakdown.md
│   ├── notes/
│   │   ├── modern-java-syntax-updates.md
│   │   └── records-and-sealed-classes.md
│   ├── src/
│   │   ├── collection/ImmutableCollectionLab.java
│   │   ├── input/UserInputCleaner.java
│   │   ├── invoice/Invoice.java, InvoiceItem.java, InvoiceTemplateService.java
│   │   ├── shape/Circle.java, Rectangle.java, Shape.java, ShapeCalculator.java, Triangle.java
│   │   └── ModernJavaLab.java
│   └── README.md
├── daily-tasks/day-18-relational-database-concepts/
│   ├── deliverables/
│   │   ├── normalization-worksheet.md
│   │   └── project-er-diagram.md
│   ├── notes/
│   │   ├── database-normalization-1nf-2nf-3nf.md
│   │   └── rdbms-vs-nosql-tradeoffs.md
│   ├── src/
│   │   └── NormalizationSimulator.java
│   └── README.md
├── daily-tasks/day-19-crud-operations/
│   ├── deliverables/
│   │   └── query-execution-results.md
│   ├── notes/
│   │   ├── ddl-vs-dml-fundamentals.md
│   │   └── safe-updates-and-filtering-guide.md
│   ├── sql/
│   │   ├── project-schema.sql, project-seed.sql
│   │   ├── queries.sql, schema.sql, seed.sql
│   ├── src/
│   │   └── DatabaseRunner.java
│   ├── test_runner.py
│   └── README.md
└── README.md```

## 🔁 Git Workflow
```bash
git status
git add .
git commit -m "update tasks"
git push origin main
```
