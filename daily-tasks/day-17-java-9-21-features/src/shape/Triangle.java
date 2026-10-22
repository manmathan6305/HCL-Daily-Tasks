package shape;

/**
 * Immutable Triangle record with compact constructor validation enforcing triangle inequality.
 */
public record Triangle(double a, double b, double c) implements Shape {
    public Triangle {
        if (a <= 0.0 || b <= 0.0 || c <= 0.0) {
            throw new IllegalArgumentException("Triangle sides must be strictly positive");
        }
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException(
                String.format("Violates triangle inequality theorem: sides (%.1f, %.1f, %.1f)", a, b, c)
            );
        }
    }

    @Override
    public String name() {
        return "Triangle (" + a + ", " + b + ", " + c + ")";
    }
}
