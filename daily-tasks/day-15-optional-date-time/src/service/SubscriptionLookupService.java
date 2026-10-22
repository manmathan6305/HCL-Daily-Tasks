package service;

import model.Customer;
import model.Subscription;
import model.SubscriptionTier;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Refactored lookup service replacing legacy null-returning methods with Optional API.
 * Demonstrates Optional.ofNullable and Optional.empty.
 */
public class SubscriptionLookupService {
    private final Map<String, Customer> customerDb = new HashMap<>();
    private final Map<String, Subscription> subscriptionDb = new HashMap<>();

    public SubscriptionLookupService() {
        seedSampleData();
    }

    private void seedSampleData() {
        ZoneId ist = ZoneId.of("Asia/Kolkata");
        ZoneId utc = ZoneId.of("UTC");
        ZoneId est = ZoneId.of("America/New_York");

        Customer c1 = new Customer("CUST-101", "Arun Kumar", "arun.kumar@hcl.com", ist, "PROMO2026");
        Customer c2 = new Customer("CUST-102", "Sarah Jenkins", "s.jenkins@cloudcorp.io", est, null);
        Customer c3 = new Customer("CUST-103", "Vikram Patel", "v.patel@techglobal.in", ist, "VIP_EARLY");

        customerDb.put(c1.getId(), c1);
        customerDb.put(c2.getId(), c2);
        customerDb.put(c3.getId(), c3);

        LocalDate baseDate = LocalDate.now();
        subscriptionDb.put("SUB-001", new Subscription(
                "SUB-001", "CUST-101", SubscriptionTier.PREMIUM,
                baseDate.minusMonths(2), true,
                ZonedDateTime.now(utc).minusMonths(2), null
        ));

        subscriptionDb.put("SUB-002", new Subscription(
                "SUB-002", "CUST-102", SubscriptionTier.STANDARD,
                baseDate.minusDays(32), false,
                ZonedDateTime.now(utc).minusDays(32), "Customer requested downgrade"
        ));

        subscriptionDb.put("SUB-003", new Subscription(
                "SUB-003", "CUST-103", SubscriptionTier.FREE_TRIAL,
                baseDate.minusDays(10), true,
                ZonedDateTime.now(utc).minusDays(10), null
        ));
    }

    /**
     * Refactored: Returns Optional<Subscription> instead of null.
     */
    public Optional<Subscription> findSubscriptionById(String subscriptionId) {
        return Optional.ofNullable(subscriptionDb.get(subscriptionId));
    }

    /**
     * Refactored: Returns Optional<Customer> instead of null.
     */
    public Optional<Customer> findCustomerById(String customerId) {
        return Optional.ofNullable(customerDb.get(customerId));
    }

    /**
     * Chained lookup: finds customer through subscription ID.
     */
    public Optional<Customer> findCustomerBySubscriptionId(String subscriptionId) {
        return findSubscriptionById(subscriptionId)
                .flatMap(sub -> findCustomerById(sub.getCustomerId()));
    }
}
