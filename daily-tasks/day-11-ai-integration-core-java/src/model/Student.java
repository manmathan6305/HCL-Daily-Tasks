package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Hardened Student Entity.
 * Corrects common AI code-generation flaws:
 * - Proper encapsulation (fields private final where applicable)
 * - Defensive copying for mutable collections (getEnrolledCourses returns unmodifiable list)
 * - Robust equals() and hashCode() contract based on unique studentId
 * - Null safety checks on all constructor arguments
 */
public class Student implements Comparable<Student> {
    private final String studentId;
    private final String name;
    private final String email;
    private double gpa;
    private final List<String> enrolledCourses;

    public Student(String studentId, String name, String email, double gpa) {
        this.studentId = Objects.requireNonNull(studentId, "studentId cannot be null");
        this.name = Objects.requireNonNull(name, "name cannot be null");
        this.email = Objects.requireNonNull(email, "email cannot be null");
        setGpa(gpa);
        this.enrolledCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0, received: " + gpa);
        }
        this.gpa = gpa;
    }

    /**
     * Defensive copy preventing encapsulation breach:
     * Callers cannot mutate internal state by calling getEnrolledCourses().clear()
     */
    public List<String> getEnrolledCourses() {
        return Collections.unmodifiableList(enrolledCourses);
    }

    public void addCourse(String courseCode) {
        Objects.requireNonNull(courseCode, "courseCode cannot be null");
        if (!enrolledCourses.contains(courseCode)) {
            enrolledCourses.add(courseCode);
        }
    }

    public boolean removeCourse(String courseCode) {
        return enrolledCourses.remove(courseCode);
    }

    @Override
    public int compareTo(Student other) {
        return this.studentId.compareTo(other.studentId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return Objects.equals(studentId, student.studentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }

    @Override
    public String toString() {
        return String.format("Student[ID=%s, Name='%s', GPA=%.2f, Courses=%s]",
                studentId, name, gpa, enrolledCourses);
    }
}
