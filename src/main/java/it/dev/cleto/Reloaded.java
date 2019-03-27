package it.dev.cleto;

import it.dev.cleto.media.MP3Show;
import it.dev.cleto.media.Programming;
import it.dev.cleto.utils.EShow;
import it.dev.cleto.utils.Utils;

import java.text.ParseException;
import java.util.Date;


public class Reloaded {


    public static void main(String[] args) throws Exception {

        // TODO inserire i test
        // TODO creare la configuration
        // TODO refactor per i metodi custom del main
        // TODO trasformare il csv in excel

        Utils.banner("Start");

        boolean isCustomURL = false;
        boolean isCustomFromTo = false;

        if (isCustomURL) {
            EShow eShow = EShow.CR31;
            String url = "https://media.deejay.it/legacy/audio/chiamate_roma_triuno_triuno/20190313.mp3";
            String date = "20190313";
            invokeSingleDownload(eShow, url, date);
            Utils.banner("End");
            System.exit(0);
        }

        Date start = Utils.dateLastDownload();
        Date end = Utils.parseDate(Utils.getDateFormat(new Date()));

        if (isCustomFromTo) {
            String from = "20190327";
            String to = "20190327";
            start = Utils.parseDate(from);
            end = Utils.parseDate(to);
        }

        validatePeriod(start, end);

        while (continueDownload(start, end)) {
            Utils.banner("  date: " + Utils.getDateFormat(start));
            Programming programming = new Programming(start);
            programming.execute();
            start = Utils.calculateNextDay(start);
        }

        Utils.banner("End");
        System.exit(0);
    }

    private static boolean continueDownload(Date start, Date end) {
        return start.before(end) || start.equals(end);
    }

    private static void validatePeriod(Date start, Date end) {
        if (start.after(end))
            throw new RuntimeException("Invalid period - start[" + Utils.getDateFormat(start) + "] end[" + Utils.getDateFormat(end) + "]");
    }

    private static void invokeSingleDownload(EShow eShow, String url, String date) throws ParseException {
        MP3Show custom = new MP3Show(eShow, date, url);
        custom.execute();
    }
}
