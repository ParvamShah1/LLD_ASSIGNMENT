import java.util.*;

public class PricingRegistry {
    private final Map<Integer, RoomPricing> roomPricings = new HashMap<>();
    private final Map<AddOn, AddOnPricing> addOnPricings = new HashMap<>();

    public void registerRoom(int roomType, RoomPricing pricing) {
        roomPricings.put(roomType, pricing);
    }

    public void registerAddOn(AddOn addOn, AddOnPricing pricing) {
        addOnPricings.put(addOn, pricing);
    }

    public double roomBasePrice(int roomType) {
        RoomPricing rp = roomPricings.get(roomType);
        if (rp == null) return 16000.0;
        return rp.basePrice();
    }

    public double addOnPrice(AddOn addOn) {
        AddOnPricing ap = addOnPricings.get(addOn);
        if (ap == null) return 0.0;
        return ap.price();
    }
}
