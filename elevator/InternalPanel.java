package elevator;

public class InternalPanel {

    private final ElevatorCar car;
    private final ElevatorController controller;

    public InternalPanel(ElevatorCar car, ElevatorController controller) {
        this.car = car;
        this.controller = controller;
    }

    public void selectFloor(int floor) {
        controller.handleInternalRequest(car, floor);
    }

    public void openDoors() {
        car.openDoor();
    }

    public void closeDoors() {
        car.closeDoor();
    }

    public void triggerAlarm() {
        controller.activateEmergency();
    }
}
