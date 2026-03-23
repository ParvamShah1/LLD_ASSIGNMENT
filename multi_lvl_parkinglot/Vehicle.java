package multi_lvl_parkinglot;

/**
 * Represents a vehicle entering the parking facility.
 */
public class Vehicle {

    private final String plateNumber;
    private final VehicleType category;

    public Vehicle(String plateNumber, VehicleType category) {
        this.plateNumber = plateNumber;
        this.category = category;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public VehicleType getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return category + " [" + plateNumber + "]";
    }
}
