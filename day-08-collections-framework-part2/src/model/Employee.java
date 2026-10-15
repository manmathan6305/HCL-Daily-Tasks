package model;

import java.util.Objects;

/**
 * Domain model representing an employee.
 * Implements Comparable for natural ordering by Employee ID (Primary Key).
 * Properly overrides equals() and hashCode() satisfying the JCF contract.
 */
public class Employee implements Comparable<Employee> {
    private final int id;
    private final String name;
    private final Department department;
    private double salary;
    private int experienceYears;

    public Employee(int id, String name, Department department, double salary, int experienceYears) {
        this.id = id;
        this.name = Objects.requireNonNull(name, "Name cannot be null");
        this.department = Objects.requireNonNull(department, "Department cannot be null");
        this.salary = salary;
        this.experienceYears = experienceYears;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Department getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public void setExperienceYears(int experienceYears) {
        this.experienceYears = experienceYears;
    }

    /**
     * Natural ordering: Ascending by Employee ID.
     */
    @Override
    public int compareTo(Employee other) {
        return Integer.compare(this.id, other.id);
    }

    /**
     * JCF Contract: If two objects are equal according to equals(),
     * calling hashCode() on each of the two objects must produce the same integer result.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return id == employee.id &&
                Double.compare(employee.salary, salary) == 0 &&
                experienceYears == employee.experienceYears &&
                Objects.equals(name, employee.name) &&
                department == employee.department;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, department, salary, experienceYears);
    }

    @Override
    public String toString() {
        return String.format("Employee[ID=%-4d | Name=%-18s | Dept=%-20s | Salary=$%,10.2f | Exp=%2d yrs]",
                id, name, department.getDisplayName(), salary, experienceYears);
    }
}
