package it.dev.cleto;

import it.dev.cleto.media.Programming;

import java.util.Calendar;
import java.util.Date;


public class Reloaded {


    public static void main(String[] args) {
        System.out.println("-- Start");


        // todo configuration logs to exclude jaudiotagger

        // todo read date last download

        // todo custom from/to
        // todo custom url name

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

        // before yesterday
        todayCal.add(Calendar.DATE, -1);
        Date beforeYesterday = todayCal.getTime();
        Programming beforeYesterdayProgramming = new Programming(beforeYesterday);
        beforeYesterdayProgramming.execute();

        System.out.println("-- End");
    }
}
