# Day 18 Deliverable: Project Entity-Relationship (ER) Diagram

## 1. Relational Architecture Diagram
The following Entity-Relationship diagram depicts the complete normalized (3NF) college registration and academic administration system with cardinalities, primary keys `[PK]`, and foreign keys `[FK]`.

```mermaid
erDiagram
    DEPARTMENTS ||--o{ STUDENTS : "admits / majors"
    DEPARTMENTS ||--o{ FACULTY : "employs"
    DEPARTMENTS ||--o{ COURSES : "offers"
    
    FACULTY ||--o{ COURSES : "teaches"
    
    STUDENTS ||--o{ REGISTRATIONS : "enrolls in"
    COURSES ||--o{ REGISTRATIONS : "is enrolled by"
    
    REGISTRATIONS ||--o{ FEE_PAYMENTS : "settles fees via"

    DEPARTMENTS {
        VARCHAR dept_id PK "e.g. CS, EE, ME"
        VARCHAR dept_name "Department title"
        VARCHAR building "Campus location"
    }

    FACULTY {
        VARCHAR faculty_id PK "Unique faculty ID"
        VARCHAR faculty_name "Full instructor name"
        VARCHAR email UK "Official email"
        VARCHAR office_room "Office room number"
        VARCHAR dept_id FK "Affiliated department"
    }

    STUDENTS {
        VARCHAR student_id PK "Unique matriculation number"
        VARCHAR full_name "Student legal name"
        VARCHAR email UK "Student email"
        VARCHAR major_dept_id FK "Major department"
    }

    COURSES {
        VARCHAR course_code PK "Catalog code e.g. CS101"
        VARCHAR course_title "Course name"
        INTEGER credits "Credit hours (1-5)"
        DECIMAL standard_fee "Tuition fee per course"
        VARCHAR dept_id FK "Offering department"
        VARCHAR instructor_id FK "Assigned faculty"
    }

    REGISTRATIONS {
        VARCHAR registration_id PK "Unique enrollment token"
        VARCHAR student_id FK "Enrolled student"
        VARCHAR course_code FK "Selected course"
        VARCHAR semester "FALL, SPRING, SUMMER"
        INTEGER academic_year "e.g. 2026"
        DATE reg_date "Date of enrollment"
        VARCHAR grade "Letter grade (A-F, IP)"
    }

    FEE_PAYMENTS {
        VARCHAR payment_id PK "Unique payment transaction"
        VARCHAR registration_id FK "Target enrollment"
        DECIMAL amount_paid "Transaction amount"
        TIMESTAMP payment_date "Payment timestamp"
        VARCHAR payment_mode "UPI, CARD, NETBANKING"
        VARCHAR status "SUCCESS, PENDING, FAILED"
    }
```

---

## 2. Cardinality & Relationship Breakdown
| Relationship | Type | Cardinality | Enforced Constraints |
| :--- | :--- | :--- | :--- |
| `DEPARTMENTS` to `STUDENTS` | 1 to Many (1:N) | One department majors zero or many students. Each student must declare exactly one major department. | `FOREIGN KEY (major_dept_id) REFERENCES DEPARTMENTS(dept_id)` |
| `DEPARTMENTS` to `FACULTY` | 1 to Many (1:N) | One department employs one or many faculty members. | `FOREIGN KEY (dept_id) REFERENCES DEPARTMENTS(dept_id) ON DELETE RESTRICT` |
| `FACULTY` to `COURSES` | 1 to Many (1:N) | A faculty member instructs zero, one, or multiple courses. | `FOREIGN KEY (instructor_id) REFERENCES FACULTY(faculty_id) ON DELETE SET NULL` |
| `STUDENTS` to `COURSES` | Many to Many (M:N) | A student registers for multiple courses; a course hosts multiple students. | Resolved via junction table `REGISTRATIONS` with composite unique constraint `(student_id, course_code, semester, academic_year)`. |
| `REGISTRATIONS` to `FEE_PAYMENTS` | 1 to Many (1:N) | Each registration generates one or more installment fee payments. | `FOREIGN KEY (registration_id) REFERENCES REGISTRATIONS(registration_id) ON DELETE CASCADE` |
