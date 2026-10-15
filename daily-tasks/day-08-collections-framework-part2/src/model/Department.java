package model;

/**
 * Enumeration of organization departments for grouping and payroll reporting.
 */
public enum Department {
    ENGINEERING("Engineering"),
    PRODUCT("Product Management"),
    DATA_SCIENCE("Data Science & AI"),
    FINANCE("Finance & Accounting"),
    HUMAN_RESOURCES("Human Resources"),
    MARKETING("Marketing & Growth");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
