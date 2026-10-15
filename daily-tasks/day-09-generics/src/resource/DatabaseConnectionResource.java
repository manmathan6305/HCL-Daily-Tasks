package resource;

/**
 * Custom AutoCloseable simulating a Database connection.
 * Used to demonstrate try-with-resources closing semantics and LIFO order.
 */
public class DatabaseConnectionResource implements AutoCloseable {
    private final String connectionId;

    public DatabaseConnectionResource(String connectionId) {
        this.connectionId = connectionId;
        System.out.println("  [OPEN]  DatabaseConnectionResource opened (ID: " + connectionId + ")");
    }

    public void executeQuery(String sql) {
        System.out.println("  [EXEC]  Executing query on " + connectionId + ": " + sql);
    }

    @Override
    public void close() {
        System.out.println("  [CLOSE] DatabaseConnectionResource closed (ID: " + connectionId + ")");
    }
}
