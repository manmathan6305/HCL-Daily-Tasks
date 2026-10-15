package review;

/**
 * Project Review Task:
 * - Code audit for the 4 core Java memory pitfalls
 * - Stack vs Heap memory diagram for a business method:
 *   processReservationRequest(patronId, isbn)
 */
public class MemoryAuditAndDiagram {

    public static void displayProjectReview() {
        System.out.println("\n============================================================");
        System.out.println(" [PROJECT TASK] CODE REVIEW FOR COMMON MEMORY PITFALLS");
        System.out.println("============================================================");

        System.out.println("1. Pitfall 1: Unbounded Growing Static Collections");
        System.out.println("   - Risk: Static Maps or Lists act as GC Roots, retaining all added objects forever.");
        System.out.println("   - Safe Practice: Use bounded caches (e.g. Caffeine/Guava Cache with LRU eviction) or WeakHashMap.");

        System.out.println("\n2. Pitfall 2: Unclosed OS & Network Resources");
        System.out.println("   - Risk: Leaving File streams, Sockets, and JDBC connections open leads to file descriptor leaks.");
        System.out.println("   - Safe Practice: Always enclose AutoCloseable resources in try-with-resources blocks.");

        System.out.println("\n3. Pitfall 3: String Concatenation in Loops");
        System.out.println("   - Risk: s += item creates N new String and char[] instances, causing high memory churn.");
        System.out.println("   - Safe Practice: Use StringBuilder with appropriate initial capacity.");

        System.out.println("\n4. Pitfall 4: Boxed Primitive Identity Comparison");
        System.out.println("   - Risk: Comparing entity IDs or quantities with == works for values <= 127 but breaks for >= 128.");
        System.out.println("   - Safe Practice: Use Objects.equals() or primitive int unboxing.");

        System.out.println("\n============================================================");
        System.out.println(" [PROJECT TASK] STACK VS HEAP MEMORY ARCHITECTURE DIAGRAM");
        System.out.println("============================================================");
        System.out.println("Tracing execution of: LibraryWaitlistService.enqueuePriorityWaitlist(req)");
        System.out.println();
        System.out.println("  +-------------------------------------+          +--------------------------------------------+");
        System.out.println("  |      JVM THREAD STACK FRAME         |          |               JVM HEAP SPACE               |");
        System.out.println("  +-------------------------------------+          +--------------------------------------------+");
        System.out.println("  | [Method: enqueuePriorityWaitlist]   |          |                                            |");
        System.out.println("  | - this (ref) -----------------------------------> LibraryWaitlistService instance           |");
        System.out.println("  | - req (ref)  -----------------------------------> ReservationRequest [ID: 'REQ-101']       |");
        System.out.println("  |                                     |          |   - patronName (ref) -> String 'Dr. Clark' |");
        System.out.println("  | [Method: compareTo]                 |          |   - patronType (ref) -> PatronType.FACULTY |");
        System.out.println("  | - tierComparison = 0 (primitive int)|          |   - timestamp (ref)  -> LocalDateTime      |");
        System.out.println("  | - this.patronType (ref) ------------+          |                                            |");
        System.out.println("  | - other.patronType (ref) -----------+----------> [String Pool: 'REQ-101', '978-0134685991'] |");
        System.out.println("  +-------------------------------------+          +--------------------------------------------+");
    }
}
