package pen;

public class ClickStrategy implements OpenCloseStrategy {

    @Override
    public void open() {
        System.out.println(">> Clicking button to extend the tip.");
    }

    @Override
    public void close() {
        System.out.println(">> Clicking button to retract the tip.");
    }
}
