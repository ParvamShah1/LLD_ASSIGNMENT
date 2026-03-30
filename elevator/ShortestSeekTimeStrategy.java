package elevator;

import java.util.List;

public class ShortestSeekTimeStrategy implements ElevatorSelectionStrategy {

    @Override
    public ElevatorCar selectElevator(List<ElevatorCar> elevators, int requestedFloor, Direction direction) {
        ElevatorCar bestChoice = null;
        int shortestDistance = Integer.MAX_VALUE;

        for (ElevatorCar car : elevators) {
            if (car.getState() == ElevatorState.OUT_OF_SERVICE) {
                continue;
            }

            int gap = Math.abs(car.getCurrentFloor() - requestedFloor);

            if (car.getState() == ElevatorState.IDLE) {
                if (gap < shortestDistance) {
                    shortestDistance = gap;
                    bestChoice = car;
                }
            } else if (car.getState() == ElevatorState.MOVING_UP && direction == Direction.UP) {
                if (car.getCurrentFloor() <= requestedFloor && gap < shortestDistance) {
                    shortestDistance = gap;
                    bestChoice = car;
                }
            } else if (car.getState() == ElevatorState.MOVING_DOWN && direction == Direction.DOWN) {
                if (car.getCurrentFloor() >= requestedFloor && gap < shortestDistance) {
                    shortestDistance = gap;
                    bestChoice = car;
                }
            }
        }

        return bestChoice;
    }
}
