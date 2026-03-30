package bookMyShow;

public class DynamicPricingStrategy implements PricingStrategy {

    private static final double GOLD_BASE_PRICE = 180.0;
    private static final double DIAMOND_BASE_PRICE = 350.0;
    private static final double SURGE_FACTOR = 1.15;

    @Override
    public double calculatePrice(ShowSeat showSeat, Show show) {
        double basePrice;

        switch (showSeat.getSeat().getCategory()) {
            case GOLD:
                basePrice = GOLD_BASE_PRICE;
                break;
            case DIAMOND:
                basePrice = DIAMOND_BASE_PRICE;
                break;
            default:
                basePrice = GOLD_BASE_PRICE;
        }

        return Math.round(basePrice * SURGE_FACTOR * 100.0) / 100.0;
    }
}
