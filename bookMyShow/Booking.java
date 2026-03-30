package bookMyShow;

import java.util.List;
import java.util.UUID;

public class Booking {

    private final String bookingId;
    private final User user;
    private final Show show;
    private final List<ShowSeat> reservedSeats;
    private final double totalAmount;
    private BookingStatus status;

    public Booking(User user, Show show, List<ShowSeat> reservedSeats, double totalAmount) {
        this.bookingId = "BK-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        this.user = user;
        this.show = show;
        this.reservedSeats = reservedSeats;
        this.totalAmount = totalAmount;
        this.status = BookingStatus.PENDING;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<ShowSeat> getReservedSeats() {
        return reservedSeats;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void confirm() {
        this.status = BookingStatus.CONFIRMED;
    }

    public void cancel() {
        this.status = BookingStatus.CANCELLED;
    }

    @Override
    public String toString() {
        return "Booking{" + bookingId + ", user=" + user.getFullName()
                + ", movie=" + show.getMovie().getTitle()
                + ", seats=" + reservedSeats.size()
                + ", amount=Rs " + totalAmount
                + ", status=" + status + "}";
    }
}
