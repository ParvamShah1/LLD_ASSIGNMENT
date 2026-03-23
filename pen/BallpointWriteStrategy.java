package pen;

public class BallpointWriteStrategy implements WriteStrategy {

    @Override
    public void perform(String inkColor) {
        System.out.println("[Ballpoint] Rolling ball deposits " + inkColor + " ink smoothly on paper.");
    }
}
