package elevator;

public class ElevatorCar {

    private final String carId;
    private int currentFloor;
    private ElevatorState state;
    private final double maxLoad;
    private double presentLoad;
    private boolean doorOpen;

    private static final double DEFAULT_MAX_LOAD = 750.0;

    public ElevatorCar(String carId, double maxLoad) {
        this.carId = carId;
        this.currentFloor = 0;
        this.state = ElevatorState.IDLE;
        this.maxLoad = maxLoad;
        this.presentLoad = 0.0;
        this.doorOpen = false;
    }

    public ElevatorCar(String carId) {
        this(carId, DEFAULT_MAX_LOAD);
    }

    public String getCarId() {
        return carId;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public ElevatorState getState() {
        return state;
    }

    public void setState(ElevatorState state) {
        this.state = state;
    }

    public void loadWeight(double weight) {
        this.presentLoad += weight;
        System.out.println("[" + carId + "] Weight added: " + weight + " kg. Total load: " + presentLoad + " kg.");
    }

    public void unloadWeight(double weight) {
        this.presentLoad = Math.max(0, this.presentLoad - weight);
        System.out.println("[" + carId + "] Weight removed: " + weight + " kg. Total load: " + presentLoad + " kg.");
    }

    public boolean isExceedingCapacity() {
        return presentLoad > maxLoad;
    }

    private void warnOverload() {
        System.out.println(">> WARNING: Elevator " + carId + " is overloaded! Current: "
                + presentLoad + " kg, Limit: " + maxLoad + " kg.");
        System.out.println(">> Please reduce the load before proceeding.");
        openDoor();
    }

    public void moveToFloor(int targetFloor) {
        if (state == ElevatorState.OUT_OF_SERVICE) {
            System.out.println("[" + carId + "] Cannot move -- elevator is out of service.");
            return;
        }

        if (isExceedingCapacity()) {
            warnOverload();
            return;
        }

        if (targetFloor == currentFloor) {
            System.out.println("[" + carId + "] Already at floor " + currentFloor + ".");
            openDoor();
            return;
        }

        closeDoor();

        if (targetFloor > currentFloor) {
            state = ElevatorState.MOVING_UP;
            System.out.println("[" + carId + "] Going UP from floor " + currentFloor + " to floor " + targetFloor + "...");
        } else {
            state = ElevatorState.MOVING_DOWN;
            System.out.println("[" + carId + "] Going DOWN from floor " + currentFloor + " to floor " + targetFloor + "...");
        }

        currentFloor = targetFloor;
        state = ElevatorState.IDLE;
        System.out.println("[" + carId + "] Arrived at floor " + currentFloor + ".");
        openDoor();
    }

    public void openDoor() {
        if (!doorOpen) {
            doorOpen = true;
            System.out.println("[" + carId + "] Doors opening.");
        }
    }

    public void closeDoor() {
        if (doorOpen) {
            doorOpen = false;
            System.out.println("[" + carId + "] Doors closing.");
        }
    }

    public void emergencyStop() {
        System.out.println("!! EMERGENCY !! Elevator " + carId + " stopping at floor " + currentFloor + "!");
        state = ElevatorState.IDLE;
        openDoor();
    }

    @Override
    public String toString() {
        return "Elevator{" + carId + ", floor=" + currentFloor + ", state=" + state + "}";
    }
}
