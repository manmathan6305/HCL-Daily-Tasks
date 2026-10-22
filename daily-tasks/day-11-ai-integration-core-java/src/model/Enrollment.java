package model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Enrollment record representing course registration (FR7).
 */
public class Enrollment {
    private final String enrollmentId;
    private final String studentId;
    private final String courseCode;
    private final LocalDateTime enrolledAt;
    private String grade;

    public Enrollment(String enrollmentId, String studentId, String courseCode) {
        this.enrollmentId = Objects.requireNonNull(enrollmentId, "enrollmentId cannot be null");
        this.studentId = Objects.requireNonNull(studentId, "studentId cannot be null");
        this.courseCode = Objects.requireNonNull(courseCode, "courseCode cannot be null");
        this.enrolledAt = LocalDateTime.now();
        this.grade = "IN_PROGRESS";
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public LocalDateTime getEnrolledAt() {
        return enrolledAt;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = Objects.requireNonNull(grade, "grade cannot be null");
    }

    @Override
    public String toString() {
        return String.format("Enrollment[%s | Student: %s -> %s | Grade: %s]",
                enrollmentId, studentId, courseCode, grade);
    }
}
