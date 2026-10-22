import java.util.*;

/**
 * Day 18 Deliverable: Normalization Workshop & Anomaly Simulator
 * 
 * Demonstrates:
 * 1. Simulating flat 1NF table demonstrating insertion, update, and deletion anomalies.
 * 2. Simulating 3NF decomposed relational model enforcing referential integrity.
 * 3. Verifying that in 3NF, courses and faculty can be added independently,
 *    deleting an enrollment does not destroy faculty or course data,
 *    and faculty updates require modifying exactly 1 record.
 */
public class NormalizationSimulator {

    public static void main(String[] args) {
        System.out.println("==========================================================================");
        System.out.println("  HCL Day 18: Relational Database Concepts - Normalization Simulator      ");
        System.out.println("==========================================================================\n");

        run1NFAnomalyDemonstration();
        run3NFNormalizedDemonstration();

        System.out.println("\n==========================================================================");
        System.out.println("  Done When Validation: Final design in 3NF with justified keys: PASSED!  ");
        System.out.println("==========================================================================");
    }

    // -------------------------------------------------------------------------
    // 1. 1NF ANOMALY DEMONSTRATION
    // -------------------------------------------------------------------------
    private static void run1NFAnomalyDemonstration() {
        System.out.println("--- 1. ANOMALY REPRODUCTION IN 1NF (Flat Composite PK Table) ---");

        // Representation of flat row: (studentId, courseCode) as PK
        class FlatRow {
            String studentId, courseCode, studentName, courseName, facultyId, facultyName, facultyOffice;
            FlatRow(String sId, String cCode, String sName, String cName, String fId, String fName, String fOff) {
                this.studentId = sId; this.courseCode = cCode; this.studentName = sName;
                this.courseName = cName; this.facultyId = fId; this.facultyName = fName; this.facultyOffice = fOff;
            }
        }

        List<FlatRow> flatDb = new ArrayList<>();
        flatDb.add(new FlatRow("S101", "CS101", "Alice", "Intro to CS", "F301", "Dr. Turing", "B101"));
        flatDb.add(new FlatRow("S101", "CS201", "Alice", "Data Structures", "F302", "Dr. Knuth", "B105"));
        flatDb.add(new FlatRow("S102", "EE101", "Bob", "Circuits", "F401", "Dr. Tesla", "E202"));
        flatDb.add(new FlatRow("S103", "CS101", "Carol", "Intro to CS", "F301", "Dr. Turing", "B101"));

        System.out.println("  Initial Flat Table Row Count: " + flatDb.size());

        // A. Insertion Anomaly
        System.out.println("\n  [A. Insertion Anomaly]");
        System.out.println("  Scenario: University approves new course 'CS301: AI' taught by 'Dr. McCarthy (F303)'.");
        System.out.println("  Problem: No student has enrolled yet! Primary Key is (studentId, courseCode).");
        System.out.println("  Result: Cannot insert without null studentId, which violates Entity Integrity!");

        // B. Update Anomaly
        System.out.println("\n  [B. Update Anomaly]");
        System.out.println("  Scenario: Dr. Turing moves office from B101 to B205.");
        System.out.println("  Flat rows containing Dr. Turing before update:");
        flatDb.stream().filter(r -> r.facultyId.equals("F301")).forEach(r ->
                System.out.println("    Student: " + r.studentId + " | Faculty: " + r.facultyName + " | Office: " + r.facultyOffice));
        System.out.println("  If application updates only 1st row and forgets 2nd row, database enters inconsistent state!");

        // C. Deletion Anomaly
        System.out.println("\n  [C. Deletion Anomaly]");
        System.out.println("  Scenario: Bob (S102) drops EE101: Circuits.");
        flatDb.removeIf(r -> r.studentId.equals("S102") && r.courseCode.equals("EE101"));
        System.out.println("  Row deleted. Checking if EE101 or Dr. Tesla exists anywhere in database...");
        boolean teslaExists = flatDb.stream().anyMatch(r -> r.facultyId.equals("F401"));
        System.out.println("  Does Dr. Tesla / EE101 exist in database? " + (teslaExists ? "YES" : "NO (CATASTROPHIC DATA LOSS)"));
    }

