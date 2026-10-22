# AI-USAGE.md: AI Integration, Prompt Engineering & Code Review Drill

**Module:** Core Java Engineering Track  
**Day:** 11  
**Sprint:** Week 2 / Sprint 1 / Module 1  
**Topic:** AI Integration & Critical Code Review  
**Git Commit:** `day-11: ai-integration-core-java`  

---

## 1. Prompt Engineering: Vague vs Specific Prompts

### Prompt A (Vague Prompt)
> *"Write a Student class and a StudentService in Java with courses and lookup."*

#### AI Code Issues Generated:
1. Public mutable fields without bounds validation (e.g. `gpa` could be set to negative).
2. Returned direct internal references to `List<String> enrolledCourses`, enabling callers to alter or clear student records directly.
3. Used naive `List<Student>` with linear $O(N)$ searches for lookups by ID.
4. Omitted `equals()` and `hashCode()`, causing duplicate instances in `HashSet` and `HashMap`.
5. Empty catch blocks (`catch (Exception e) {}`) swallowing critical system faults.

### Prompt B (Specific Grounded Prompt)
> *"Generate a production-ready Java 21 Student domain entity and StudentService interface with implementation. Enforce strict encapsulation: private final fields where applicable, defensive unmodifiable collections on getters, constructor null validation via Objects.requireNonNull, robust equals/hashCode based on studentId, and O(1) Map-indexed lookups with meaningful domain exception propagation."*

#### Result:
Generated code adheres to clean architecture, prevents memory corruption, maintains $O(1)$ algorithmic complexity, and preserves encapsulation.

---

## 2. Review Checklist for AI-Generated Code

Always audit AI code against this 6-point checklist:
1. **Encapsulation & Immutability:** Are internal collections defensively copied (`Collections.unmodifiableList`)?
2. **Contract Compliance:** Are `equals()` and `hashCode()` consistently overridden based on business identity?
3. **Null Safety:** Are input arguments guarded with `Objects.requireNonNull()` or validated with preconditions?
4. **Data Structure Choice:** Is the collection optimal ($O(1)$ Map lookup vs $O(N)$ linear scans)?
5. **Exception Hygiene:** Are exceptions propagated or logged rather than silently swallowed?
6. **Thread & Memory Safety:** Are static shared caches bounded to prevent memory leaks?

---

## 3. Review Table: >= 5 Genuine AI Issues Found & Fixed

| Issue # | Vulnerability / Flaw | AI-Generated Code (Before) | Hardened Code (After) | Engineering Impact |
| :---: | :--- | :--- | :--- | :--- |
| **1** | **Encapsulation Breach (Mutable Internal List)** | `public List<String> getCourses() { return this.courses; }` | `public List<String> getEnrolledCourses() { return Collections.unmodifiableList(enrolledCourses); }` | Prevents malicious or accidental modification of student records outside the service layer. |
| **2** | **Missing equals/hashCode Contract** | Inherited from `java.lang.Object` (compares JVM heap memory address) | Overrode both using `Objects.hash(studentId)` and `studentId.equals(...)` | Ensures identical student IDs resolve to the same key in `HashMap` and prevent duplicate `Set` entries. |
| **3** | **Unvalidated Field Bounds (Illegal GPA)** | `public void setGpa(double gpa) { this.gpa = gpa; }` | `if (gpa < 0.0 \|\| gpa > 4.0) throw new IllegalArgumentException(...);` | Prevents invalid business state from corrupting downstream grading calculations. |
| **4** | **Suboptimal Data Structure ($O(N)$ vs $O(1)$)** | Stored in `List<Student>`; looped through entire list for `findById` | Stored in `Map<String, Student>` keyed by `studentId` | Replaces $O(N)$ linear scans with instant $O(1)$ amortized lookups. |
| **5** | **Swallowed Exceptions (Silent Failure)** | `try { ... } catch (Exception e) { return null; }` | Propagates specific exceptions: `NoSuchElementException`, `IllegalStateException` | Eliminates silent bugs; surfaces clear failure reasons to callers and API consumers. |

---

## 4. Project Task Implementation (FR7 & FR8)

- **FR7 (Course Enrollment):**
  - Implemented `enrollStudentInCourse(studentId, courseCode)`.
  - Validates that both Student and Course exist.
  - Prevents duplicate enrollments using student enrollment records.
  - Generates unique tracking ID (`ENR-1001`).
- **FR8 (Official Academic Transcript Generation):**
  - Implemented `generateAcademicTranscript(studentId)`.
  - Aggregates enrolled courses, titles, credit weights, and course completion status.
  - Computes total registered credits and formats an official ASCII transcript.
