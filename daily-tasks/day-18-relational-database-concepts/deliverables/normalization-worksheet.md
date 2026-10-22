# Day 18 Deliverable: Normalization Workshop & Worksheet

## 1. Context & Business Domain
The objective of this workshop is to take an unnormalized, flat spreadsheet representation of **college course registrations** (`college_registrations`) and systematically decompose it through **1NF**, **2NF**, and **3NF** to eliminate data redundancy and insertion, update, and deletion anomalies.

---

## 2. Unnormalized Form (0NF / Flat Spreadsheet)

### Schema Definition
```text
college_registrations (
    student_id, student_name, student_email, major,
    registered_courses, faculty_details, course_fee,
    payment_status, registration_date
)
```

### Raw Spreadsheet Data Example
| student_id | student_name | student_email | major | registered_courses | faculty_details | course_fee | payment_status | registration_date |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| S101 | Alice Walker | alice@univ.edu | CS | CS101: Intro to CS (3 cr), CS201: Data Structures (4 cr) | Dr. Alan Turing (Off: B101, CS Dept), Dr. Donald Knuth (Off: B105, CS Dept) | $1500, $2000 | PAID, PAID | 2026-08-15 |
| S102 | Bob Miller | bob@univ.edu | EE | EE101: Circuits (3 cr) | Dr. Nikola Tesla (Off: E202, EE Dept) | $1500 | PENDING | 2026-08-16 |
| S103 | Carol Davis | carol@univ.edu | CS | CS101: Intro to CS (3 cr) | Dr. Alan Turing (Off: B101, CS Dept) | $1500 | PAID | 2026-08-17 |

---

## 3. First Normal Form (1NF)

### 1NF Criteria
1. Every column must contain **atomic (indivisible)** values.
2. No repeating groups or comma-separated arrays within a single attribute.
3. Each record must have a unique identity (Primary Key defined).

### Decomposition to 1NF
We flatten the multi-valued course and faculty entries into atomic rows and establish the composite Primary Key: **`(student_id, course_code)`**.

| student_id [PK] | course_code [PK] | student_name | student_email | major | course_name | credits | faculty_id | faculty_name | faculty_office | department_name | fee_amount | payment_status | reg_date |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| S101 | CS101 | Alice Walker | alice@univ.edu | CS | Intro to CS | 3 | F301 | Dr. Alan Turing | B101 | Computer Science | 1500 | PAID | 2026-08-15 |
| S101 | CS201 | Alice Walker | alice@univ.edu | CS | Data Structures | 4 | F302 | Dr. Donald Knuth | B105 | Computer Science | 2000 | PAID | 2026-08-15 |
| S102 | EE101 | Bob Miller | bob@univ.edu | EE | Circuits | 3 | F401 | Dr. Nikola Tesla | E202 | Electrical Eng | 1500 | PENDING | 2026-08-16 |
| S103 | CS101 | Carol Davis | carol@univ.edu | CS | Intro to CS | 3 | F301 | Dr. Alan Turing | B101 | Computer Science | 1500 | PAID | 2026-08-17 |

### Anomalies Present in 1NF
- **Insertion Anomaly:** We cannot add a new course (e.g. `CS301: Cloud Computing`) or a new faculty member until at least one student registers for it, because `student_id` is part of the composite primary key and cannot be `NULL` (Entity Integrity Constraint).
- **Deletion Anomaly:** If `Bob Miller (S102)` drops `EE101: Circuits`, row 3 is deleted. As a result, all institutional records of course `EE101`, Dr. Nikola Tesla, and office `E202` are wiped out!
- **Update Anomaly:** Dr. Alan Turing's office (`B101`) is duplicated across multiple rows. If he moves to `B205`, every matching row must be updated. Missing even one row creates contradictory data.

---

## 4. Second Normal Form (2NF)

### 2NF Criteria
1. The table must already be in **1NF**.
2. **No Partial Functional Dependencies:** Every non-prime attribute must be fully functionally dependent on the *entire* candidate primary key, not a proper subset of it.

### Functional Dependency Analysis in 1NF
Candidate Key: `(student_id, course_code)`
- `student_id -> student_name, student_email, major` *(Partial Dependency on part of PK)*
- `course_code -> course_name, credits, faculty_id, faculty_name, faculty_office, department_name, fee_amount` *(Partial Dependency on part of PK)*
- `(student_id, course_code) -> payment_status, reg_date` *(Full Functional Dependency)*

### Decomposition to 2NF
We split into three tables to remove partial dependencies:

#### Table 1: `students`
- **Primary Key:** `student_id`
- **Attributes:** `student_id [PK], student_name, student_email, major`

#### Table 2: `courses`
- **Primary Key:** `course_code`
- **Attributes:** `course_code [PK], course_name, credits, faculty_id, faculty_name, faculty_office, department_name, fee_amount`

#### Table 3: `course_registrations` (Junction Table)
- **Primary Key:** `(student_id, course_code)`
- **Foreign Keys:** `student_id REFERENCES students(student_id)`, `course_code REFERENCES courses(course_code)`
- **Attributes:** `student_id [PK, FK], course_code [PK, FK], reg_date, payment_status`

---

## 5. Third Normal Form (3NF)

