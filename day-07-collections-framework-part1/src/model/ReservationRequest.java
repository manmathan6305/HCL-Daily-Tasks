package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Represents a reservation request for a book waitlist.
 * Implements Comparable for PriorityQueue ordering:
 * 1. Priority tier (Faculty first, then Student, then Guest)
 * 2. FIFO arrival timestamp tie-breaker for identical tiers.
 */
public class ReservationRequest implements Comparable<ReservationRequest> {
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

    private final String requestId;
    private final String patronName;
    private final PatronType patronType;
    private final String bookIsbn;
    private final LocalDateTime timestamp;

    public ReservationRequest(String requestId, String patronName, PatronType patronType, String bookIsbn) {
        this(requestId, patronName, patronType, bookIsbn, LocalDateTime.now());
    }

    public ReservationRequest(String requestId, String patronName, PatronType patronType, String bookIsbn, LocalDateTime timestamp) {
        this.requestId = Objects.requireNonNull(requestId, "requestId cannot be null");
        this.patronName = Objects.requireNonNull(patronName, "patronName cannot be null");
        this.patronType = Objects.requireNonNull(patronType, "patronType cannot be null");
        this.bookIsbn = Objects.requireNonNull(bookIsbn, "bookIsbn cannot be null");
        this.timestamp = Objects.requireNonNull(timestamp, "timestamp cannot be null");
    }

    public String getRequestId() {
        return requestId;
    }

    public String getPatronName() {
        return patronName;
    }

    public PatronType getPatronType() {
        return patronType;
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public int compareTo(ReservationRequest other) {
        // Faculty-first comparison based on tier priority (1 is higher priority than 2)
        int tierComparison = Integer.compare(this.patronType.getPriorityLevel(), other.patronType.getPriorityLevel());
        if (tierComparison != 0) {
            return tierComparison;
        }
        // FIFO order within the same priority tier
        return this.timestamp.compareTo(other.timestamp);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReservationRequest that)) return false;
        return Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId);
    }

    @Override
    public String toString() {
        return String.format("[ReqID: %s | %s (%s) | ISBN: %s | Time: %s]",
                requestId, patronName, patronType, bookIsbn, timestamp.format(TIME_FMT));
    }
}
