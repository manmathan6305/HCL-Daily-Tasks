package model;

import java.time.LocalDateTime;
import java.util.Objects;

public class PatronReservation implements Comparable<PatronReservation> {
    public enum Tier { FACULTY(1), STUDENT(2), GUEST(3);
        final int priority;
        Tier(int p) { this.priority = p; }
    }

    private final String reservationId;
    private final String patronName;
    private final Tier tier;
    private final String isbn;
    private final LocalDateTime timestamp;

    public PatronReservation(String reservationId, String patronName, Tier tier, String isbn) {
        this.reservationId = Objects.requireNonNull(reservationId, "ID required");
        this.patronName = Objects.requireNonNull(patronName, "Name required");
        this.tier = Objects.requireNonNull(tier, "Tier required");
        this.isbn = Objects.requireNonNull(isbn, "ISBN required");
        this.timestamp = LocalDateTime.now();
    }

    public String getReservationId() { return reservationId; }
    public String getPatronName() { return patronName; }
    public Tier getTier() { return tier; }
    public String getIsbn() { return isbn; }

    @Override
    public int compareTo(PatronReservation o) {
        int tierComp = Integer.compare(this.tier.priority, o.tier.priority);
        if (tierComp != 0) return tierComp;
        return this.timestamp.compareTo(o.timestamp);
    }

    @Override
    public String toString() {
        return String.format("Reservation[%s | %s (%s) -> ISBN: %s]", reservationId, patronName, tier, isbn);
    }
}
