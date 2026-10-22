package model;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.Optional;

/**
 * Domain model representing a customer's active or expired subscription.
 * Strictly uses java.time types. Never uses java.util.Date.
 */
public class Subscription {
    private final String subscriptionId;
    private final String customerId;
    private final SubscriptionTier tier;
    private final LocalDate startDate;
    private final boolean autoRenew;
    private final ZonedDateTime activatedAtUtc;
    private final String cancellationReason; // nullable internally

    public Subscription(String subscriptionId, String customerId, SubscriptionTier tier,
                        LocalDate startDate, boolean autoRenew, ZonedDateTime activatedAtUtc,
                        String cancellationReason) {
        this.subscriptionId = Objects.requireNonNull(subscriptionId, "subscriptionId cannot be null");
        this.customerId = Objects.requireNonNull(customerId, "customerId cannot be null");
        this.tier = Objects.requireNonNull(tier, "tier cannot be null");
        this.startDate = Objects.requireNonNull(startDate, "startDate cannot be null");
        this.autoRenew = autoRenew;
        this.activatedAtUtc = Objects.requireNonNull(activatedAtUtc, "activatedAtUtc cannot be null");
        this.cancellationReason = cancellationReason;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public SubscriptionTier getTier() {
        return tier;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public boolean isAutoRenew() {
        return autoRenew;
    }

    public ZonedDateTime getActivatedAtUtc() {
        return activatedAtUtc;
    }

    public Optional<String> getCancellationReason() {
        return Optional.ofNullable(cancellationReason);
    }

    @Override
    public String toString() {
        return String.format("Subscription[id=%s, customer=%s, tier=%s, start=%s, autoRenew=%b, status=%s]",
                subscriptionId, customerId, tier, startDate, autoRenew,
                getCancellationReason().map(r -> "CANCELLED(" + r + ")").orElse("ACTIVE"));
    }
}
