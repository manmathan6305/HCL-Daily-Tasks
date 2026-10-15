# Day 8: Collections Framework — Part 2

**Status:** Completed  
**Progress:** 100%  
**Week:** 2 | **Sprint:** 1 | **Module:** 1  
**Deliverable:** `EmployeeDirectory.java`  
**Git Commit:** `day-8: collections framework`

---

## Learning Objectives
By the end of this day, you will be able to:
- Understand and exploit the internal architectures of `HashMap`, `LinkedHashMap`, and `TreeMap`.
- Utilize `Map.merge()` for concurrent-friendly rollups, departmental headcounts, and payroll aggregations.
- Formulate sorted analytical reports using `TreeMap`.
- Distinguish between `Comparable` (natural internal order) and `Comparator` (external custom order).
- Construct multi-criteria sorting pipelines using `Comparator.comparing().reversed().thenComparing()`.
- Identify, demonstrate, and fix the classic "Missing-hashCode Bug" to satisfy the `equals()` and `hashCode()` contract.

---

## Main Points Summary

| Concept | Description | Key Details |
| :--- | :--- | :--- |
| **`HashMap` Internals** | Hash-table based associative array | Buckets transition to Red-Black Trees when collision depth $\ge 8$ and table capacity $\ge 64$, ensuring $O(\log N)$ worst-case lookup. |
| **`LinkedHashMap`** | Hash map with doubly-linked bucket entries | Maintains predictable insertion order or access order (ideal for LRU cache implementations). |
| **`TreeMap`** | Red-Black self-balancing tree | Guarantees keys are ordered in natural `Comparable` or explicit `Comparator` sequence ($O(\log N)$ operations). |
| **`Map.merge()`** | Atomic value combination | `map.merge(key, value, BiFunction)` eliminates boilerplate null-checking when tallying frequencies or aggregating sums. |
| **`Comparable` vs `Comparator`**| Ordering contracts | `Comparable` is embedded inside the class (`compareTo`); `Comparator` is a decoupled strategy combinator (`compare`). |
| **Comparator Chaining** | Multi-attribute sorting | `Comparator.comparing(fn).reversed().thenComparing(fn2)` enables fluent, declarative ranking pipelines. |
| **Missing-hashCode Bug** | Broken hash contract | Overriding `equals` without `hashCode` causes identical logical keys to route to different buckets, returning `null` on `map.get()`. |

---

## Estimated Study Time
Total: approximately 3 hours 30 minutes.

### Suggested Schedule
- **Session 1:** `Map` Hierarchy, `HashMap` bucket mechanics & treeification — 45 minutes.
- **Session 2:** `Map.merge()` aggregations & `TreeMap` sorted reporting — 45 minutes.
- **Session 3:** `Comparable` vs `Comparator` chaining & top-5 salary rankings — 60 minutes.
- **Session 4:** The `hashCode()` and `equals()` contract violation & fix demo — 60 minutes.

---

## Topics & Reference Notes

Detailed conceptual guides are provided in the `notes/` directory:
- [Map Internals and Implementations](./notes/map-internals-and-implementations.md)
- [Comparable vs Comparator](./notes/comparable-vs-comparator.md)
- [The hashCode() and equals() Contract](./notes/hashcode-and-equals-contract.md)

---

## Project Structure & Deliverables

```text
day-08-collections-framework-part2/
├── notes/
│   ├── comparable-vs-comparator.md
│   ├── hashcode-and-equals-contract.md
│   └── map-internals-and-implementations.md
├── src/
│   ├── model/
│   │   ├── Department.java
│   │   └── Employee.java
│   ├── service/
│   │   ├── EmployeeDirectoryService.java
│   │   └── MissingHashCodeBugDemo.java
│   └── EmployeeDirectory.java       # Primary deliverable implementing FR4 - FR6
└── README.md
```

---

## Functional Requirements Implemented (FR4 - FR6)

- **FR4 (Fast Lookups & Frequency Rollup):**
  - Instant $O(1)$ lookup by primary key (Employee ID) backed by `HashMap`.
  - Department headcount frequency distribution and total salary rollups calculated via `Map.merge()`.
- **FR5 (Sorted Departmental Analytics):**
  - Generates comprehensive departmental breakdown sorted alphabetically by department using `TreeMap`.
  - Internal department employees sorted naturally by ID (`Comparable<Employee>`).
- **FR6 (Multi-Attribute Comparator Pipelines):**
  - Ranks top 5 highest-paid earners using `Comparator.comparing(Employee::getSalary).reversed().thenComparing(Employee::getName)`.
  - Seniority report sorting by Department ascending, followed by Experience years descending.
- **Bug Demonstration & Fix:**
  - `MissingHashCodeBugDemo.java` proves why omitting `hashCode()` causes `map.get()` to return `null` even when `equals()` returns `true`, and validates the contract fix.

---

## Setup and Run Instructions

1. Navigate to the `day-08-collections-framework-part2` directory:
   ```bash
   cd day-08-collections-framework-part2
   ```
2. Compile all source files:
   ```bash
   javac src/model/*.java src/service/*.java src/EmployeeDirectory.java
   ```
3. Run the application:
   ```bash
   java -cp src EmployeeDirectory
   ```