### 3NF Criteria
1. The relations must already be in **2NF**.
2. **No Transitive Dependencies:** No non-prime attribute may depend on another non-prime attribute ($X \to Y \to Z$). Every non-prime attribute must depend *directly and only* on a candidate key.

### Identifying Transitive Dependencies in 2NF `courses`
In the 2NF `courses` table:
$$\text{course\_code [PK]} \to \text{faculty\_id}$$
$$\text{faculty\_id} \to \text{faculty\_name, faculty\_office, department\_name}$$
Here, `faculty_name`, `faculty_office`, and `department_name` depend on `faculty_id`, which is a non-prime attribute! This is a classic **transitive dependency**.
Furthermore:
$$\text{faculty\_id} \to \text{department\_id} \to \text{department\_name, office\_building}$$

### Decomposition to 3NF Relations

#### 1. `departments`
- **Primary Key:** `dept_id`
- **Columns:** `dept_id (VARCHAR(10)) [PK], dept_name (VARCHAR(100)), building (VARCHAR(50))`
- **Justification:** Centralizes department metadata.

#### 2. `faculty`
- **Primary Key:** `faculty_id`
- **Foreign Key:** `dept_id REFERENCES departments(dept_id)`
- **Columns:** `faculty_id (VARCHAR(10)) [PK], faculty_name (VARCHAR(100)), email (VARCHAR(120)), office_room (VARCHAR(20)), dept_id [FK]`
- **Justification:** Eliminates duplicate faculty updates; faculty exist independently of whether courses are actively scheduled.

#### 3. `students`
- **Primary Key:** `student_id`
- **Foreign Key:** `major_dept_id REFERENCES departments(dept_id)`
- **Columns:** `student_id (VARCHAR(10)) [PK], full_name (VARCHAR(100)), email (VARCHAR(120)), major_dept_id [FK]`
- **Justification:** Preserves pure student demographic details.

#### 4. `courses`
- **Primary Key:** `course_code`
- **Foreign Keys:** `dept_id REFERENCES departments(dept_id)`, `instructor_id REFERENCES faculty(faculty_id)`
- **Columns:** `course_code (VARCHAR(10)) [PK], course_title (VARCHAR(150)), credits (INT), standard_fee (DECIMAL(10,2)), dept_id [FK], instructor_id [FK]`
- **Justification:** Decouples faculty profile and departmental attributes from individual courses.

#### 5. `registrations` (M:N Junction Table)
- **Primary Key:** `registration_id (VARCHAR(15)) [PK]` or Composite `(student_id, course_code, semester, year) [PK]`
- **Foreign Keys:** `student_id REFERENCES students(student_id)`, `course_code REFERENCES courses(course_code)`
- **Columns:** `registration_id [PK], student_id [FK], course_code [FK], semester (VARCHAR(10)), academic_year (INT), reg_date (DATE), grade (VARCHAR(2))`
- **Justification:** Resolves Many-to-Many student-to-course relationship with enrollment history and historical grades.

#### 6. `fee_payments`
- **Primary Key:** `payment_id (VARCHAR(20)) [PK]`
- **Foreign Key:** `registration_id REFERENCES registrations(registration_id)`
- **Columns:** `payment_id [PK], registration_id [FK], amount_paid (DECIMAL(10,2)), payment_date (TIMESTAMP), payment_mode (VARCHAR(20)), status (VARCHAR(20))`
- **Justification:** Normalizes transaction and payment tracking, allowing multiple partial installment payments per registration.

---

## 6. Verification of Anomaly Elimination in 3NF
| Anomaly Type | 1NF Behavior | 3NF Resolution |
| :--- | :--- | :--- |
| **Insertion** | Cannot add course or faculty without a student enrollment. | Courses and faculty are inserted directly into `courses` and `faculty` tables with zero dependencies on student enrollment. |
| **Deletion** | Deleting a student registration deleted course and faculty records. | Deleting a record from `registrations` leaves `students`, `courses`, and `faculty` tables completely intact. |
| **Update** | Changing faculty office required updating $N$ student registration rows. | Updating faculty office requires exactly **1 update query** on the `faculty` table (`UPDATE faculty SET office_room = 'B205' WHERE faculty_id = 'F301'`). |

---

## 7. Key Justification Matrix
| Table | Candidate Keys | Primary Key | Foreign Keys & Target | Justification |
| :--- | :--- | :--- | :--- | :--- |
| `departments` | `dept_id`, `dept_name` | `dept_id` | None | Compact alphanumeric code (e.g. `CS`, `EE`) |
| `faculty` | `faculty_id`, `email` | `faculty_id` | `dept_id -> departments(dept_id)` | Immutable employee identifier |
| `students` | `student_id`, `email` | `student_id` | `major_dept_id -> departments(dept_id)` | Institutional matriculation number |
| `courses` | `course_code` | `course_code` | `instructor_id -> faculty(faculty_id)`, `dept_id -> departments(dept_id)` | Universal catalog code (e.g. `CS101`) |
| `registrations` | `registration_id`, `(student_id, course_code, semester, academic_year)` | `registration_id` | `student_id -> students(student_id)`, `course_code -> courses(course_code)` | Surrogate PK + composite unique business key for M:N junction |
| `fee_payments` | `payment_id` | `payment_id` | `registration_id -> registrations(registration_id)` | Unique financial audit reference |
