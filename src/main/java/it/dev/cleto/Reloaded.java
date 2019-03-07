package it.dev.cleto;

import java.util.Calendar;
import java.util.Date;


public class Reloaded {


    public static void main(String[] args) {
        System.out.println("-- Start");

        // today
        Calendar todayCal = Calendar.getInstance();
        Date today = todayCal.getTime();
        Programming todayProgramming = new Programming(today);
        todayProgramming.execute();

        // yesterday
        todayCal.add(Calendar.DATE, -1);
        Date yesterday = todayCal.getTime();
        Programming yesterdayProgramming = new Programming(yesterday);
        yesterdayProgramming.execute();

        System.out.println("-- End");
    }
}
