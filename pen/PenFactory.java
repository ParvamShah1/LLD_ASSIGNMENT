package pen;

public class PenFactory {

    public static Pen buildPen(PenType type, MechanismType mechanism, String inkColor) {
        WriteStrategy writer = resolveWriteStrategy(type);
        OpenCloseStrategy opener = resolveOpenCloseStrategy(mechanism);
        RefillStrategy refiller = resolveRefillStrategy(type);

        return new Pen(inkColor, type, writer, opener, refiller);
    }

    private static WriteStrategy resolveWriteStrategy(PenType type) {
        switch (type) {
            case BALLPOINT:
                return new BallpointWriteStrategy();
            case GEL:
                return new GelWriteStrategy();
            case INK:
                return new InkWriteStrategy();
            default:
                throw new IllegalArgumentException("Unrecognized pen type: " + type);
        }
    }

    private static OpenCloseStrategy resolveOpenCloseStrategy(MechanismType mechanism) {
        switch (mechanism) {
            case CAP:
                return new CapStrategy();
            case CLICK:
                return new ClickStrategy();
            default:
                throw new IllegalArgumentException("Unrecognized mechanism: " + mechanism);
        }
    }

    private static RefillStrategy resolveRefillStrategy(PenType type) {
        switch (type) {
            case INK:
                return new BottleRefillStrategy();
            case BALLPOINT:
            case GEL:
                return new TubeRefillStrategy();
            default:
                throw new IllegalArgumentException("Unrecognized pen type for refill: " + type);
        }
    }
}
