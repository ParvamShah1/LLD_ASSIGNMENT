package pen;

public class BottleRefillStrategy implements RefillStrategy {

    @Override
    public void refill(String freshColor) {
        System.out.println("~~ Dipping nib into ink bottle to absorb " + freshColor + " ink.");
    }
}
