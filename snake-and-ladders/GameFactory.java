package snakeandladders;

import java.util.List;

public class GameFactory {

    private GameFactory() {
        // utility class
    }

    public static Game createGame(List<String> playerNames, List<Snake> snakes,
                                  List<Ladder> ladders, Difficulty difficulty) {
        Board board = new Board(100, snakes, ladders);
        Dice dice = new Dice(6);

        MakeMoveStrategy strategy;
        switch (difficulty) {
            case HARD:
                strategy = new HardMoveStrategy();
                break;
            case EASY:
            default:
                strategy = new EasyMoveStrategy();
                break;
        }

        return new Game(board, playerNames, dice, strategy);
    }
}
