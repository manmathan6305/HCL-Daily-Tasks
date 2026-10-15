package toolkit;

import java.util.Objects;

/**
 * Upper bounded generic container restricted strictly to Numerical types.
 * Demonstrates <T extends Number>.
 */
public class Box<T extends Number> {
    private T value;

    public Box(T value) {
        this.value = Objects.requireNonNull(value, "Box value cannot be null");
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = Objects.requireNonNull(value, "Box value cannot be null");
    }

    public double doubleValue() {
        return value.doubleValue();
    }

    /**
     * Compares numeric values using upper-bounded wildcard (? extends Number).
     */
    public boolean isGreaterThan(Box<? extends Number> other) {
        return this.doubleValue() > other.doubleValue();
    }

    @Override
    public String toString() {
        return String.format("Box<%s>[%s]", value.getClass().getSimpleName(), value);
    }
}
