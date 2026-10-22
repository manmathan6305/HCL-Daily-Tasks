# Java Executors & Thread Pools Deep Dive

## 1. Threads vs Executors
Directly instantiating `new Thread(runnable).start()` is an anti-pattern in production:
- High thread creation cost (allocates ~1MB stack per thread).
- No control over total active threads (leads to OutOfMemoryError).
- Thread dies after task ends; cannot be reused.

`ExecutorService` manages a reusable worker thread pool with an internal work queue (`BlockingQueue`).

---

## 2. Thread Pool Flavors in `Executors`
| Pool Type | When to Use | Caution |
| :--- | :--- | :--- |
| `Executors.newFixedThreadPool(n)` | Predictable CPU/IO bound workloads with bounded concurrency | WorkQueue can grow unboundedly if tasks arrive faster than consumed |
| `Executors.newCachedThreadPool()` | Short-lived asynchronous tasks | Can spawn unbounded threads under sudden traffic spikes |
| `Executors.newSingleThreadExecutor()` | Sequential FIFO processing | Single point of bottleneck |
| `Executors.newVirtualThreadPerTaskExecutor()` (Java 21) | High-concurrency I/O-bound microservices | Not suitable for long CPU-bound computations |

---

## 3. When Async Helps (and When It Doesn't)
- **High Gain (I/O Bound):** Database queries, remote REST calls, file system reads, message broker writes. Threads wait for sockets, so pooling or async frees CPU cores to do other work.
- **Low/Negative Gain (CPU Bound):** Array sorting, crypto hashing, image processing. If you have 8 CPU cores, running 100 CPU-intensive threads creates severe context switching overhead and runs *slower* than 8 threads.
