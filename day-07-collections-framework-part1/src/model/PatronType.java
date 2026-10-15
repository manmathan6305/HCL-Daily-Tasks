package model;

/**
 * Represents the type of library patron with priority levels for resource allocation.
 * Faculty requests are given higher priority over students and guests.
 */
public enum PatronType {
    FACULTY(1),
    STUDENT(2),
    GUEST(3);

    private final int priorityLevel;

    PatronType(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }
}
