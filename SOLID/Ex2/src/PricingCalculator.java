import java.util.*;

public class PricingCalculator {

    private final Map<String, MenuItem> menu;

    public PricingCalculator(Map<String, MenuItem> menu) {
        this.menu = menu;
    }

    public double lineTotal(OrderLine line) {
        MenuItem item = menu.get(line.itemId);
        return item.price * line.qty;
    }

    public double subtotal(List<OrderLine> lines) {
        double sum = 0.0;
        for (OrderLine l : lines) {
            sum += lineTotal(l);
        }
        return sum;
    }

    public String itemName(String itemId) {
        return menu.get(itemId).name;
    }
}
