package pen;

public class GelWriteStrategy implements WriteStrategy {

    @Override
    public void perform(String inkColor) {
        System.out.println("[Gel] Dispensing thick " + inkColor + " gel ink with vivid strokes.");
    }
}
