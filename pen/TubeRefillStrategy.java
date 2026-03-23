package pen;

public class TubeRefillStrategy implements RefillStrategy {

    @Override
    public void refill(String freshColor) {
        System.out.println("~~ Swapping ink cartridge with a new " + freshColor + " tube.");
    }
}
