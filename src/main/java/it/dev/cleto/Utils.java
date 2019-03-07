package it.dev.cleto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final String DAY_FORMAT = "EEEE";
    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat(DATE_FORMAT);
    private static final SimpleDateFormat SDF_DAY = new SimpleDateFormat(DAY_FORMAT);

    public static final String MP3 = ".mp3";
    public static final String URL_SEPARATOR = "/";
    public static final String FILE_SEPARATOR = "_";
    public static final String BASE_URL = "https://media.deejay.it/legacy/audio/";
    public static final String BASE_PATH = "/home/biadmin/Music/";
    public static final String DOWNLOADS = "/home/biadmin/Music/downloads.log";
    public static final String TITLE_TAG = "TIT2";


    public static String getDateFormat(Date date) {
        return SDF_DATE.format(date);
    }

    public static String getDayFormat(Date date) {
        return SDF_DAY.format(date).toUpperCase();
    }

}
