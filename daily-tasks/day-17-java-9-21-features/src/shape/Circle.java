package shape;

/**
 * Immutable Circle record with compact constructor validation.
 */
public record Circle(double radius) implements Shape {
    public Circle {
        if (radius <= 0.0) {
            throw new IllegalArgumentException("Circle radius must be strictly positive: " + radius);
        }
    }

    @Override
    public String name() {
        return "Circle (r=" + radius + ")";
    }
}
