package model;

import java.util.Objects;

public class EnterpriseEmployee implements Comparable<EnterpriseEmployee> {
    private final String id;
    private final String name;
    private final String department;
    private final double salary;
    private final int experience;

    public EnterpriseEmployee(String id, String name, String department, double salary, int experience) {
        this.id = Objects.requireNonNull(id, "ID required");
        this.name = Objects.requireNonNull(name, "Name required");
        this.department = Objects.requireNonNull(department, "Dept required");
        this.salary = salary;
        this.experience = experience;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getExperience() { return experience; }

    @Override
    public int compareTo(EnterpriseEmployee o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnterpriseEmployee that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("[%s] %-18s | %-16s | $%,10.2f | Exp: %2d yrs",
                id, name, department, salary, experience);
    }
}
