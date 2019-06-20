//package it.dev.cleto;
//
//import it.dev.cleto.media.MP3Show;
//import it.dev.cleto.report.Report;
//import it.dev.cleto.utils.EShow;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//import java.util.Calendar;
//import java.util.Date;
//
//
///**
// * Unit test for simple Reloaded.
// */
//public class ReloadedTest {
//    /**
//     * Rigorous Test :-)
//     */
//    @Test
//    public void shouldAnswerWithTrue() {
//        String FILE_URL = "https://www.unipg.it/files/pagine/410/4-PDF-A.pdf";
//        String FILE_NAME = "/home/biadmin/Music/20190305_cr31.mp3";
//        assert (false);
//    }
//
//    @Test
//    public void reportExists() {
//        Report p = new Report();
//        p.getReport();
//    }
//
//    @Test
//    public void resetTagTest() throws IOException {
//        Calendar todayCal = Calendar.getInstance();
//        todayCal.add(Calendar.DATE, -4);
//        Date beforeYesterday = todayCal.getTime();
//        MP3Show mp3 = new MP3Show(EShow.TROP, beforeYesterday);
//        mp3.resetTags();
//    }
//}
