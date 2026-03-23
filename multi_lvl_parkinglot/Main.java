package multi_lvl_parkinglot;

import java.time.LocalDateTime;

/**
 * Demo driver that sets up a small 2-floor parking lot,
 * parks a few vehicles, prints status, and processes exits.
 */
public class Main {

    public static void main(String[] args) {

        // -- 1. Build strategies --
        SlotAssignmentStrategy assigner = new NearestEuclideanSlotStrategy(12.0);
        PricingStrategy pricer = new HourlyPricingStrategy();

        // -- 2. Create the lot --
        ParkingLot lot = new ParkingLot("CityCenter Parking", assigner, pricer);

        // -- 3. Add gates --
        Gate gateA = new Gate("A", 0, 0.0, 0.0);
        Gate gateB = new Gate("B", 1, 0.0, 0.0);
        lot.addGate(gateA);
        lot.addGate(gateB);

        // -- 4. Populate slots --
        //   Floor 0: 3 SMALL, 3 MEDIUM, 1 LARGE
        lot.addSlot(new Slot("S0-1", SlotType.SMALL,  0, 1.0, 2.0));
        lot.addSlot(new Slot("S0-2", SlotType.SMALL,  0, 3.0, 2.0));
        lot.addSlot(new Slot("S0-3", SlotType.SMALL,  0, 5.0, 2.0));
        lot.addSlot(new Slot("M0-1", SlotType.MEDIUM, 0, 1.0, 5.0));
        lot.addSlot(new Slot("M0-2", SlotType.MEDIUM, 0, 3.0, 5.0));
        lot.addSlot(new Slot("M0-3", SlotType.MEDIUM, 0, 5.0, 5.0));
        lot.addSlot(new Slot("L0-1", SlotType.LARGE,  0, 3.0, 8.0));

        //   Floor 1: 2 SMALL, 2 MEDIUM, 1 LARGE
        lot.addSlot(new Slot("S1-1", SlotType.SMALL,  1, 2.0, 2.0));
        lot.addSlot(new Slot("S1-2", SlotType.SMALL,  1, 4.0, 2.0));
        lot.addSlot(new Slot("M1-1", SlotType.MEDIUM, 1, 2.0, 5.0));
        lot.addSlot(new Slot("M1-2", SlotType.MEDIUM, 1, 4.0, 5.0));
        lot.addSlot(new Slot("L1-1", SlotType.LARGE,  1, 3.0, 8.0));

        System.out.println("---- Parking Lot Initialised ----\n");

        // -- 5. Park some vehicles through Gate A (floor 0) --
        Vehicle bike1    = new Vehicle("KA-01-1234", VehicleType.TWO_WHEELER);
        Vehicle sedan    = new Vehicle("MH-12-AB-5678", VehicleType.CAR);
        Vehicle suv      = new Vehicle("DL-04-CX-9999", VehicleType.CAR);
        Vehicle tourBus  = new Vehicle("TN-07-BUS-100", VehicleType.BUS);
        Vehicle bike2    = new Vehicle("KA-03-7777", VehicleType.TWO_WHEELER);

        Ticket t1 = lot.park(bike1, gateA);
        Ticket t2 = lot.park(sedan, gateA);
        Ticket t3 = lot.park(suv, gateA);
        Ticket t4 = lot.park(tourBus, gateA);
        Ticket t5 = lot.park(bike2, gateB);   // enters from floor-1 gate

        // -- 6. Show current status --
        lot.printStatus();

        // -- 7. Simulate exits with artificial departure times --
        System.out.println("---- Processing Exits ----\n");

        if (t1 != null) {
            // Bike stayed ~45 minutes (rounds up to 1 hour)
            lot.exit(t1.getTicketId(), t1.getEntryTime().plusMinutes(45));
        }
        if (t2 != null) {
            // Sedan stayed 2 hours 10 minutes (rounds up to 3 hours)
            lot.exit(t2.getTicketId(), t2.getEntryTime().plusHours(2).plusMinutes(10));
        }
        if (t4 != null) {
            // Bus stayed exactly 4 hours
            lot.exit(t4.getTicketId(), t4.getEntryTime().plusHours(4));
        }

        // -- 8. Status after some exits --
        lot.printStatus();

        // -- 9. Try to park another bus to show slot reuse --
        Vehicle anotherBus = new Vehicle("AP-28-BUS-200", VehicleType.BUS);
        lot.park(anotherBus, gateA);

        lot.printStatus();
    }
}
