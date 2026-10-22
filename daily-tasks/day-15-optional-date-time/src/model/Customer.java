package model;

import java.time.ZoneId;
import java.util.Objects;
import java.util.Optional;

/**
 * Immutable Customer record ensuring no method returns null.
 */
public class Customer {
    private final String id;
    private final String fullName;
    private final String email;
    private final ZoneId preferredTimeZone;
    private final String promoCode; // nullable field internally, exposed via Optional

    public Customer(String id, String fullName, String email, ZoneId preferredTimeZone, String promoCode) {
        this.id = Objects.requireNonNull(id, "Customer ID must not be null");
        this.fullName = Objects.requireNonNull(fullName, "Full name must not be null");
        this.email = Objects.requireNonNull(email, "Email must not be null");
        this.preferredTimeZone = Objects.requireNonNull(preferredTimeZone, "Time zone must not be null");
        this.promoCode = promoCode; // may be null
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public ZoneId getPreferredTimeZone() {
        return preferredTimeZone;
    }

    /**
     * Returns Optional to indicate promo code presence or absence without nulls.
     */
    public Optional<String> getPromoCode() {
        return Optional.ofNullable(promoCode);
    }

    @Override
    public String toString() {
        return String.format("Customer[id=%s, name=%s, email=%s, tz=%s, promo=%s]",
                id, fullName, email, preferredTimeZone, getPromoCode().orElse("NONE"));
    }
}
