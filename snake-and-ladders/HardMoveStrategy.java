package snakeandladders;

public class HardMoveStrategy implements MakeMoveStrategy {

    private static final int MAX_CONSECUTIVE_SIXES = 3;

    @Override
    public boolean executeTurn(Player player, Board board, Dice dice) {
        int consecutiveMaxRolls = 0;

        while (true) {
            int rolled = dice.roll();
            System.out.println("  " + player.getName() + " rolled a " + rolled);

            if (rolled == dice.getMaxValue()) {
                consecutiveMaxRolls++;
                if (consecutiveMaxRolls >= MAX_CONSECUTIVE_SIXES) {
                    System.out.println("  " + player.getName() + " rolled " + MAX_CONSECUTIVE_SIXES
                            + " consecutive sixes! Turn forfeited.");
                    return false;
                }
            }

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

            if (rolled != dice.getMaxValue()) {
                break;
            }

            System.out.println("  Bonus roll for " + player.getName() + "!");
        }

        return false;
    }
}
