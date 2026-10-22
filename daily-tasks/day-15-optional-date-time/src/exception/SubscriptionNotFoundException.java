package exception;

/**
 * Custom exception thrown when a requested subscription or customer cannot be located.
 * Used in conjunction with Optional.orElseThrow().
 */
public class SubscriptionNotFoundException extends RuntimeException {
    public SubscriptionNotFoundException(String message) {
        super(message);
    }
}
