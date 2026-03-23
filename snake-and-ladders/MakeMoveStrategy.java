package snakeandladders;

public interface MakeMoveStrategy {
    /**
     * Executes a full turn for the given player.
     * Returns true if the player has won (reached the final square).
     */
    boolean executeTurn(Player player, Board board, Dice dice);
}
