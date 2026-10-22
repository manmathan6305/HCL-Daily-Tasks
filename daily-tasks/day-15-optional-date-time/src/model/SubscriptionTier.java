package model;

import java.time.Duration;
import java.time.Period;

/**
 * Represents subscription tiers with associated billing periods and grace durations.
 */
public enum SubscriptionTier {
    FREE_TRIAL(Period.ofDays(14), Duration.ofHours(24), 0.0),
    STANDARD(Period.ofMonths(1), Duration.ofDays(3), 19.99),
    PREMIUM(Period.ofMonths(3), Duration.ofDays(7), 49.99),
    ENTERPRISE(Period.ofYears(1), Duration.ofDays(14), 199.99);

    private final Period billingPeriod;
    private final Duration graceDuration;
    private final double monthlyCost;

    SubscriptionTier(Period billingPeriod, Duration graceDuration, double monthlyCost) {
        this.billingPeriod = billingPeriod;
        this.graceDuration = graceDuration;
        this.monthlyCost = monthlyCost;
    }

    public Period getBillingPeriod() {
        return billingPeriod;
    }

    public Duration getGraceDuration() {
        return graceDuration;
    }

    public double getMonthlyCost() {
        return monthlyCost;
    }
}
