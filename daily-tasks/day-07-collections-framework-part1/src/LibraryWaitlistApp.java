import benchmark.CollectionPerformanceBenchmark;
import model.Book;
import model.PatronType;
import model.ReservationRequest;
import service.LibraryWaitlistService;

import java.util.Arrays;
import java.util.List;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Main application demonstrating Collections Framework - Part 1:
 * - ArrayList catalogue
 * - LinkedHashSet de-duplication
 * - ArrayDeque FIFO waitlist
 * - PriorityQueue Faculty-first reservation
 * - Iterator remove vs ListIterator vs ConcurrentModificationException
 * - Performance microbenchmark: ArrayList vs LinkedList
 */
public class LibraryWaitlistApp {
    private final LibraryWaitlistService libraryService = new LibraryWaitlistService();

    public static void main(String[] args) {
        LibraryWaitlistApp app = new LibraryWaitlistApp();
        System.out.println("============================================================");
        System.out.println("  HCL Day 7: Collections Framework - Library Management App ");
        System.out.println("============================================================");
        app.runAutomatedDemo();
    }

    public void runAutomatedDemo() {
        System.out.println("\n============================================================");
        System.out.println(" [FR1] CATALOGUE MANAGEMENT & LINKEDHASHSET DE-DUPLICATION");
        System.out.println("============================================================");
        
        List<Book> incomingShipment = Arrays.asList(
                new Book("978-0134685991", "Effective Java (3rd Edition)", "Joshua Bloch", false, 0),
                new Book("978-0596009205", "Head First Design Patterns", "Eric Freeman", false, 0),
                new Book("978-0134685991", "Effective Java (Duplicate Copy)", "Joshua Bloch", false, 0), // Duplicate ISBN
                new Book("978-0132350884", "Clean Code", "Robert C. Martin", true, 18), // Overdue
                new Book("978-0596009205", "Head First Design Patterns (Duplicate)", "Eric Freeman", false, 0), // Duplicate ISBN
                new Book("978-1491950357", "Building Microservices", "Sam Newman", true, 25) // Overdue
        );

        System.out.println("Attempting to import " + incomingShipment.size() + " books into LinkedHashSet registry...");
        int addedCount = libraryService.bulkImportWithDeduplication(incomingShipment);
        System.out.printf("Successfully imported %d unique books (Duplicates filtered out):%n", addedCount);
        
        int idx = 1;
        for (Book b : libraryService.getCatalogue()) {
            System.out.printf("  %d. %s by %s [ISBN: %s]%n", idx++, b.getTitle(), b.getAuthor(), b.getIsbn());
        }

        System.out.println("\n============================================================");
        System.out.println(" [FR2] WAITLIST: ARRAYDEQUE (FIFO) VS PRIORITYQUEUE (FACULTY-FIRST)");
        System.out.println("============================================================");

        ReservationRequest req1 = new ReservationRequest("REQ-101", "Alice (Student)", PatronType.STUDENT, "978-0134685991");
        ReservationRequest req2 = new ReservationRequest("REQ-102", "Bob (Student)", PatronType.STUDENT, "978-0134685991");
        ReservationRequest req3 = new ReservationRequest("REQ-103", "Dr. Clark (Faculty)", PatronType.FACULTY, "978-0134685991");
        ReservationRequest req4 = new ReservationRequest("REQ-104", "Prof. Davis (Faculty)", PatronType.FACULTY, "978-0134685991");
        ReservationRequest req5 = new ReservationRequest("REQ-105", "Evan (Guest)", PatronType.GUEST, "978-0134685991");

        System.out.println("Enqueueing requests in arrival order: Alice -> Bob -> Dr. Clark -> Prof. Davis -> Evan");
        
        List<ReservationRequest> allRequests = List.of(req1, req2, req3, req4, req5);
        for (ReservationRequest req : allRequests) {
            libraryService.enqueueGeneralWaitlist(req);
            libraryService.enqueuePriorityWaitlist(req);
        }

        System.out.println("\n--- Standard ArrayDeque FIFO Queue Dispatch Order ---");
        while (!libraryService.getGeneralWaitlist().isEmpty()) {
            ReservationRequest next = libraryService.processNextGeneralRequest();
            System.out.println("  -> Dispatched: " + next);
        }

        System.out.println("\n--- PriorityQueue (Faculty-First Tiered Dispatch) Order ---");
        while (!libraryService.getPriorityWaitlist().isEmpty()) {
            ReservationRequest next = libraryService.processNextPriorityRequest();
            System.out.println("  -> Dispatched: " + next);
        }

        System.out.println("\n============================================================");
        System.out.println(" [FR3] CONCURRENCY & ITERATOR REMOVE VS LISTITERATOR");
        System.out.println("============================================================");

        System.out.println("1. Demonstrating ConcurrentModificationException on naive for-each loop:");
        String cmeMessage = libraryService.demonstrateConcurrentModificationException();
        System.out.println("   Result: " + cmeMessage);

        System.out.println("\n2. Safe overdue removal using Iterator.remove() for items overdue >= 15 days:");
        List<Book> removed = libraryService.removeOverdueItemsSafe(15);
        for (Book b : removed) {
            System.out.printf("   Removed overdue book: '%s' (Overdue by %d days)%n", b.getTitle(), b.getOverdueDays());
        }
        System.out.printf("   Catalogue size after safe cleanup: %d books remaining.%n", libraryService.getCatalogueSize());

        System.out.println("\n3. Demonstrating ListIterator bidirectional capabilities:");
        List<String> listIteratorLog = libraryService.demonstrateListIterator();
        for (String line : listIteratorLog) {
            System.out.println("   " + line);
        }

        System.out.println("\n============================================================");
        System.out.println(" [BENCHMARK] ARRAYLIST VS LINKEDLIST TIMING TABLE");
        System.out.println("============================================================");
        
        System.out.println("Executing micro-benchmark (Sample size: 30,000 iterations)...");
        var benchmarkResults = CollectionPerformanceBenchmark.runBenchmark(30_000);
        String tableOutput = CollectionPerformanceBenchmark.formatTimingTable(benchmarkResults);
        System.out.println(tableOutput);

        Path current = Paths.get("").toAbsolutePath();
        String deliverablePath = current.endsWith("day-07-collections-framework-part1")
                ? "deliverables/timing-table.md"
                : "daily-tasks/day-07-collections-framework-part1/deliverables/timing-table.md";
        CollectionPerformanceBenchmark.exportMarkdownReport(benchmarkResults, deliverablePath);
        System.out.println("Benchmark deliverable exported to: " + deliverablePath);

        System.out.println("\n[SUCCESS] Day 7 Daily Hands-on Task & Project FR1-FR3 Completed Successfully!");
    }
}
