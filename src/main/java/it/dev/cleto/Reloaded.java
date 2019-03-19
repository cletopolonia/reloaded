package it.dev.cleto;

import it.dev.cleto.media.MP3Show;
import it.dev.cleto.media.Programming;
import it.dev.cleto.utils.EShow;
import it.dev.cleto.utils.Utils;

import java.util.Calendar;
import java.util.Date;


public class Reloaded {


    public static void main(String[] args) throws Exception {

        // TODO inserire i test
        // TODO refactor per i metodi custom del main
        // TODO trasformare il csv in excel

        Utils.banner("Start");

        boolean isCustomURL = false;
        boolean isCustomFromTo = false;

        if (isCustomURL) {
            String url = "https://media.deejay.it/legacy/audio/chiamate_roma_triuno_triuno/20190313.mp3";
            String date = "20190313";
            MP3Show custom = new MP3Show(EShow.CR31, date, url);
            custom.execute();
            Utils.banner("End");
            System.exit(0);
        }

        Calendar nowCal = Calendar.getInstance();
        Date start = nowCal.getTime();
        Date end = Utils.dateLastDownload();

        if (isCustomFromTo) {
            String from = "20190313";
            String to = "20190313";
            start = Utils.parseDate(from);
            end = Utils.parseDate(to);
        }

        while (start.after(end)) {
            Utils.banner("  date: " + Utils.getDateFormat(end));
            Programming beforeYesterdayProgramming = new Programming(end);
            beforeYesterdayProgramming.execute();
            end = Utils.calculateNextDay(end);
        }

        Utils.banner("End");
        System.exit(0);
    }
}
