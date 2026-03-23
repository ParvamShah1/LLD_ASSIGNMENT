package pen;

public class Pen {

    private String inkColor;
    private boolean activated;
    private final PenType variant;

    private final WriteStrategy writeStrategy;
    private final OpenCloseStrategy openCloseStrategy;
    private final RefillStrategy refillStrategy;

    public Pen(String inkColor, PenType variant,
               WriteStrategy writeStrategy,
               OpenCloseStrategy openCloseStrategy,
               RefillStrategy refillStrategy) {
        this.inkColor = inkColor;
        this.variant = variant;
        this.activated = false;
        this.writeStrategy = writeStrategy;
        this.openCloseStrategy = openCloseStrategy;
        this.refillStrategy = refillStrategy;
    }

    public void start() {
        if (activated) {
            System.out.println("Pen is already activated, no need to open again.");
            return;
        }
        openCloseStrategy.open();
        activated = true;
    }

    public void close() {
        if (!activated) {
            System.out.println("Pen is already closed, nothing to do.");
            return;
        }
        openCloseStrategy.close();
        activated = false;
    }

    public void write() {
        if (!activated) {
            throw new IllegalStateException("Cannot write -- pen is not activated! Call start() first.");
        }
        writeStrategy.perform(inkColor);
    }

    public void refill(String newColor) {
        if (activated) {
            System.out.println("Please close the pen before refilling.");
            return;
        }
        refillStrategy.refill(newColor);
        this.inkColor = newColor;
        System.out.println("Ink color updated to: " + newColor);
    }

    public String getInkColor() {
        return inkColor;
    }

    public boolean isActivated() {
        return activated;
    }

    public PenType getVariant() {
        return variant;
    }

    @Override
    public String toString() {
        return "Pen{variant=" + variant + ", inkColor='" + inkColor + "', activated=" + activated + "}";
    }
}
