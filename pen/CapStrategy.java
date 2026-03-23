package pen;

public class CapStrategy implements OpenCloseStrategy {

    @Override
    public void open() {
        System.out.println(">> Pulling cap off the pen.");
    }

    @Override
    public void close() {
        System.out.println(">> Snapping cap back onto the pen.");
    }
}
