# Garbage Collection Basics & Tuning Flags

## 1. The Generational Hypothesis
Most allocated objects in Java die young (short-lived). HotSpot exploits this by partitioning the heap into generations:

```text
+-------------------------------------------------------+-----------------------+
|                   Young Generation                    |    Old Generation     |
| +-------------------+---------------+---------------+ |       (Tenured)       |
| |    Eden Space     |  Survivor S0  |  Survivor S1  | |                       |
| +-------------------+---------------+---------------+ |                       |
+-------------------------------------------------------+-----------------------+
```

### Young Generation
- **Eden Space:** Where all new objects are initially allocated.
- **Survivor Spaces (`S0` & `S1`):** Two identical spaces. When Eden fills, a **Minor GC** moves surviving objects into one of the Survivor spaces while incrementing their age counter.
- **Tenuring Threshold:** When an object survives multiple Minor GC cycles (typically 15 by default), it is promoted to the **Old Generation**.

### Old (Tenured) Generation
- Stores long-lived domain objects, singletons, and large caches.
- Collected via **Major GC / Full GC** when capacity runs low. Major GCs involve compaction and produce longer Stop-The-World (STW) pauses.

---

## 2. Essential JVM Memory & GC Flags

| Flag | Purpose | Example |
| :--- | :--- | :--- |
| **`-Xms`** | Sets initial heap size | `java -Xms512m` |
| **`-Xmx`** | Sets maximum heap size | `java -Xmx2g` |
| **`-Xss`** | Sets thread stack size | `java -Xss512k` |
| **`-Xlog:gc`** | Enables modern unified JVM GC logging | `java -Xlog:gc:gc.log` |
| **`-XX:+UseG1GC`** | Enables Garbage-First Collector (default in modern JDK) | `java -XX:+UseG1GC` |
| **`-XX:+HeapDumpOnOutOfMemoryError`** | Generates HPROF dump upon OOM | `java -XX:+HeapDumpOnOutOfMemoryError` |

---

## 3. Interpreting `-Xlog:gc` Output
```text
[0.082s][info][gc] GC(0) Pause Young (Normal) (G1 Evacuation Pause) 24M->4M(256M) 1.842ms
```
- `0.082s` : Time elapsed since application launch.
- `GC(0)` : Event identifier.
- `Pause Young` : Young Generation collection.
- `24M->4M` : Heap usage dropped from 24MB down to 4MB (20MB reclaimed).
- `(256M)` : Current total allocated heap.
- `1.842ms` : Total Stop-The-World pause duration.
