package shape;

/**
 * Immutable Rectangle record with compact constructor validation.
 */
public record Rectangle(double width, double height) implements Shape {
    public Rectangle {
        if (width <= 0.0 || height <= 0.0) {
            throw new IllegalArgumentException(
                String.format("Rectangle dimensions must be positive: width=%.2f, height=%.2f", width, height)
            );
        }
    }

    @Override
    public String name() {
        return "Rectangle (" + width + " x " + height + ")";
    }
}
