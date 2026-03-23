package snakeandladders;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Snake> snakes = Arrays.asList(
                new Snake(99, 12),
                new Snake(75, 33),
                new Snake(47, 19),
                new Snake(65, 24),
                new Snake(92, 50),
                new Snake(38, 3)
        );

        List<Ladder> ladders = Arrays.asList(
                new Ladder(4, 56),
                new Ladder(12, 48),
                new Ladder(28, 72),
                new Ladder(41, 79),
                new Ladder(63, 86),
                new Ladder(71, 93)
        );

        List<String> playerNames = Arrays.asList("Alice", "Bob", "Charlie");

        Difficulty mode = Difficulty.HARD;

        System.out.println("Starting Snake and Ladders in " + mode + " mode!\n");

        Game game = GameFactory.createGame(playerNames, snakes, ladders, mode);
        game.startGame();
    }
}
