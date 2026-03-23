package multi_lvl_parkinglot;

/**
 * Represents an entry/exit gate at the parking facility.
 * Each gate has a fixed location used for distance calculations.
 */
public class Gate {

    private final String gateId;
    private final int floor;
    private final double xPos;
    private final double yPos;

    public Gate(String gateId, int floor, double xPos, double yPos) {
        this.gateId = gateId;
        this.floor = floor;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public String getGateId() {
        return gateId;
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

    @Override
    public String toString() {
        return "Gate-" + gateId + " (Floor " + floor + ")";
    }
}
