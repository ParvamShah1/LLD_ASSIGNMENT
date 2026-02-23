import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final PricingRegistry pricing;

    public HostelFeeCalculator(FakeBookingRepo repo, PricingRegistry pricing) {
        this.repo = repo;
        this.pricing = pricing;
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000));
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        double base = pricing.roomBasePrice(req.roomType);

        double add = 0.0;
        for (AddOn a : req.addOns) {
            add += pricing.addOnPrice(a);
        }

        return new Money(base + add);
    }
}
