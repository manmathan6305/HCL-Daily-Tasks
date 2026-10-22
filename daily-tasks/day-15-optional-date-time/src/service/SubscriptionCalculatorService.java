package service;

import model.Customer;
import model.Subscription;
import model.SubscriptionTier;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Core business calculation service utilizing java.time API exclusively:
 * - Period for date-based differences (expiry date calculation)
 * - Duration for time-based grace period additions
 * - ZonedDateTime for timezone conversions between UTC and IST
 * - DateTimeFormatter for standardized presentation
 */
public class SubscriptionCalculatorService {

    public static final ZoneId ZONE_IST = ZoneId.of("Asia/Kolkata");
    public static final ZoneId ZONE_UTC = ZoneId.of("UTC");

    public static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
    public static final DateTimeFormatter ZONED_FMT = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss z");

    /**
     * Computes the exact expiration date based on subscription start date and tier period.
     */
    public LocalDate calculateExpiryDate(Subscription subscription) {
        Objects.requireNonNull(subscription, "Subscription must not be null");
        return subscription.getStartDate().plus(subscription.getTier().getBillingPeriod());
    }

    /**
     * Calculates days remaining until expiration. Negative indicates expired.
     */
    public long calculateDaysRemaining(Subscription subscription, LocalDate currentDate) {
        LocalDate expiry = calculateExpiryDate(subscription);
        return ChronoUnit.DAYS.between(currentDate, expiry);
    }

    /**
     * Calculates the end of the grace period using Duration added to expiry at midnight.
     */
    public ZonedDateTime calculateGracePeriodEndUtc(Subscription subscription) {
        LocalDate expiry = calculateExpiryDate(subscription);
        ZonedDateTime expiryMidnightUtc = expiry.atStartOfDay(ZONE_UTC);
        return expiryMidnightUtc.plus(subscription.getTier().getGraceDuration());
    }

    /**
     * Converts a UTC timestamp to Customer's localized preferred timezone.
     */
    public ZonedDateTime convertUtcToCustomerZone(ZonedDateTime utcDateTime, Customer customer) {
        Objects.requireNonNull(utcDateTime, "utcDateTime must not be null");
        Objects.requireNonNull(customer, "customer must not be null");
        return utcDateTime.withZoneSameInstant(customer.getPreferredTimeZone());
    }

    /**
     * Converts UTC timestamp to Indian Standard Time (IST).
     */
    public ZonedDateTime convertUtcToIst(ZonedDateTime utcDateTime) {
        return utcDateTime.withZoneSameInstant(ZONE_IST);
    }

    /**
     * Formats subscription report into a structured overview.
     */
    public String generateSubscriptionAudit(Subscription sub, Customer cust, LocalDate today) {
        LocalDate expiry = calculateExpiryDate(sub);
        long daysRemaining = calculateDaysRemaining(sub, today);
        ZonedDateTime graceEndUtc = calculateGracePeriodEndUtc(sub);
        ZonedDateTime graceEndCustZone = convertUtcToCustomerZone(graceEndUtc, cust);
        ZonedDateTime graceEndIst = convertUtcToIst(graceEndUtc);

        Period billingPeriod = sub.getTier().getBillingPeriod();
        Duration graceDuration = sub.getTier().getGraceDuration();

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("+-------------------------------------------------------------+%n"));
        sb.append(String.format("| SUBSCRIPTION AUDIT REPORT: %-32s |%n", sub.getSubscriptionId()));
        sb.append(String.format("+-------------------------------------------------------------+%n"));
        sb.append(String.format("| Customer Name   : %-41s |%n", cust.getFullName()));
        sb.append(String.format("| Tier / Cost     : %-25s ($%.2f/mo)   |%n", sub.getTier().name(), sub.getTier().getMonthlyCost()));
        sb.append(String.format("| Start Date      : %-41s |%n", sub.getStartDate().format(DATE_FMT)));
        sb.append(String.format("| Billing Period  : %-41s |%n", billingPeriod));
        sb.append(String.format("| Expiry Date     : %-41s |%n", expiry.format(DATE_FMT)));
        sb.append(String.format("| Days Remaining  : %-41s |%n", daysRemaining >= 0 ? daysRemaining + " days" : "EXPIRED (" + Math.abs(daysRemaining) + " days ago)"));
        sb.append(String.format("| Grace Duration  : %-41s |%n", graceDuration.toHours() + " hours (" + graceDuration.toDays() + " days)"));
        sb.append(String.format("| Grace End (UTC) : %-41s |%n", graceEndUtc.format(ZONED_FMT)));
        sb.append(String.format("| Grace End (Cust): %-41s |%n", graceEndCustZone.format(ZONED_FMT)));
        sb.append(String.format("| Grace End (IST) : %-41s |%n", graceEndIst.format(ZONED_FMT)));
        sb.append(String.format("| Auto Renew      : %-41s |%n", sub.isAutoRenew() ? "YES" : "NO"));
        sb.append(String.format("| Promo Code      : %-41s |%n", cust.getPromoCode().orElse("NO PROMO APPLIED")));
        sb.append(String.format("+-------------------------------------------------------------+"));
        return sb.toString();
    }
}
