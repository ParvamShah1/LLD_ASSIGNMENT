package elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorController {

    private final List<ElevatorCar> fleet;
    private final ElevatorSelectionStrategy dispatchStrategy;

    public ElevatorController(ElevatorSelectionStrategy dispatchStrategy) {
        this.fleet = new ArrayList<>();
        this.dispatchStrategy = dispatchStrategy;
    }

    public synchronized void registerElevator(ElevatorCar car) {
        fleet.add(car);
        System.out.println("Registered elevator: " + car.getCarId());
    }

    public synchronized void handleExternalRequest(int floor, Direction direction) {
        System.out.println("\n--- External request: floor " + floor + ", direction " + direction + " ---");
        ElevatorCar selected = dispatchStrategy.selectElevator(fleet, floor, direction);

        if (selected == null) {
            System.out.println("No elevator available to serve this request right now.");
            return;
        }

        System.out.println("Dispatching elevator " + selected.getCarId() + " to floor " + floor + ".");
        selected.moveToFloor(floor);
    }

    public synchronized void handleInternalRequest(ElevatorCar car, int targetFloor) {
        System.out.println("\n--- Internal request: " + car.getCarId() + " -> floor " + targetFloor + " ---");
        car.moveToFloor(targetFloor);
    }

    public synchronized void activateEmergency() {
        System.out.println("\n!!! BUILDING EMERGENCY ACTIVATED !!!");
        for (ElevatorCar car : fleet) {
            car.emergencyStop();
        }
    }

    public synchronized void handlePowerFailure() {
        System.out.println("\n*** POWER FAILURE — returning all elevators to ground floor ***");
        for (ElevatorCar car : fleet) {
            if (car.getState() != ElevatorState.OUT_OF_SERVICE) {
                car.moveToFloor(0);
                car.setState(ElevatorState.OUT_OF_SERVICE);
                System.out.println("[" + car.getCarId() + "] Set to OUT_OF_SERVICE.");
            }
        }
    }
}
