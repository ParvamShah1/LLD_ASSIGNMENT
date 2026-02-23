import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Hostel Fee Calculator ===");

        PricingRegistry pricing = new PricingRegistry();
        pricing.registerRoom(LegacyRoomTypes.SINGLE, () -> 14000.0);
        pricing.registerRoom(LegacyRoomTypes.DOUBLE, () -> 15000.0);
        pricing.registerRoom(LegacyRoomTypes.TRIPLE, () -> 12000.0);
        pricing.registerAddOn(AddOn.MESS, () -> 1000.0);
        pricing.registerAddOn(AddOn.LAUNDRY, () -> 500.0);
        pricing.registerAddOn(AddOn.GYM, () -> 300.0);

        BookingRequest req = new BookingRequest(LegacyRoomTypes.DOUBLE, List.of(AddOn.LAUNDRY, AddOn.MESS));
        HostelFeeCalculator calc = new HostelFeeCalculator(new FakeBookingRepo(), pricing);
        calc.process(req);
    }
}
