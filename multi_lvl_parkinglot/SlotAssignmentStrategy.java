package multi_lvl_parkinglot;

import java.util.List;

/**
 * Strategy interface for deciding which available slot
 * should be assigned to an incoming vehicle.
 */
public interface SlotAssignmentStrategy {

    /**
     * Picks the best available slot for the given vehicle entering at the specified gate.
     *
     * @param slots    all slots in the lot
     * @param vehicle  the vehicle that needs parking
     * @param entryGate the gate through which the vehicle entered
     * @return the chosen Slot, or null if nothing suitable is available
     */
    Slot findSlot(List<Slot> slots, Vehicle vehicle, Gate entryGate);
}
