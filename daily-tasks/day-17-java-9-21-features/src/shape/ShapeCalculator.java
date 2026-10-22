package shape;

/**
 * Demonstrates Java 17-21 Exhaustive Pattern Matching in Switch Expressions.
 * Because Shape is sealed permitting only Circle, Rectangle, and Triangle,
 * the compiler proves exhaustiveness without requiring a default branch.
 */
public class ShapeCalculator {

    public static double computeArea(Shape shape) {
        return switch (shape) {
            case Circle c    -> Math.PI * c.radius() * c.radius();
            case Rectangle r -> r.width() * r.height();
            case Triangle t  -> {
                // Heron's formula
                double s = (t.a() + t.b() + t.c()) / 2.0;
                yield Math.sqrt(s * (s - t.a()) * (s - t.b()) * (s - t.c()));
            }
        };
    }

    public static double computePerimeter(Shape shape) {
        return switch (shape) {
            case Circle c    -> 2 * Math.PI * c.radius();
            case Rectangle r -> 2 * (r.width() + r.height());
            case Triangle t  -> t.a() + t.b() + t.c();
        };
    }

    public static String describe(Shape shape) {
        return switch (shape) {
            case Circle c when c.radius() > 10.0 -> "Large " + c.name();
            case Circle c                        -> "Standard " + c.name();
            case Rectangle r when r.width() == r.height() -> "Square " + r.name();
            case Rectangle r                     -> "Oblong " + r.name();
            case Triangle t                      -> "General " + t.name();
        };
    }
}
