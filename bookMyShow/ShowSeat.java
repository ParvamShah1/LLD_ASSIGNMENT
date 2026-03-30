package bookMyShow;

public class ShowSeat {

    private final String showSeatId;
    private final Seat seat;
    private SeatStatus status;
    private String heldByEmail;
    private long holdExpiresAt;

    public ShowSeat(String showSeatId, Seat seat) {
        this.showSeatId = showSeatId;
        this.seat = seat;
        this.status = SeatStatus.AVAILABLE;
        this.heldByEmail = null;
        this.holdExpiresAt = 0;
    }

    public String getShowSeatId() {
        return showSeatId;
    }

    public Seat getSeat() {
        return seat;
    }

    public SeatStatus getStatus() {
        return status;
    }

    public String getHeldByEmail() {
        return heldByEmail;
    }

    public boolean isHoldExpired() {
        return System.currentTimeMillis() > holdExpiresAt;
    }

    public void hold(String email, long expirationTime) {
        this.status = SeatStatus.LOCKED;
        this.heldByEmail = email;
        this.holdExpiresAt = expirationTime;
    }

    public void release() {
        this.status = SeatStatus.AVAILABLE;
        this.heldByEmail = null;
        this.holdExpiresAt = 0;
    }

    public void markBooked() {
        this.status = SeatStatus.BOOKED;
    }

    @Override
    public String toString() {
        return showSeatId + " [" + seat.getSeatId() + " - " + status + "]";
    }
}
