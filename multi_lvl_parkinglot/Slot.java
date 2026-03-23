package multi_lvl_parkinglot;

/**
 * A single parking spot within the lot.
 * Tracks its position (floor + x,y coordinates) and availability.
 */
public class Slot {

    private final String slotId;
    private final SlotType size;
    private final int floor;
    private final double xPos;
    private final double yPos;
    private boolean isAvailable;

    public Slot(String slotId, SlotType size, int floor, double xPos, double yPos) {
        this.slotId = slotId;
        this.size = size;
        this.floor = floor;
        this.xPos = xPos;
        this.yPos = yPos;
        this.isAvailable = true;
    }

    /**
     * Computes weighted Euclidean distance from a reference point.
     * Floor difference is amplified by the given weight factor to penalize
     * cross-floor travel.
     */
    public double distanceTo(double refX, double refY, int refFloor, double floorWeight) {
        double dx = this.xPos - refX;
        double dy = this.yPos - refY;
        double dFloor = (this.floor - refFloor) * floorWeight;
        return Math.sqrt(dx * dx + dy * dy + dFloor * dFloor);
    }

    public void markOccupied() {
        this.isAvailable = false;
    }

    public void markFree() {
        this.isAvailable = true;
    }

    // --- Getters ---

    public String getSlotId() {
        return slotId;
    }

    public SlotType getSize() {
        return size;
    }

    public int getFloor() {
        return floor;
    }

    public double getXPos() {
        return xPos;
    }

    public double getYPos() {
        return yPos;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    @Override
    public String toString() {
        return slotId + " (Floor " + floor + ", " + size + ", " + (isAvailable ? "FREE" : "TAKEN") + ")";
    }
}
