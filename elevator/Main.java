package elevator;

public class Main {
    public static void main(String[] args) {

        // Setup dispatch strategy and controller
        ElevatorSelectionStrategy strategy = new ShortestSeekTimeStrategy();
        ElevatorController controller = new ElevatorController(strategy);

        // Create elevator cars
        ElevatorCar liftA = new ElevatorCar("Lift-1", 750.0);
        ElevatorCar liftB = new ElevatorCar("Lift-2", 1200.0);
        ElevatorCar liftC = new ElevatorCar("Lift-3");

        // Put Lift-3 under maintenance
        liftC.setState(ElevatorState.OUT_OF_SERVICE);
        System.out.println(liftC.getCarId() + " set to OUT_OF_SERVICE (maintenance)\n");

        // Register all lifts
        controller.registerElevator(liftA);
        controller.registerElevator(liftB);
        controller.registerElevator(liftC);

        // Create panels
        ExternalPanel floor3Panel = new ExternalPanel(3, controller);
        ExternalPanel floor7Panel = new ExternalPanel(7, controller);
        InternalPanel liftAPanel = new InternalPanel(liftA, controller);
        InternalPanel liftBPanel = new InternalPanel(liftB, controller);

        // Scenario 1: Someone on floor 3 presses UP
        floor3Panel.pressUp();

        // Scenario 2: Overload Lift-1 and try to move
        liftA.loadWeight(900.0);
        liftAPanel.selectFloor(8);

        // Scenario 3: Remove some weight and retry
        liftA.unloadWeight(300.0);
        liftAPanel.selectFloor(8);

        // Scenario 4: Someone on floor 7 presses DOWN — Lift-2 should be dispatched
        floor7Panel.pressDown();

        // Scenario 5: Move Lift-2 internally to floor 2
        liftBPanel.selectFloor(2);

        // Scenario 6: Trigger building emergency
        liftAPanel.triggerAlarm();

        // Scenario 7: Power failure
        controller.handlePowerFailure();
    }
}
