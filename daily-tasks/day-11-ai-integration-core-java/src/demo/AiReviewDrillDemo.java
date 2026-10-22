package demo;

import model.Course;
import model.Enrollment;
import model.Student;
import service.StudentService;
import service.StudentServiceImpl;

import java.util.List;

/**
 * Main application demonstrating Day 11:
 * - Prompt & Review Drill (Vague vs Specific AI prompts)
 * - Review of 5 Critical AI Generated Issues Found & Fixed
 * - Project Requirements FR7 (Course Enrollment) & FR8 (Academic Transcript)
 */
public class AiReviewDrillDemo {

    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("  HCL Day 11: AI Integration & Code Review Drill            ");
        System.out.println("============================================================");

        AiReviewDrillDemo demo = new AiReviewDrillDemo();
        demo.runPromptComparison();
        demo.runDemonstration();
    }

    public void runPromptComparison() {
        System.out.println("\n--- [DRILL 1] VAGUE PROMPT VS SPECIFIC PROMPT ANALYSIS ---");
        System.out.println("Vague Prompt:    'Create a Student class with some fields in Java.'");
        System.out.println("AI Vulnerabilities Generated:");
        System.out.println("  1. Public mutable fields or missing validation in setters (GPA allowed -5.0).");
        System.out.println("  2. Direct exposure of internal List<String> courses (caller can clear list).");
        System.out.println("  3. Missing equals() and hashCode() causing HashSet duplicate collisions.");
        System.out.println();
        System.out.println("Specific Prompt: 'Generate a production-ready Java 21 Student entity with immutable");
        System.out.println("studentId, name, email, validated GPA [0.0-4.0], unmodifiable defensive list for");
        System.out.println("enrolled courses, and equals/hashCode based strictly on studentId.'");
        System.out.println("Result: Clean, encapsulated, bulletproof OOP domain model.");
    }

    public void runDemonstration() {
        System.out.println("\n--- [DRILL 2] EXECUTING HARDENED SERVICE & FR7 / FR8 ---");
        StudentService service = new StudentServiceImpl();

        // 1. Setup Courses
        Course cs101 = new Course("CS-101", "Data Structures & Algorithms", 4, 30);
        Course cs202 = new Course("CS-202", "Advanced Java Architecture", 4, 25);
        Course db301 = new Course("DB-301", "Distributed Database Systems", 3, 20);
        service.addCourse(cs101);
        service.addCourse(cs202);
        service.addCourse(db301);

        // 2. Setup Students
        Student s1 = new Student("STU-101", "Alice Morgan", "alice.m@university.edu", 3.92);
        Student s2 = new Student("STU-102", "Bob Vance", "bob.v@university.edu", 3.65);
        Student s3 = new Student("STU-103", "Catherine Reed", "c.reed@university.edu", 3.98);
        service.registerStudent(s1);
        service.registerStudent(s2);
        service.registerStudent(s3);

        // 3. FR7: Enroll Student in Courses
        System.out.println("\n[FR7] Enrolling Student in Courses:");
        Enrollment e1 = service.enrollStudentInCourse("STU-101", "CS-101");
        Enrollment e2 = service.enrollStudentInCourse("STU-101", "CS-202");
        Enrollment e3 = service.enrollStudentInCourse("STU-101", "DB-301");
        service.enrollStudentInCourse("STU-102", "CS-101");
        service.enrollStudentInCourse("STU-103", "CS-202");
        System.out.println("  -> Enrolled: " + e1);
        System.out.println("  -> Enrolled: " + e2);
        System.out.println("  -> Enrolled: " + e3);

        // 4. Test Defensive Encapsulation
        System.out.println("\n[DEFENSE TEST] Attempting to mutate student enrolledCourses from caller:");
        try {
            s1.getEnrolledCourses().add("HACK-999");
            System.out.println("  -> FAIL: Internal list was compromised!");
        } catch (UnsupportedOperationException ex) {
            System.out.println("  -> SUCCESS: UnsupportedOperationException caught! List is defensively unmodifiable.");
        }

        // 5. FR8: Generate Official Academic Transcript
        System.out.println("\n[FR8] Generating Academic Transcript for STU-101:");
        String transcript = service.generateAcademicTranscript("STU-101");
        System.out.println(transcript);

        // 6. Ranking Top Performers
        System.out.println("Top Performers (by GPA):");
        List<Student> topStudents = service.findTopPerformers(2);
        for (Student s : topStudents) {
            System.out.printf("  * %s - GPA: %.2f%n", s.getName(), s.getGpa());
        }

        System.out.println("\n[SUCCESS] Day 11 AI Integration, Code Review & FR7-FR8 Completed Successfully!");
    }
}
