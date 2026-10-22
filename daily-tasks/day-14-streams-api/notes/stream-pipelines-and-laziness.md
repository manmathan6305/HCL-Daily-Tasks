# Java Streams Pipeline Architecture & Laziness

## 1. Stream Core Principles
A Stream is a sequence of elements supporting sequential and parallel aggregate operations.
- **Streams Do Not Store Data:** Unlike collections, streams are pipelines that compute data on demand.
- **Functional Purity:** Operations produce a result without mutating their data source.
- **Laziness:** Intermediate operations are not evaluated until a terminal operation is invoked.
- **Single-Use:** A stream pipeline cannot be reused once consumed by a terminal operation.

---

## 2. Pipeline Anatomy

```text
[ Data Source ] -> [ Intermediate Operations (Lazy) ] -> [ Terminal Operation (Eager) ]
(List, Set, Map)      filter(), map(), sorted()              collect(), reduce(), count()
```

### Intermediate Operations (Stateless vs Stateful)
- **Stateless:** `filter()`, `map()`, `flatMap()`, `peek()`. Each element is processed independently.
- **Stateful:** `sorted()`, `distinct()`, `limit()`, `skip()`. Requires buffering prior elements before proceeding.

### Terminal Operations
- `collect()`: Accumulates elements into a container (`List`, `Map`, `String`).
- `reduce()`: Combines elements into a single summary value.
- `anyMatch()`, `allMatch()`, `noneMatch()`: Short-circuiting boolean evaluations.
- `forEach()`: Consumes elements with side effects.
