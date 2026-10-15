package resource;

/**
 * Custom AutoCloseable simulating an Audit Transaction Logger.
 * Used to demonstrate try-with-resources closing semantics and LIFO order.
 */
public class AuditLoggerResource implements AutoCloseable {
    private final String sessionUser;

    public AuditLoggerResource(String sessionUser) {
        this.sessionUser = sessionUser;
        System.out.println("  [OPEN]  AuditLoggerResource opened for user: " + sessionUser);
    }

    public void logAction(String action) {
        System.out.println("  [AUDIT] Action recorded: '" + action + "' by " + sessionUser);
    }

    @Override
    public void close() {
        System.out.println("  [CLOSE] AuditLoggerResource closed for user: " + sessionUser);
    }
}
