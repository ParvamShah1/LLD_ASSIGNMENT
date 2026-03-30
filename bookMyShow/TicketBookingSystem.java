package bookMyShow;

import java.util.List;

public class TicketBookingSystem {

    private final SeatLockManager lockManager;
    private final PricingStrategy pricingStrategy;

    public TicketBookingSystem(SeatLockManager lockManager, PricingStrategy pricingStrategy) {
        this.lockManager = lockManager;
        this.pricingStrategy = pricingStrategy;
    }

    public Booking bookTicket(User user, Show show, List<ShowSeat> requestedSeats) throws Exception {
        boolean locked = lockManager.holdSeats(requestedSeats, user);

        if (!locked) {
            throw new Exception("Seats are unavailable or held by someone else. Try again later.");
        }

        double total = 0.0;
        for (ShowSeat ss : requestedSeats) {
            total += pricingStrategy.calculatePrice(ss, show);
        }

        Booking booking = new Booking(user, show, requestedSeats, total);
        System.out.println("[BookingSystem] Booking created: " + booking);
        return booking;
    }

    public boolean confirmPayment(Booking booking) {
        if (booking.getStatus() == BookingStatus.CANCELLED) {
            System.out.println("[BookingSystem] Cannot confirm — booking already cancelled.");
            return false;
        }

        for (ShowSeat ss : booking.getReservedSeats()) {
            if (ss.getStatus() == SeatStatus.LOCKED && ss.isHoldExpired()) {
                System.out.println("[BookingSystem] Hold expired for seat " + ss.getSeat().getSeatId()
                        + ". Cancelling booking.");
                lockManager.releaseSeats(booking.getReservedSeats());
                booking.cancel();
                return false;
            }
        }

        for (ShowSeat ss : booking.getReservedSeats()) {
            ss.markBooked();
        }
        booking.confirm();
        System.out.println("[BookingSystem] Payment confirmed: " + booking);
        return true;
    }

    public void cancelBooking(Booking booking) {
        if (booking.getStatus() == BookingStatus.CONFIRMED) {
            lockManager.releaseSeats(booking.getReservedSeats());
        }
        booking.cancel();
        System.out.println("[BookingSystem] Booking cancelled: " + booking.getBookingId());
    }
}
