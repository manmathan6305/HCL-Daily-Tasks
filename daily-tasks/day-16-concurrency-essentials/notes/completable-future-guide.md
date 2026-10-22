# CompletableFuture Mastery Guide

## 1. Why `CompletableFuture` Replaced Traditional `Future`
`java.util.concurrent.Future` introduced in Java 5 had severe limitations:
- `get()` is **blocking**; you cannot notify or react when the result becomes available.
- Cannot easily chain multiple asynchronous stages.
- Cannot combine multiple futures without manually polling or blocking.
- No declarative exception handling.

`CompletableFuture<T>` (introduced in Java 8, expanded in Java 9-21) implements both `Future<T>` and `CompletionStage<T>`, providing a full reactive pipeline.

---

## 2. Core Method Categories

### A. Initiation
- `CompletableFuture.supplyAsync(() -> fetchPrice(), executor)`: Asynchronous computation returning a value.
- `CompletableFuture.runAsync(() -> logEvent(), executor)`: Asynchronous computation without return value (`Void`).

### B. Transformation (Chaining)
- `thenApply(Function<T, R>)`: Synchronous transformation on the same thread.
- `thenApplyAsync(Function<T, R>, executor)`: Asynchronous transformation dispatched to executor.

### C. Composition & Combination
- `thenCompose(Function<T, CompletionStage<U>>)`: Like `flatMap`, unfolds nested CompletableFutures.
- `thenCombine(CompletionStage<U>, BiFunction<T, U, V>)`: Waits for two independent futures and merges their results.
- `CompletableFuture.allOf(f1, f2, f3)`: Returns `CompletableFuture<Void>` that completes when **all** input futures finish.
- `CompletableFuture.anyOf(f1, f2, f3)`: Completes as soon as **any one** input future finishes.

### D. Error Handling
- `exceptionally(Function<Throwable, T>)`: Recovers from an exception by providing a fallback value.
- `handle(BiFunction<T, Throwable, R>)`: Intercepts both success result and exception for uniform transformation.
