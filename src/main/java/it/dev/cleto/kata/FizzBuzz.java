package it.dev.cleto.kata;

public class FizzBuzz {
    public static void main(String[] args) {
        for (int i = 1; i <= 100; i++) {
            System.out.println(printFizzBuzz(i));
        }
    }

    private static String printFizzBuzz(int i) {
        String value;
        switch (i % 15) {
            case 3:
            case 6:
            case 9:
            case 12:  // divisible by 3, print Fizz
                value = "Fizz";
                break;
            case 5:
            case 10:  // divisible by 5, print Buzz
                value = "Buzz";
                break;
            case 0:  // divisible by 3 and by 5, print FizzBuzz
                value = "FizzBuzz";
                break;
            default:  // else print the numberOfPopulation
                value = Integer.toString(i);
        }
        return value;
    }

//    @Test
//    public void fizzBuzz1() {
//        String fizzBuzz = printFizzBuzz(1);
//        assertEquals("1", fizzBuzz);
//    }
//
//    @Test
//    public void fizzBuzz3() {
//        String fizzBuzz = printFizzBuzz(3);
//        assertEquals("Fizz", fizzBuzz);
//    }
//
//    @Test
//    public void fizzBuzz5() {
//        String fizzBuzz = printFizzBuzz(5);
//        assertEquals("Buzz", fizzBuzz);
//    }
//
//    @Test
//    public void fizzBuzz15() {
//        String fizzBuzz = printFizzBuzz(15);
//        assertEquals("FizzBuzz", fizzBuzz);
//    }


    private static String printFizzBuzz2(int i) {
        if (i % 15 == 0) return "FizzBuzz";
        else if (i % 5 == 0) return "Buzz";
        else if (i % 3 == 0) return "Fizz";
        else return String.valueOf(i);
    }

}