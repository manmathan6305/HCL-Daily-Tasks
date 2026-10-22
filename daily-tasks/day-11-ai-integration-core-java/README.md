# Day 11: AI Integration (Core Java) & Critical Code Review

**Status:** Completed  
**Progress:** 100%  
**Week:** 2 | **Sprint:** 1 | **Module:** 1  
**Deliverable:** Review table + `AI-USAGE.md` (>= 5 entries)  
**Git Commit:** `day-11: ai-integration-core-java`  

---

## Learning Objectives
By the end of this day, you will be able to:
- Effectively leverage AI tools (GitHub Copilot, LLMs) to generate Java boilerplate while preventing subtle architectural flaws.
- Formulate specific, grounded prompts that enforce encapsulation, immutability, and thread safety.
- Apply a systematic 6-point code review checklist to AI-generated code.
- Identify and remediate at least 5 common AI anti-patterns: encapsulation leaks, missing equals/hashCode, unvalidated state, suboptimal collections ($O(N)$ vs $O(1)$), and swallowed exceptions.
- Implement project functional requirements **FR7 (Course Enrollment)** and **FR8 (Academic Transcript Generation)**.

---

## Main Points Summary

| Concept | Description | Key Details |
| :--- | :--- | :--- |
| **Prompt Specificity** | Guiding AI outputs | Vague prompts produce unvalidated, leaky code. Grounded prompts specify constraints, immutability, and contracts. |
| **Encapsulation Leaks** | Exposing internal state | Returning mutable collections allows external modification. Fixed via `Collections.unmodifiableList()`. |
| **Contract Hygiene** | `equals()` and `hashCode()` | Missing implementations cause subtle bugs in `Set` and `Map`. Must be strictly based on business identity. |
| **Collection Choice** | Algorithmic efficiency | AI often defaults to `ArrayList` searches ($O(N)$). Fixed by indexing via `HashMap` ($O(1)$). |
| **Exception Hygiene** | Eliminating silent failures | Replacing `catch (Exception e) {}` with meaningful domain exceptions (`NoSuchElementException`). |
| **FR7 & FR8** | Academic Project Features | Course enrollment with duplicate prevention, credit calculation, and official transcript generation. |

---

## Estimated Study Time
Total: approximately 3 hours 30 minutes.

### Suggested Schedule
- **Session 1:** Prompt engineering fundamentals & Vague vs Specific comparison — 45 minutes.
- **Session 2:** The 6-point AI code review checklist & identifying the 5 bugs — 60 minutes.
- **Session 3:** Hardening Student entity, StudentService, and implementing FR7/FR8 — 60 minutes.
- **Session 4:** Compiling, testing, documenting `AI-USAGE.md`, and review — 45 minutes.

---

## Project Structure & Deliverables

```text
day-11-ai-integration-core-java/
├── deliverables/
│   └── AI-USAGE.md                 # Review table with >= 5 genuine AI issues found & fixed
├── notes/
│   ├── ai-code-review-checklist.md # 6-point security & OOP audit checklist
│   └── prompt-engineering-for-java.md
├── src/
│   ├── demo/
│   │   └── AiReviewDrillDemo.java  # Main driver running prompt drill & FR7/FR8
│   ├── model/
│   │   ├── Course.java
│   │   ├── Enrollment.java
│   │   └── Student.java
│   └── service/
│       ├── StudentService.java
│       └── StudentServiceImpl.java
└── README.md
```

---

## Setup and Run Instructions

1. Navigate to the `day-11-ai-integration-core-java` directory:
   ```bash
   cd daily-tasks/day-11-ai-integration-core-java
   ```
2. Compile all source files:
   ```bash
   javac src/model/*.java src/service/*.java src/demo/*.java
   ```
3. Run the demonstration application:
   ```bash
   java -cp src demo.AiReviewDrillDemo
   ```
