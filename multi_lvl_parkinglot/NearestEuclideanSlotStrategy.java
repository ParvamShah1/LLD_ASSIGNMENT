package multi_lvl_parkinglot;

import java.util.List;

/**
 * Assigns the nearest compatible slot based on Euclidean distance,
 * with a configurable weight applied to floor differences so that
 * same-floor slots are strongly preferred.
 */
public class NearestEuclideanSlotStrategy implements SlotAssignmentStrategy {

    private final double floorPenalty;

    /**
     * @param floorPenalty multiplier for floor difference in distance calc.
     *                     Higher values discourage cross-floor assignments.
     */
    public NearestEuclideanSlotStrategy(double floorPenalty) {
        this.floorPenalty = floorPenalty;
    }

    /** Default floor penalty of 10.0 */
    public NearestEuclideanSlotStrategy() {
        this(10.0);
    }

    @Override
    public Slot findSlot(List<Slot> slots, Vehicle vehicle, Gate entryGate) {
        SlotType required = mapVehicleToSlotSize(vehicle.getCategory());

        Slot best = null;
        double bestDist = Double.MAX_VALUE;

        for (Slot s : slots) {
            if (!s.isAvailable()) continue;
            if (s.getSize() != required) continue;

            double dist = s.distanceTo(
                    entryGate.getXPos(), entryGate.getYPos(),
                    entryGate.getFloor(), floorPenalty);

            if (dist < bestDist) {
                bestDist = dist;
                best = s;
            }
        }
        return best;
    }

    /** Maps each vehicle category to the slot size it requires. */
    private SlotType mapVehicleToSlotSize(VehicleType vt) {
        switch (vt) {
            case TWO_WHEELER: return SlotType.SMALL;
            case CAR:         return SlotType.MEDIUM;
            case BUS:         return SlotType.LARGE;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + vt);
        }
    }
}
