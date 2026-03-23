package pen;

public class InkWriteStrategy implements WriteStrategy {

    @Override
    public void perform(String inkColor) {
        System.out.println("[Fountain] Nib channels " + inkColor + " liquid ink onto the page.");
    }
}
