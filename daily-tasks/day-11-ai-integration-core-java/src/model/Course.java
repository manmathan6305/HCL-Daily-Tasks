package model;

import java.util.Objects;

/**
 * Course entity for Project Task (FR7 / FR8).
 */
public class Course {
    private final String courseCode;
    private final String title;
    private final int credits;
    private final int maxCapacity;

    public Course(String courseCode, String title, int credits, int maxCapacity) {
        this.courseCode = Objects.requireNonNull(courseCode, "courseCode cannot be null");
        this.title = Objects.requireNonNull(title, "title cannot be null");
        if (credits <= 0) throw new IllegalArgumentException("Credits must be positive");
        if (maxCapacity <= 0) throw new IllegalArgumentException("Max capacity must be positive");
        this.credits = credits;
        this.maxCapacity = maxCapacity;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getCredits() {
        return credits;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course course)) return false;
        return Objects.equals(courseCode, course.courseCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseCode);
    }

    @Override
    public String toString() {
        return String.format("Course[%s: '%s' (%d credits, max %d)]", courseCode, title, credits, maxCapacity);
    }
}
