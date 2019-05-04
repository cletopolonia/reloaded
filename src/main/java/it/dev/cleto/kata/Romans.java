package it.dev.cleto.kata;

import java.util.ArrayList;
import java.util.List;

public class Romans {

    public static final int MAX_LIMIT = 3999;

    public static void main(String[] args) {
        for (int i = 1; i < 10; i++) {
            System.out.println(integerToRoman2(i));
        }


    }

    public static String integerToRoman(int value) {
        if (value <= 0 || value > MAX_LIMIT) throw new IllegalArgumentException();

        String[] romans = {"I", "IV", "V", "IX", "X"};
        int[] numbers = {1, 4, 5, 9, 10};
        StringBuilder stringBuilder = new StringBuilder();

        int i = romans.length - 1;
        while (value > 0) {
            if (value - numbers[i] >= 0) {
                stringBuilder.append(romans[i]);
                value = value - numbers[i];
            } else {
                i--;
            }
        }

        return stringBuilder.toString();

    }


    public static String integerToRoman2(int value) {

        if (value < 0 || value > 10) throw new IllegalArgumentException();
        if (value == 0) return "0";

        List<String> romans = new ArrayList<>();
        romans.add("V");
        romans.add("IV");
        romans.add("I");

        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(4);
        numbers.add(1);

        String result = "";

        int pos = 0;
        while (value > 0) {
            if (value - numbers.get(pos) >= 0) {
                result += romans.get(pos);
                value -= numbers.get(pos);
            } else {
                pos++;
            }
        }
        return result;

    }


}
