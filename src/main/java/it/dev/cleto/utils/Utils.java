package it.dev.cleto.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class Utils {

    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final String DAY_FORMAT = "EEEE";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat(DATE_FORMAT);
    private static final SimpleDateFormat SDF_DAY = new SimpleDateFormat(DAY_FORMAT);
    private static final SimpleDateFormat SDF_TIME = new SimpleDateFormat(TIME_FORMAT);

    public static final String EXT_MP3 = ".mp3";
    public static final String EXT_ORIGINAL_MP3 = ".original.mp3";

    public static final String URL_SEPARATOR = "/";
    public static final String FILE_SEPARATOR = "_";
    public static final String LINE = System.lineSeparator();

    public static final String BASE_URL = "https://media.deejay.it/legacy/audio/";
    public static final String BASE_PATH = "/home/biadmin/Music/";
    //public static final String DOWNLOADS = "/home/biadmin/Music/downloads.log";
    public static final String DOWNLOADS_CSV = "/home/biadmin/Music/downloads.csv";

    private static final Integer DECORATOR_WIDTH = 80;
    public static final String UNKNOWN = "Unknown";



    public static String getDateFormat(Date date) {
        return SDF_DATE.format(date);
    }

    public static String getDayFormat(Date date) {
        return SDF_DAY.format(date).toUpperCase();
    }

    public static String getTimeFormat(Date date) {
        return SDF_TIME.format(date);
    }

    public static String getDateCompleteFormat() {
        Date now = new Date();
        return getDateFormat(now) + " " + getTimeFormat(now);

    }

    protected static String decorator() {
        final char[] decorator = new char[DECORATOR_WIDTH];
        Arrays.fill(decorator, '=');
        return (new String(decorator)) + LINE;
    }

    public static void banner(String message) {
        System.out.println(mark(message));
    }

    protected static String mark(final String message) {
        final StringBuilder mark = new StringBuilder();
        mark.append(LINE);
        mark.append(decorator());
        mark.append(message + LINE);
        mark.append(decorator());
        return mark.toString();
    }
}
