package multi_lvl_parkinglot;

import java.time.LocalDateTime;

/**
 * Strategy interface for computing the parking fee
 * when a vehicle exits the lot.
 */
public interface PricingStrategy {

    /**
     * Calculates the total fee for a parking session.
     *
     * @param slotSize  the type of slot that was occupied
     * @param arrival   when the vehicle entered
     * @param departure when the vehicle is leaving
     * @return the fee amount
     */
    double calculateFee(SlotType slotSize, LocalDateTime arrival, LocalDateTime departure);
}
