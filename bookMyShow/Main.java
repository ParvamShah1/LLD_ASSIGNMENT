package bookMyShow;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Setup system with 5-minute hold window and dynamic pricing
        SeatLockManager lockManager = new SeatLockManager(5 * 60 * 1000);
        PricingStrategy pricing = new DynamicPricingStrategy();
        TicketBookingSystem system = new TicketBookingSystem(lockManager, pricing);

        // Create users
        User alice = new User("alice@mail.com", "Alice");
        User bob = new User("bob@mail.com", "Bob");

        // Setup movie, theater, screen
        Movie movie = new Movie("MOV-1", "Interstellar", "English");
        Theater theater = new Theater("TH-1", "PVR Phoenix", "Mumbai");
        Screen screen = new Screen("SCR-1", "Screen 1", theater);
        theater.addScreen(screen);

        // Create seats
        Seat seat1 = new Seat("G1", 1, 1, SeatCategory.GOLD);
        Seat seat2 = new Seat("G2", 1, 2, SeatCategory.GOLD);
        Seat seat3 = new Seat("D1", 2, 1, SeatCategory.DIAMOND);

        // Create show seats
        ShowSeat ss1 = new ShowSeat("SS-1", seat1);
        ShowSeat ss2 = new ShowSeat("SS-2", seat2);
        ShowSeat ss3 = new ShowSeat("SS-3", seat3);

        // Create show
        Show show = new Show("SH-1", movie, screen, System.currentTimeMillis() + 3600000,
                Arrays.asList(ss1, ss2, ss3));

        System.out.println("=== Movie: " + movie + " at " + theater + " ===\n");

        List<ShowSeat> aliceSeats = Arrays.asList(ss1, ss2);
        List<ShowSeat> bobSeats = Arrays.asList(ss2, ss3);

        // Simulate concurrent booking with two threads
        Thread aliceThread = new Thread(() -> {
            try {
                Booking aliceBooking = system.bookTicket(alice, show, aliceSeats);
                Thread.sleep(200);
                boolean paid = system.confirmPayment(aliceBooking);
                if (paid) {
                    System.out.println("[Alice] Successfully booked! Amount: Rs " + aliceBooking.getTotalAmount());
                }
            } catch (Exception e) {
                System.out.println("[Alice] Booking failed: " + e.getMessage());
            }
        }, "Alice-Thread");

        Thread bobThread = new Thread(() -> {
            try {
                Thread.sleep(100);
                Booking bobBooking = system.bookTicket(bob, show, bobSeats);
                boolean paid = system.confirmPayment(bobBooking);
                if (paid) {
                    System.out.println("[Bob] Successfully booked! Amount: Rs " + bobBooking.getTotalAmount());
                }
            } catch (Exception e) {
                System.out.println("[Bob] Booking failed: " + e.getMessage());
            }
        }, "Bob-Thread");

        aliceThread.start();
        bobThread.start();

        try {
            aliceThread.join();
            bobThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n=== Final Seat Status ===");
        for (ShowSeat ss : show.getShowSeats()) {
            System.out.println("  " + ss);
        }
    }
}
