package shape;

/**
 * Sealed interface permitting only Circle, Rectangle, and Triangle records.
 * Enables exhaustive pattern matching in switch expressions without requiring a default clause.
 */
public sealed interface Shape permits Circle, Rectangle, Triangle {
    String name();
}
