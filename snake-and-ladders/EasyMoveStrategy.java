package snakeandladders;

public class EasyMoveStrategy implements MakeMoveStrategy {

    @Override
    public boolean executeTurn(Player player, Board board, Dice dice) {
        boolean keepRolling = true;

        while (keepRolling) {
            int rolled = dice.roll();
            System.out.println("  " + player.getName() + " rolled a " + rolled);

            int tentative = player.getCurrentPosition() + rolled;

            if (tentative > board.getBoardSize()) {
                System.out.println("  Move exceeds board limit (" + tentative + " > " + board.getBoardSize() + "). Turn skipped.");
                return false;
            }

            int finalPos = board.checkSnakesAndLadders(tentative);
            player.setCurrentPosition(finalPos);
            System.out.println("  " + player.getName() + " moves to " + finalPos);

            if (finalPos == board.getBoardSize()) {
                return true;
            }

            // In easy mode, rolling max value (6) grants an extra turn, unlimited
            keepRolling = (rolled == dice.getMaxValue());
            if (keepRolling) {
                System.out.println("  Bonus roll for " + player.getName() + "!");
            }
        }

        return false;
    }
}
