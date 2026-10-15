package service;

import model.Book;
import model.ReservationRequest;

import java.util.*;

/**
 * Service managing library catalogue, de-duplication, dual-queue waitlists (FIFO & Priority),
 * safe iterator-based deletions, and iterator concurrency semantics.
 */
public class LibraryWaitlistService {
    // 1. ArrayList catalogue: Fast index-based lookups and appends
    private final List<Book> catalogue = new ArrayList<>();

    // 2. LinkedHashSet for de-duplication while preserving incoming insertion order
    private final Set<Book> deduplicatedRegistry = new LinkedHashSet<>();

    // 3. ArrayDeque waitlist: Highly efficient FIFO double-ended queue for standard waitlists
    private final Deque<ReservationRequest> generalWaitlist = new ArrayDeque<>();

    // 4. PriorityQueue for faculty-first reservations: Min-heap based on natural priority
    private final PriorityQueue<ReservationRequest> priorityWaitlist = new PriorityQueue<>();

    /**
     * Adds a single book to the catalogue and registry.
     */
    public boolean addBook(Book book) {
        if (deduplicatedRegistry.add(book)) {
            catalogue.add(book);
            return true;
        }
        return false;
    }

    /**
     * Bulk imports a batch of books, de-duplicating via LinkedHashSet.
     * Returns the count of newly added unique books.
     */
    public int bulkImportWithDeduplication(List<Book> incomingBooks) {
        int initialSize = deduplicatedRegistry.size();
        for (Book book : incomingBooks) {
            if (deduplicatedRegistry.add(book)) {
                catalogue.add(book);
            }
        }
        return deduplicatedRegistry.size() - initialSize;
    }

    /**
     * FR2: Enqueues a reservation request into the standard ArrayDeque FIFO queue.
     */
    public void enqueueGeneralWaitlist(ReservationRequest request) {
        generalWaitlist.offerLast(request);
    }

    /**
     * FR2: Polls the next patron from the standard ArrayDeque FIFO queue.
     */
    public ReservationRequest processNextGeneralRequest() {
        return generalWaitlist.pollFirst();
    }

    /**
     * FR2: Enqueues a reservation request into the Faculty-First PriorityQueue.
     */
    public void enqueuePriorityWaitlist(ReservationRequest request) {
        priorityWaitlist.offer(request);
    }

    /**
     * FR2: Polls the next highest priority patron from the PriorityQueue.
     */
    public ReservationRequest processNextPriorityRequest() {
        return priorityWaitlist.poll();
    }

    /**
     * FR3: Safely audits and removes overdue items using Iterator.remove().
     * This avoids ConcurrentModificationException by coordinating modCount with the iterator.
     */
    public List<Book> removeOverdueItemsSafe(int minOverdueDays) {
        List<Book> removedBooks = new ArrayList<>();
        Iterator<Book> iterator = catalogue.iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.isOverdue() && book.getOverdueDays() >= minOverdueDays) {
                removedBooks.add(book);
                iterator.remove(); // SAFE: synchronizes expectedModCount with modCount
                deduplicatedRegistry.remove(book);
            }
        }
        return removedBooks;
    }

    /**
     * Demonstrates why directly removing from a Collection inside a for-each loop fails.
     * Throws ConcurrentModificationException because modCount != expectedModCount.
     */
    public String demonstrateConcurrentModificationException() {
        List<String> list = new ArrayList<>(List.of("Book-1", "Book-2", "Book-3", "Book-4"));
        try {
            for (String item : list) {
                if ("Book-1".equals(item)) {
                    // UNSAFE: Modifies collection directly during active iteration
                    list.remove(item);
                }
            }
            return "No exception thrown (unexpected)";
        } catch (ConcurrentModificationException e) {
            return "CAUGHT ConcurrentModificationException: Collection was structurally modified during iteration without notifying iterator (modCount != expectedModCount).";
        }
    }

    /**
     * Demonstrates ListIterator capabilities:
     * - Bidirectional navigation (hasNext/hasPrevious)
     * - In-place element replacement (set)
     * - In-place element insertion (add)
     */
    public List<String> demonstrateListIterator() {
        List<String> log = new ArrayList<>();
        ListIterator<Book> listIterator = catalogue.listIterator();

        log.add("--- Forward Traversal via ListIterator ---");
        while (listIterator.hasNext()) {
            int index = listIterator.nextIndex();
            Book book = listIterator.next();
            log.add(String.format("Index %d: %s", index, book.getTitle()));
            // Example modification using listIterator.set()
            if (book.getOverdueDays() > 15) {
                book.setOverdue(true);
                listIterator.set(book);
            }
        }

        log.add("--- Backward Traversal via ListIterator ---");
        while (listIterator.hasPrevious()) {
            int prevIndex = listIterator.previousIndex();
            Book book = listIterator.previous();
            log.add(String.format("Reverse Index %d: %s", prevIndex, book.getTitle()));
        }

        return log;
    }

    public List<Book> getCatalogue() {
        return Collections.unmodifiableList(catalogue);
    }

    public Deque<ReservationRequest> getGeneralWaitlist() {
        return generalWaitlist;
    }

    public PriorityQueue<ReservationRequest> getPriorityWaitlist() {
        return priorityWaitlist;
    }

    public int getCatalogueSize() {
        return catalogue.size();
    }
}
