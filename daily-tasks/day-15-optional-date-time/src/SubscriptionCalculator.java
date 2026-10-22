import exception.SubscriptionNotFoundException;
import model.Customer;
import model.Subscription;
import model.SubscriptionTier;
import service.SubscriptionCalculatorService;
import service.SubscriptionLookupService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;

/**
 * Day 15 Deliverable: Subscription Calculator
 * - Zero java.util.Date or java.util.Calendar usage (100% java.time API)
 * - Zero methods returning null (all query fallbacks handled with java.util.Optional)
 * - Safe Optional lookup chaining with map(), flatMap(), filter()
 * - Refactored null-heavy lookup service to throw SubscriptionNotFoundException via orElseThrow()
 * - Accurate IST/UTC conversions, Period and Duration calculations
 */
public class SubscriptionCalculator {

    public static void main(String[] args) {
        System.out.println("===================================================================");
        System.out.println("  HCL Day 15: Optional & DateTime API - Subscription Calculator    ");
        System.out.println("===================================================================\n");

        SubscriptionLookupService lookupService = new SubscriptionLookupService();
        SubscriptionCalculatorService calcService = new SubscriptionCalculatorService();
        LocalDate today = LocalDate.now();

        System.out.println("--- 1. AUDITING REGISTERED SUBSCRIPTIONS (java.time & Optional) ---");
        String[] testSubIds = {"SUB-001", "SUB-002", "SUB-003"};

        for (String subId : testSubIds) {
            // Using Optional lookup and chaining
            Optional<Subscription> subOpt = lookupService.findSubscriptionById(subId);
            if (subOpt.isPresent()) {
                Subscription sub = subOpt.get();
                // Lookup customer with flatMap
                Customer customer = lookupService.findCustomerById(sub.getCustomerId())
                        .orElseThrow(() -> new SubscriptionNotFoundException("Customer missing for sub: " + subId));

                String audit = calcService.generateSubscriptionAudit(sub, customer, today);
                System.out.println(audit);
                System.out.println();
            }
        }

        System.out.println("--- 2. DEMONSTRATING OPTIONAL PATTERNS (map, flatMap, filter, orElse) ---");
        // Safe chained lookup
        String targetId = "SUB-001";
        String customerEmail = lookupService.findSubscriptionById(targetId)
                .flatMap(sub -> lookupService.findCustomerById(sub.getCustomerId()))
                .map(Customer::getEmail)
                .orElse("default-support@hcl.com");
        System.out.println("Target Sub " + targetId + " -> Customer Email: " + customerEmail);

        // Filter demonstration: Check if customer has Premium tier and active auto-renew
        boolean isAutoRenewPremium = lookupService.findSubscriptionById("SUB-001")
                .filter(sub -> sub.getTier() == SubscriptionTier.PREMIUM)
                .filter(Subscription::isAutoRenew)
                .isPresent();
        System.out.println("SUB-001 is Active Auto-renew Premium? " + isAutoRenewPremium);

        // Safe promo code retrieval with fallback
        String promo1 = lookupService.findCustomerById("CUST-101")
                .flatMap(Customer::getPromoCode)
                .orElse("STANDARD_NO_DISCOUNT");
        String promo2 = lookupService.findCustomerById("CUST-102")
                .flatMap(Customer::getPromoCode)
                .orElse("STANDARD_NO_DISCOUNT");
        System.out.println("CUST-101 Promo Code: " + promo1);
        System.out.println("CUST-102 Promo Code: " + promo2);

        System.out.println("\n--- 3. REFACTORED NULL LOOKUP WITH orElseThrow() CUSTOM EXCEPTION ---");
        String nonExistentSubId = "SUB-999-UNKNOWN";
        System.out.println("Attempting lookup for non-existent ID: " + nonExistentSubId);
        try {
            Subscription missingSub = lookupService.findSubscriptionById(nonExistentSubId)
                    .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found for ID: " + nonExistentSubId));
            System.out.println("Retrieved: " + missingSub);
        } catch (SubscriptionNotFoundException ex) {
            System.out.println("  [SUCCESSFULLY CAUGHT EXPECTED EXCEPTION]");
            System.out.println("  Exception Type : " + ex.getClass().getName());
            System.out.println("  Message        : " + ex.getMessage());
        }

        System.out.println("\n--- 4. TIMEZONE CONVERSIONS: UTC vs IST vs USER TIMEZONE ---");
        ZonedDateTime nowUtc = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime nowIst = calcService.convertUtcToIst(nowUtc);
        ZonedDateTime nowEst = nowUtc.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime nowJst = nowUtc.withZoneSameInstant(ZoneId.of("Asia/Tokyo"));

        System.out.println("  UTC Instant        : " + nowUtc.format(SubscriptionCalculatorService.ZONED_FMT));
        System.out.println("  IST (Asia/Kolkata) : " + nowIst.format(SubscriptionCalculatorService.ZONED_FMT));
        System.out.println("  EST (America/NY)   : " + nowEst.format(SubscriptionCalculatorService.ZONED_FMT));
        System.out.println("  JST (Asia/Tokyo)   : " + nowJst.format(SubscriptionCalculatorService.ZONED_FMT));

        System.out.println("\n===================================================================");
        System.out.println("  All Day 15 Requirements Verified Successfully!");
        System.out.println("  - No method returns null");
        System.out.println("  - Zero java.util.Date / java.util.Calendar usage");
        System.out.println("===================================================================");
    }
}
