package it.dev.cleto.polygon.village;

import java.util.Random;

public class Utils {

    private static String[] NAMES = {"OLIVER", "HARRY", "JACK", "GEORGE"
            , "NOAH", "CHARLIE", "JACOB", "ALFIE", "FREDDIE", "OSCAR"};

    public static int getRandomNumberInRange(int min, int max) {
        if (min == max)
            return min;
        if (min > max) {
            throw new IllegalArgumentException("max[" + max + "] must be greater than min[" + min + "]");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String getRandomName() {
        return NAMES[getRandomNumberInRange(0, NAMES.length - 1)];
    }

    public static int getSumSide(Polygon man, Polygon woman) {
        if (getRandomNumberInRange(0, 1) == 1)
            return man.getHight() + woman.getBase();
        return man.getBase() + woman.getHight();
    }
}
