package exception;

public class ResourceAllocationException extends Exception {
    public ResourceAllocationException(String message) {
        super(message);
    }
    public ResourceAllocationException(String message, Throwable cause) {
        super(message, cause);
    }
}
