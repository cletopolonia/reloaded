package it.dev.cleto;

import it.dev.cleto.media.MP3Show;
import it.dev.cleto.media.Programming;
import it.dev.cleto.utils.EShow;
import it.dev.cleto.utils.Period;
import it.dev.cleto.utils.Utils;
import org.apache.log4j.Logger;

import java.text.ParseException;
import java.util.Date;


public class Reloaded {
    private static Logger log = Logger.getLogger(Reloaded.class);

    private static void start() {

        // TODO inserire i test
        // TODO creare la configuration
        // TODO trasformare il csv in excel

        Utils.banner("Start");
        try {
            Utils.disableLoggerJAudioTagger();
            invokeSingleDownload(false);
            Period period = invokePeriodCreation(false);
            while (period.continueDownload()) {
                Date elaborationDate = period.getStart();
                log.info("date: " + Utils.getDateFormat(elaborationDate));
                Programming programming = new Programming(elaborationDate);
                programming.execute();
                period.increaseStartDate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Utils.banner("End");
        System.exit(0);
    }

    private static void invokeSingleDownload(boolean isEnabled) throws ParseException {

//        String urlFixed = "https://media.deejay.it/legacy/audio/chiamate_roma_triuno_triuno/20190104.mp3";
//        String urlFixed = "https://media.deejay.it/legacy/podcast/trio/audio/20190531.mp3";
//        String urlFixed = "https://media.deejay.it/legacy/audio/deejay_chiama_italia/20190404.1.mp3";

        if (!isEnabled) return;
        log.info("invokeSingleDownload");
        String basePath = "https://media.deejay.it/";

        String pathDate = "2019/06/20";

        String realDate = pathDate.replace("/", "");

//        EShow eShow = EShow.CR31;
//        String urlFixed = basePath + pathDate + "/episodes/chiamate_roma_triuno_triuno/" + realDate +".mp3";

//        EShow eShow = EShow.DJCI;
//        String urlFixed = basePath + pathDate + "/episodes/deejay_chiama_italia/" + realDate +".mp3";

        EShow eShow = EShow.TROP;
        String urlFixed = basePath + pathDate + "/episodes/tropical_pizza/" + realDate + ".mp3";

        MP3Show custom = new MP3Show(eShow, realDate, urlFixed);
        custom.process();
        Utils.banner("End");
        System.exit(0);
    }

    private static Period invokePeriodCreation(boolean isCustomEnabled) throws ParseException {
        if (!isCustomEnabled) {
            return new Period();
        } else {
            log.info("invokePeriodCreation");
            String from = "20190827";
            String to = "20190921";
            return new Period(from, to);
        }
    }

    public static void main(String[] args) {
        Reloaded.start();
    }

}
