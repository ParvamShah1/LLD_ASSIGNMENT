package multi_lvl_parkinglot;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Issued when a vehicle is parked. Serves as proof of parking
 * and is required to calculate fees upon exit.
 */
public class Ticket {

    private static final AtomicLong COUNTER = new AtomicLong(1000);
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final String ticketId;
    private final Vehicle vehicle;
    private final Slot assignedSlot;
    private final LocalDateTime entryTime;

    public Ticket(Vehicle vehicle, Slot assignedSlot, LocalDateTime entryTime) {
        this.ticketId = "TKT-" + COUNTER.incrementAndGet();
        this.vehicle = vehicle;
        this.assignedSlot = assignedSlot;
        this.entryTime = entryTime;
    }

    public String getTicketId() {
        return ticketId;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Slot getAssignedSlot() {
        return assignedSlot;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    @Override
    public String toString() {
        return "Ticket{" + ticketId
                + ", vehicle=" + vehicle
                + ", slot=" + assignedSlot.getSlotId()
                + ", entry=" + entryTime.format(FMT)
                + "}";
    }
}
