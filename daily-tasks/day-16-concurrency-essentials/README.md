# 📅 Day 16: Concurrency Essentials

## 📋 Task Overview
- **Topic:** `Concurrency Essentials`
- **Module / Sprint:** Week 3, Sprint 2.2
- **Daily Hands-on Task:** Price Aggregator: fetch prices from 3 simulated slow suppliers (sleep 1-2 s) sequentially, then with `ExecutorService` + `Callable`, then with `CompletableFuture.allOf`; handle one supplier failing with `exceptionally`; compare timings.
- **Deliverable:** `PriceAggregator + timing comparison`
- **Git Commit:** `day-16: concurrency-essentials`
- **Done When Criteria:** `Async version faster, failure handled without crashing`

---

## 🏗️ Architecture & Project Structure
```text
day-16-concurrency-essentials/
├── deliverables/
│   └── timing-comparison.md            # Benchmark report and mathematical speedup analysis
├── notes/
│   ├── completable-future-guide.md     # supplyAsync, thenApply, allOf, exceptionally
│   └── executors-and-thread-pools.md   # Thread pools, sizing, I/O vs CPU concurrency
├── src/
│   ├── model/
│   │   └── SupplierQuote.java          # Immutable quote result or error fallback
│   ├── service/
│   │   ├── CompletablePriceAggregator.java # CompletableFuture.allOf & exceptionally
│   │   ├── ExecutorPriceAggregator.java    # ExecutorService + Callable
│   │   └── SequentialAggregator.java       # Sequential baseline
│   ├── supplier/
│   │   ├── SlowSupplier.java           # Simulated 1-2s delay supplier with fault injection
│   │   └── SupplierClient.java         # Functional interface
│   └── PriceAggregator.java            # Main runner, benchmark comparison & verification
└── README.md
```

---

## 🚀 Key Results
- **Sequential Baseline:** ~4,510 ms (sum of latencies)
- **ExecutorService:** ~1,815 ms (**2.48x speedup**)
- **CompletableFuture:** ~1,812 ms (**2.49x speedup**)
- **Fault Resilience:** Handled simulated 503 gateway crash smoothly using `.exceptionally()` fallback without halting other tasks or crashing the application.

---

## ⚙️ Compilation and Execution
```bash
# Navigate to directory
cd "C:\Program Files\Java\jdk-21\bin\hcl\HCL-daily-tasks\HCL-Daily-Tasks\daily-tasks\day-16-concurrency-essentials\src"

# Compile all classes
javac -d ../bin model/*.java supplier/*.java service/*.java PriceAggregator.java

# Run PriceAggregator benchmark
java -cp ../bin PriceAggregator
```
