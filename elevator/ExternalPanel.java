package elevator;

public class ExternalPanel {

    private final int floorNumber;
    private final ElevatorController controller;

    public ExternalPanel(int floorNumber, ElevatorController controller) {
        this.floorNumber = floorNumber;
        this.controller = controller;
    }

    public void pressUp() {
        controller.handleExternalRequest(floorNumber, Direction.UP);
    }

    public void pressDown() {
        controller.handleExternalRequest(floorNumber, Direction.DOWN);
    }
}
