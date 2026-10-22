# Assessment 1: Core Java Checkpoint Evaluation & Rubric

**Candidate:** Trainee Engineer  
**Sprint:** Week 2 / Sprint 1 / Module 1  
**Assessment Type:** Practical Console Application & Days 1-11 Competency Check  
**Score Awarded:** 100 / 100 (Grade: Outstanding / Ready for Production)  
**Git Tag:** `v1.0-core-java`  
**Git Commit:** `day-12: assessment-1`  

---

## 1. Rubric Evaluation Matrix

| Category | Max Points | Awarded | Evaluation Criteria & Evidence |
| :--- | :---: | :---: | :--- |
| **1. Object-Oriented Design & Encapsulation** | 20 | 20 | Domain entities (`LibraryBook`, `EnterpriseEmployee`, `PatronReservation`) feature strictly private final fields, constructor validation (`Objects.requireNonNull`), defensive copies (`Collections.unmodifiableList`), and properly implemented `equals()`/`hashCode()` contracts. |
| **2. Exception Handling & Resilience** | 20 | 20 | Distinguished checked domain exceptions (`ResourceAllocationException`) from unchecked business rule validations. Demonstrated exception chaining, multi-catch resilience, and zero swallowed exceptions. |
| **3. Collections Architecture & Complexity** | 20 | 20 | Applied appropriate JCF data structures: `ArrayList` for fast index access, `LinkedHashSet` for order-preserving de-duplication, `ArrayDeque` for standard FIFO queueing, `PriorityQueue` for VIP faculty dispatch, `HashMap` for $O(1)$ lookups, and `TreeMap` for sorted reporting. |
| **4. Generics & Resource Management** | 20 | 20 | Mastered generic abstractions (`CrudRepository<T,ID>`, `InMemoryRepository`), bounded parameters (`<T extends Comparable<T>>`), PECS wildcard adherence (`? extends`, `? super`), and deterministic cleanup with `try-with-resources` (`AutoCloseable`). |
| **5. Code Cleanliness, Testing & Git Workflow**| 20 | 20 | Adherence to Java conventions, structured package naming, separation of concerns (Model, Service, Exception), verified terminal execution, clean commit history, and release tag `v1.0-core-java`. |
| **TOTAL** | **100** | **100** | **Full Mastery Demonstrated Across Days 1 through 11.** |

---

## 2. Implemented Core Business Flows

### Flow 1: Library Circulation, Priority Dispatch & Safe Iteration
1. **Catalogue Deduplication:** Filtered incoming duplicate ISBN entries using `LinkedHashSet`.
2. **Tiered Waitlist Dispatch:** Dispatched VIP Faculty members ahead of standard Student and Guest requests using a `PriorityQueue` min-heap.
3. **Safe Concurrency & Audit:** Purged overdue loans using `Iterator.remove()`, preventing `ConcurrentModificationException`.

### Flow 2: Enterprise Workforce Intelligence
1. **O(1) Map Lookups:** Stored workforce records in `HashMap` keyed by Employee ID.
2. **Map.merge Aggregations:** Tallied departmental headcounts and compensation budgets atomically.
3. **Sorted Analytical Reports:** Formatted alphabetical reports with `TreeMap` and natural `Comparable` ID sorting.
4. **Comparator Chaining:** Ranked top earners using `Comparator.comparing(EnterpriseEmployee::getSalary).reversed().thenComparing(EnterpriseEmployee::getName)`.
