package multi_lvl_parkinglot;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Central orchestrator for the parking facility.
 * Manages slots, gates, active tickets, and delegates to
 * strategy objects for slot assignment and pricing.
 */
public class ParkingLot {

    private final String name;
    private final List<Slot> slots;
    private final List<Gate> gates;
    private final Map<String, Ticket> activeTickets;   // ticketId -> Ticket
    private final SlotAssignmentStrategy assignmentStrategy;
    private final PricingStrategy pricingStrategy;

    public ParkingLot(String name,
                      SlotAssignmentStrategy assignmentStrategy,
                      PricingStrategy pricingStrategy) {
        this.name = name;
        this.slots = new ArrayList<>();
        this.gates = new ArrayList<>();
        this.activeTickets = new HashMap<>();
        this.assignmentStrategy = assignmentStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    // ---- Setup helpers ----

    public void addSlot(Slot slot) {
        slots.add(slot);
    }

    public void addGate(Gate gate) {
        gates.add(gate);
    }

    public Gate getGateById(String gateId) {
        for (Gate g : gates) {
            if (g.getGateId().equals(gateId)) return g;
        }
        return null;
    }

    // ---- Core operations ----

    /**
     * Parks a vehicle that entered through the specified gate.
     * Delegates slot selection to the configured SlotAssignmentStrategy.
     *
     * @return a Ticket if successful, or null when the lot is full for
     *         that vehicle category.
     */
    public Ticket park(Vehicle vehicle, Gate entryGate) {
        Slot chosen = assignmentStrategy.findSlot(slots, vehicle, entryGate);
        if (chosen == null) {
            System.out.println(">> No suitable slot for " + vehicle + ". Lot full for this type.");
            return null;
        }

        chosen.markOccupied();
        Ticket ticket = new Ticket(vehicle, chosen, LocalDateTime.now());
        activeTickets.put(ticket.getTicketId(), ticket);

        System.out.println(">> Parked " + vehicle + " at " + chosen + " | " + ticket.getTicketId());
        return ticket;
    }

    /**
     * Processes a vehicle exit. Frees the slot and computes the fee.
     *
     * @param ticketId   the ticket that was issued at entry
     * @param exitTime   the time the vehicle is leaving
     * @return the computed fee, or -1 if the ticket was not found
     */
    public double exit(String ticketId, LocalDateTime exitTime) {
        Ticket ticket = activeTickets.remove(ticketId);
        if (ticket == null) {
            System.out.println(">> Ticket " + ticketId + " not recognised.");
            return -1;
        }

        ticket.getAssignedSlot().markFree();
        double fee = pricingStrategy.calculateFee(
                ticket.getAssignedSlot().getSize(),
                ticket.getEntryTime(),
                exitTime);

        System.out.printf(">> Vehicle %s exiting. Duration-based fee: Rs %.2f%n",
                ticket.getVehicle(), fee);
        return fee;
    }

    /**
     * Prints a summary of every slot grouped by floor.
     */
    public void printStatus() {
        System.out.println("\n===== " + name + " — Status =====");
        int totalFree = 0;
        int totalOccupied = 0;

        // Group slots by floor for readability
        Map<Integer, List<Slot>> byFloor = new HashMap<>();
        for (Slot s : slots) {
            byFloor.computeIfAbsent(s.getFloor(), k -> new ArrayList<>()).add(s);
        }

        for (int floor : byFloor.keySet().stream().sorted().collect(java.util.stream.Collectors.toList())) {
            System.out.println("  Floor " + floor + ":");
            for (Slot s : byFloor.get(floor)) {
                System.out.println("    " + s);
                if (s.isAvailable()) totalFree++;
                else totalOccupied++;
            }
        }

        System.out.println("  -----------");
        System.out.printf("  Total: %d slots | %d free | %d occupied%n",
                totalFree + totalOccupied, totalFree, totalOccupied);
        System.out.println("  Active tickets: " + activeTickets.size());
        System.out.println("================================\n");
    }
}
