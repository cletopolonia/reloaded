package it.dev.cleto;

import it.dev.cleto.media.MP3Show;
import it.dev.cleto.media.Programming;
import it.dev.cleto.utils.EShow;
import it.dev.cleto.utils.Utils;

import java.util.Calendar;
import java.util.Date;


public class Reloaded {


    public static void main(String[] args) throws Exception {

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
        Date now = nowCal.getTime();
        Date lastDownload = Utils.dateLastDownload();

        if (isCustomFromTo) {
            String start = "20190313";
            String stop = "20190313";
            now = Utils.parseDate(start);
            lastDownload = Utils.parseDate(stop);
        }

        while (now.after(lastDownload)) {
            Utils.banner("  date: " + Utils.getDateFormat(lastDownload));
            Programming beforeYesterdayProgramming = new Programming(lastDownload);
            beforeYesterdayProgramming.execute();
            lastDownload = Utils.calculateNextDay(lastDownload);
        }

        Utils.banner("End");
        System.exit(0);
    }
}
