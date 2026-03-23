package snakeandladders;

import java.util.Random;

public class Dice {
    private final int maxValue;
    private final Random rng;

    public Dice(int maxValue) {
        this.maxValue = maxValue;
        this.rng = new Random();
    }

    public int roll() {
        return rng.nextInt(maxValue) + 1;
    }

    public int getMaxValue() {
        return maxValue;
    }
}
