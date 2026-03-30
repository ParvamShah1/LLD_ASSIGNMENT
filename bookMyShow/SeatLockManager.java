package bookMyShow;

import java.util.List;

public class SeatLockManager {

    private final long holdDurationMillis;

    public SeatLockManager(long holdDurationMillis) {
        this.holdDurationMillis = holdDurationMillis;
    }

    public synchronized boolean holdSeats(List<ShowSeat> seats, User user) {
        for (ShowSeat ss : seats) {
            if (ss.getStatus() == SeatStatus.BOOKED) {
                return false;
            }
            if (ss.getStatus() == SeatStatus.LOCKED) {
                if (!ss.getHeldByEmail().equals(user.getEmailId()) && !ss.isHoldExpired()) {
                    return false;
                }
            }
        }

        long expiresAt = System.currentTimeMillis() + holdDurationMillis;
        for (ShowSeat ss : seats) {
            ss.hold(user.getEmailId(), expiresAt);
        }
        return true;
    }

    public synchronized void releaseSeats(List<ShowSeat> seats) {
        for (ShowSeat ss : seats) {
            if (ss.getStatus() == SeatStatus.LOCKED) {
                ss.release();
            }
        }
    }
}
