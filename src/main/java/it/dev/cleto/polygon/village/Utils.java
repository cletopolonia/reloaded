package it.dev.cleto.polygon.village;

import java.util.Random;

public class Utils {
    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max[" + max + "] must be greater than min[" + min + "]");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static Rectangle createRectangle(int lowSide, int heightSide) {
        if (getRandomNumberInRange(0, 1) == 1)
            return new Rectangle(lowSide, heightSide);
        return new Rectangle(heightSide, lowSide);
    }
}
