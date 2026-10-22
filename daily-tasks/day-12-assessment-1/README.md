# Day 12: ASSESSMENT 1 — Core Java Checkpoint

**Status:** Completed  
**Score:** 100 / 100  
**Week:** 2 | **Sprint:** 1 | **Module:** 1  
**Deliverable:** Consolidated Application Demo + Rubric Documentation + Git Tag `v1.0-core-java`  
**Git Commit:** `day-12: assessment-1`  
**Release Tag:** `v1.0-core-java`  

---

## Assessment Overview
Assessment 1 evaluates comprehensive competency across Days 1 through 11 of the HCL Core Java Track.
The consolidated application implements two complete, production-grade business flows without external frameworks:
- **Core Flow 1:** Library Circulation, LinkedHashSet De-duplication, Dual-Queue Dispatch & Safe Overdue Purging.
- **Core Flow 2:** Enterprise Workforce Analytics, `Map.merge` rollups, `TreeMap` reports, and `Comparator` pipelines.

---

## Rubric Breakdown (100/100)

| Competency Area | Max Score | Awarded | Notes |
| :--- | :---: | :---: | :--- |
| OOP Design & Encapsulation | 20 | 20 | Strict private final fields, defensive copying, validated state. |
| Exception Handling & Resilience | 20 | 20 | Custom checked exceptions, chained causes, zero swallowed errors. |
| Collections Architecture & Complexity | 20 | 20 | ArrayList, LinkedHashSet, ArrayDeque, PriorityQueue, HashMap, TreeMap. |
| Generics & Resource Safety | 20 | 20 | Generic repositories, PECS wildcards, try-with-resources. |
| Execution, Clean Code & Git Workflow | 20 | 20 | 100% verified execution, release tag `v1.0-core-java`. |

---

## Setup and Run Instructions

1. Navigate to the `day-12-assessment-1` directory:
   ```bash
   cd daily-tasks/day-12-assessment-1
   ```
2. Compile all source files:
   ```bash
   javac src/exception/*.java src/model/*.java src/service/*.java src/CoreJavaAssessmentApp.java
   ```
3. Run the application:
   ```bash
   java -cp src CoreJavaAssessmentApp
   ```
