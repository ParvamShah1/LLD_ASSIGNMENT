package snakeandladders;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Game {
    private final Board board;
    private final Queue<Player> playerQueue;
    private final Dice dice;
    private final MakeMoveStrategy moveStrategy;

    public Game(Board board, List<String> playerNames, Dice dice, MakeMoveStrategy moveStrategy) {
        this.board = board;
        this.dice = dice;
        this.moveStrategy = moveStrategy;
        this.playerQueue = new LinkedList<>();
        for (String name : playerNames) {
            playerQueue.add(new Player(name));
        }
    }

    public void startGame() {
        System.out.println("=== Snake and Ladders Game Begins ===");
        System.out.println("Players: " + playerQueue);
        System.out.println("Board size: " + board.getBoardSize());
        System.out.println("Snakes: " + board.getSnakes());
        System.out.println("Ladders: " + board.getLadders());
        System.out.println();

        int roundNumber = 1;

        while (true) {
            Player current = playerQueue.poll();
            System.out.println("Round " + roundNumber + " | " + current.getName() + "'s turn (position: " + current.getCurrentPosition() + ")");

            boolean hasWon = moveStrategy.executeTurn(current, board, dice);

            if (hasWon) {
                System.out.println();
                System.out.println("*** " + current.getName() + " has reached " + board.getBoardSize() + " and wins the game! ***");
                return;
            }

            playerQueue.add(current);
            roundNumber++;
            System.out.println();
        }
    }
}
