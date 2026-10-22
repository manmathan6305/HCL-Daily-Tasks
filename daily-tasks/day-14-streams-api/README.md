# Day 14: Streams API & Enterprise Sales Analytics

**Status:** Completed  
**Progress:** 100%  
**Week:** 3 | **Sprint:** 2 | **Module:** 2  
**Deliverable:** `SalesAnalytics.java` + Refactored Reports  
**Git Commit:** `day-14: streams-api`  
**Done When:** Every report uses Collectors; zero external mutation inside streams.  

---

## Learning Objectives
By the end of this day, you will be able to:
- Construct declarative, non-mutating data transformation pipelines using the **Java Streams API**.
- Exploit intermediate lazy operations (`filter`, `map`, `distinct`, `sorted`, `limit`).
- Master downstream reduction patterns with the `Collectors` API (`groupingBy`, `partitioningBy`, `summingDouble`, `summarizingDouble`, `joining`).
- Conduct empirical performance benchmarks comparing **Sequential vs Parallel Streams** on CPU-intensive workloads.
- Understand the internal mechanics of the `ForkJoinPool.commonPool()` and identify scenarios where parallel streams help or hurt.

---

## Main Points Summary

| Concept | Description | Key Details |
| :--- | :--- | :--- |
| **Stream Lifecycle** | Source -> Intermediate -> Terminal | Intermediate steps are lazily evaluated. Terminal operations trigger single-pass processing. |
| **`groupingBy`** | SQL GROUP BY equivalent | Maps keys to grouped collections or downstream statistical reductions (`summingDouble`). |
| **`partitioningBy`** | Boolean bifurcation | Splits a stream into two lists (`true` and `false`) in a single traversal pass. |
| **`summarizingDouble`** | Multi-metric statistical aggregator | Simultaneously computes count, sum, min, average, and max. |
| **`joining`** | String accumulator | Concatenates string elements with custom delimiters, prefixes, and suffixes. |
| **Parallel Streams** | Multi-threaded pipelining | Uses `ForkJoinPool.commonPool()`. Highly effective for CPU-bound tasks on array-backed sources. |

---

## Estimated Study Time
Total: approximately 3 hours 30 minutes.

### Suggested Schedule
- **Session 1:** Streams fundamentals, lifecycle, and laziness — 45 minutes.
- **Session 2:** The Collectors API deep dive (groupingBy, partitioningBy) — 60 minutes.
- **Session 3:** Building `SalesAnalytics.java` on 1,000 order transactions — 60 minutes.
- **Session 4:** Sequential vs Parallel benchmark, review, and documentation — 45 minutes.

---

## Project Structure & Deliverables

```text
day-14-streams-api/
├── notes/
│   ├── collectors-api-deep-dive.md
│   ├── parallel-streams-and-performance.md
│   └── stream-pipelines-and-laziness.md
├── src/
│   ├── generator/
│   │   └── SalesOrderGenerator.java # Deterministic generator of 1,000 orders
│   ├── model/
│   │   └── Order.java               # Transaction domain entity
│   └── SalesAnalytics.java          # Primary Deliverable
└── README.md
```

---

## Setup and Run Instructions

1. Navigate to the `day-14-streams-api` directory:
   ```bash
   cd daily-tasks/day-14-streams-api
   ```
2. Compile all source files:
   ```bash
   javac src/model/*.java src/generator/*.java src/SalesAnalytics.java
   ```
3. Run the application:
   ```bash
   java -cp src SalesAnalytics
   ```
