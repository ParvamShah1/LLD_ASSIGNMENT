package snakeandladders;

import java.util.List;

public class Board {
    private final int boardSize;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;

    public Board(int boardSize, List<Snake> snakes, List<Ladder> ladders) {
        this.boardSize = boardSize;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int checkSnakesAndLadders(int position) {
        for (Snake snake : snakes) {
            if (snake.getHead() == position) {
                System.out.println("    Bitten by snake at " + snake.getHead() + "! Sliding down to " + snake.getTail());
                return snake.getTail();
            }
        }
        for (Ladder ladder : ladders) {
            if (ladder.getBottom() == position) {
                System.out.println("    Climbed ladder from " + ladder.getBottom() + " up to " + ladder.getTop() + "!");
                return ladder.getTop();
            }
        }
        return position;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }
}
