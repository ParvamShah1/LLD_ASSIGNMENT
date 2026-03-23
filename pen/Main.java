package pen;

public class Main {

    public static void main(String[] args) {

        System.out.println("===== Ballpoint Pen with Click Mechanism =====");
        Pen ballpoint = PenFactory.buildPen(PenType.BALLPOINT, MechanismType.CLICK, "Blue");
        System.out.println("Created: " + ballpoint);
        ballpoint.start();
        ballpoint.write();
        ballpoint.close();
        System.out.println();

        System.out.println("===== Gel Pen with Cap Mechanism =====");
        Pen gel = PenFactory.buildPen(PenType.GEL, MechanismType.CAP, "Black");
        System.out.println("Created: " + gel);
        gel.start();
        gel.write();
        gel.close();
        gel.refill("Purple");
        gel.start();
        gel.write();
        gel.close();
        System.out.println();

        System.out.println("===== Fountain/Ink Pen with Cap Mechanism =====");
        Pen fountain = PenFactory.buildPen(PenType.INK, MechanismType.CAP, "Red");
        System.out.println("Created: " + fountain);
        fountain.start();
        fountain.write();
        fountain.close();
        fountain.refill("Green");
        fountain.start();
        fountain.write();
        fountain.close();
        System.out.println();

        System.out.println("===== Error Scenarios =====");

        // Attempt to write without activating
        Pen locked = PenFactory.buildPen(PenType.BALLPOINT, MechanismType.CLICK, "Orange");
        try {
            locked.write();
        } catch (IllegalStateException ex) {
            System.out.println("Caught expected error: " + ex.getMessage());
        }

        // Attempt to refill while pen is open
        Pen openPen = PenFactory.buildPen(PenType.GEL, MechanismType.CAP, "Pink");
        openPen.start();
        openPen.refill("Cyan");
        openPen.close();

        // Duplicate open / close
        Pen dup = PenFactory.buildPen(PenType.INK, MechanismType.CAP, "Teal");
        dup.close();
        dup.start();
        dup.start();
        dup.close();
    }
}