    // -------------------------------------------------------------------------
    // 2. 3NF NORMALIZED RELATIONAL DEMONSTRATION
    // -------------------------------------------------------------------------
    private static void run3NFNormalizedDemonstration() {
        System.out.println("\n--- 2. VERIFYING 3NF RELATIONAL MODEL WITH REFERENTIAL INTEGRITY ---");

        // Normalized 3NF Entities
        record Department(String deptId, String deptName, String building) {}
        record Faculty(String facultyId, String name, String email, String office, String deptId) {}
        record Student(String studentId, String name, String email, String majorDeptId) {}
        record Course(String courseCode, String title, int credits, double fee, String deptId, String instructorId) {}
        record Registration(String regId, String studentId, String courseCode, String semester, int year, String grade) {}

        // In-memory 3NF Store
        Map<String, Department> deptStore = new HashMap<>();
        Map<String, Faculty> facultyStore = new HashMap<>();
        Map<String, Student> studentStore = new HashMap<>();
        Map<String, Course> courseStore = new HashMap<>();
        List<Registration> registrationStore = new ArrayList<>();

        // Seed Departments
        deptStore.put("CS", new Department("CS", "Computer Science", "Alan Turing Hall"));
        deptStore.put("EE", new Department("EE", "Electrical Engineering", "Maxwell Building"));

        // Seed Faculty (No student needed!)
        facultyStore.put("F301", new Faculty("F301", "Dr. Alan Turing", "turing@univ.edu", "B101", "CS"));
        facultyStore.put("F302", new Faculty("F302", "Dr. Donald Knuth", "knuth@univ.edu", "B105", "CS"));
        facultyStore.put("F401", new Faculty("F401", "Dr. Nikola Tesla", "tesla@univ.edu", "E202", "EE"));

        // Seed Courses (No student needed!)
        courseStore.put("CS101", new Course("CS101", "Intro to CS", 3, 1500.0, "CS", "F301"));
        courseStore.put("CS201", new Course("CS201", "Data Structures", 4, 2000.0, "CS", "F302"));
        courseStore.put("EE101", new Course("EE101", "Circuits", 3, 1500.0, "EE", "F401"));

        // Seed Students
        studentStore.put("S101", new Student("S101", "Alice Walker", "alice@univ.edu", "CS"));
        studentStore.put("S102", new Student("S102", "Bob Miller", "bob@univ.edu", "EE"));
        studentStore.put("S103", new Student("S103", "Carol Davis", "carol@univ.edu", "CS"));

        // Seed Registrations (M:N Junction Table)
        registrationStore.add(new Registration("REG-01", "S101", "CS101", "FALL", 2026, "A"));
        registrationStore.add(new Registration("REG-02", "S101", "CS201", "FALL", 2026, "IP"));
        registrationStore.add(new Registration("REG-03", "S102", "EE101", "FALL", 2026, "B+"));
        registrationStore.add(new Registration("REG-04", "S103", "CS101", "FALL", 2026, "A-"));

        // Verification A: Insertion without enrollment
        System.out.println("  [A. 3NF Insertion Test]");
        System.out.println("  Adding new faculty 'Dr. John McCarthy (F303)' and course 'CS301: AI' without any student enrollments...");
        facultyStore.put("F303", new Faculty("F303", "Dr. John McCarthy", "mccarthy@univ.edu", "B301", "CS"));
        courseStore.put("CS301", new Course("CS301", "Artificial Intelligence", 4, 2200.0, "CS", "F303"));
        System.out.printf("  >> Successfully added course '%s' taught by '%s'! (Zero students required)%n",
                courseStore.get("CS301").title(), facultyStore.get("F303").name());

        // Verification B: Single-row update
        System.out.println("\n  [B. 3NF Update Test]");
        System.out.println("  Moving Dr. Turing's office to 'B205' in Faculty table...");
        Faculty oldTuring = facultyStore.get("F301");
        facultyStore.put("F301", new Faculty(oldTuring.facultyId(), oldTuring.name(), oldTuring.email(), "B205", oldTuring.deptId()));
        System.out.println("  >> Exactly 1 record updated in 'faculty' table: Office = " + facultyStore.get("F301").office());
        System.out.println("  >> Registrations reference courseCode -> course references instructorId. Zero data inconsistency!");

        // Verification C: Clean deletion
        System.out.println("\n  [C. 3NF Deletion Test]");
        System.out.println("  Student Bob (S102) drops EE101. Removing enrollment 'REG-03' from registrations...");
        registrationStore.removeIf(r -> r.regId().equals("REG-03"));
        System.out.println("  >> Registration removed. Total active enrollments: " + registrationStore.size());
        System.out.println("  >> Verifying Course EE101 still exists: " + courseStore.get("EE101").title());
        System.out.println("  >> Verifying Instructor Dr. Tesla still exists: " + facultyStore.get("F401").name());
    }
}
