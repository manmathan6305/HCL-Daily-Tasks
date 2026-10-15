package toolkit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Utility library providing:
 * - Bounded generic methods (<T extends Comparable<T>>)
 * - PECS (Producer Extends, Consumer Super) wildcard methods
 */
public class GenericToolkit {

    /**
     * Generic bounded method to find the maximum element in any collection
     * of elements implementing Comparable.
     */
    public static <T extends Comparable<T>> Optional<T> findMax(Collection<T> collection) {
        if (collection == null || collection.isEmpty()) {
            return Optional.empty();
        }

        T max = null;
        for (T item : collection) {
            if (max == null || item.compareTo(max) > 0) {
                max = item;
            }
        }
        return Optional.of(max);
    }

    /**
     * PECS: Producer Extends.
     * The input list produces numbers for reading, so we use <? extends Number>.
     */
    public static double sumOfNumbers(List<? extends Number> producerList) {
        double sum = 0.0;
        for (Number num : producerList) {
            sum += num.doubleValue();
        }
        return sum;
    }

    /**
     * PECS: Consumer Super.
     * The destination list consumes integers for writing, so we use <? super Integer>.
     */
    public static void addSequentialNumbers(List<? super Integer> consumerList, int count) {
        for (int i = 1; i <= count; i++) {
            consumerList.add(i * 10);
        }
    }

    /**
     * PECS: Combined Producer Extends and Consumer Super.
     * Safely transfers elements from source (producer) to destination (consumer).
     */
    public static <T> void transfer(List<? extends T> srcProducer, List<? super T> destConsumer) {
        for (T item : srcProducer) {
            destConsumer.add(item);
        }
    }
}
