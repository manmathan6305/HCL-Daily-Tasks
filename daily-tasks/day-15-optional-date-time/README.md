# 📅 Day 15: Optional & DateTime API

## 📋 Task Overview
- **Topic:** `Optional & DateTime`
- **Module / Sprint:** Week 3, Sprint 2.2
- **Daily Hands-on Task:** Subscription Calculator: compute expiry dates, days remaining, grace periods and IST/UTC conversions with `java.time`; refactor a null-heavy lookup service to return `Optional` and use `orElseThrow` with a custom exception.
- **Deliverable:** `SubscriptionCalculator.java`
- **Git Commit:** `day-15: optional-date-time`
- **Done When Criteria:** `No method returns null; no java.util.Date`

---

## 🏗️ Architecture & Project Structure
```text
day-15-optional-date-time/
├── notes/
│   ├── java-time-api-guide.md          # Comprehensive java.time API cheatsheet
│   └── optional-best-practices.md      # Rules for using Optional (do's & don'ts)
├── src/
│   ├── exception/
│   │   └── SubscriptionNotFoundException.java # Custom domain exception
│   ├── model/
│   │   ├── Customer.java               # Immutable customer with Optional promo code
│   │   ├── Subscription.java           # java.time domain model
│   │   └── SubscriptionTier.java       # Enum with Period and Duration
│   ├── service/
│   │   ├── SubscriptionCalculatorService.java # Expiry, grace period, IST/UTC conversions
│   │   └── SubscriptionLookupService.java     # Refactored Optional lookup API
│   └── SubscriptionCalculator.java     # Main executable driver & verification
└── README.md
```

---

## 🚀 Key Features Implemented
1. **Zero Nulls Returned:**
   - Lookup queries return `Optional<Subscription>` and `Optional<Customer>`.
   - Optional customer promo codes exposed safely via `getPromoCode()`.
2. **Zero `java.util.Date`:**
   - Pure modern `java.time` implementation using `LocalDate`, `ZonedDateTime`, `Period`, `Duration`, `ChronoUnit`, and `DateTimeFormatter`.
3. **Custom Exception Handling:**
   - Missing lookups refactored to throw `SubscriptionNotFoundException` via `.orElseThrow()`.
4. **Time Zone Conversions:**
   - Seamless bidirectional instant conversion between UTC (`ZoneId.of("UTC")`) and Indian Standard Time (`ZoneId.of("Asia/Kolkata")`) and customer local timezones.

---

## ⚙️ Compilation and Execution
```bash
# Navigate to source directory
cd "C:\Program Files\Java\jdk-21\bin\hcl\HCL-daily-tasks\HCL-Daily-Tasks\daily-tasks\day-15-optional-date-time\src"

# Compile all classes
javac -d ../bin exception/*.java model/*.java service/*.java SubscriptionCalculator.java

# Run SubscriptionCalculator
java -cp ../bin SubscriptionCalculator
```
