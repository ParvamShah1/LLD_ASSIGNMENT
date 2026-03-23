package multi_lvl_parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Charges a flat rate per hour (rounded up to the next full hour)
 * depending on the slot size.
 *
 * Rates (per hour):
 *   SMALL  -> Rs 15
 *   MEDIUM -> Rs 30
 *   LARGE  -> Rs 60
 */
public class HourlyPricingStrategy implements PricingStrategy {

    private static final double RATE_SMALL  = 15.0;
    private static final double RATE_MEDIUM = 30.0;
    private static final double RATE_LARGE  = 60.0;

    @Override
    public double calculateFee(SlotType slotSize, LocalDateTime arrival, LocalDateTime departure) {
        long totalMinutes = Duration.between(arrival, departure).toMinutes();
        // Ceiling division: any partial hour counts as a full hour, minimum 1 hour
        long hours = Math.max(1, (totalMinutes + 59) / 60);
        return hours * rateFor(slotSize);
    }

    private double rateFor(SlotType size) {
        switch (size) {
            case SMALL:  return RATE_SMALL;
            case MEDIUM: return RATE_MEDIUM;
            case LARGE:  return RATE_LARGE;
            default:
                throw new IllegalArgumentException("Unrecognized slot size: " + size);
        }
    }
}
