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

        // today -2gg
        Calendar todayCal = Calendar.getInstance();
        todayCal.add(Calendar.DATE, -2);
        Date beforeYesterday = todayCal.getTime();
        Programming beforeYesterdayProgramming = new Programming(beforeYesterday);
        beforeYesterdayProgramming.execute();

        // today -1gg
        todayCal = Calendar.getInstance();
        todayCal.add(Calendar.DATE, -1);
        Date yesterday = todayCal.getTime();
        Programming yesterdayProgramming = new Programming(yesterday);
        yesterdayProgramming.execute();

        // today
        todayCal = Calendar.getInstance();
        Date today = todayCal.getTime();
        Programming todayProgramming = new Programming(today);
        todayProgramming.execute();
        System.out.println("-- End");
    }
}
