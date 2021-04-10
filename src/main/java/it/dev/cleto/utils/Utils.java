package it.dev.cleto.utils;

import lombok.extern.log4j.Log4j;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Handler;

@Log4j
public class Utils {
    private static final Integer DECORATOR_WIDTH = 80;
    private static final String DATE_FORMAT = "yyyyMMdd";
    private static final String DAY_FORMAT = "EEEE";
    private static final String TIME_FORMAT = "HH:mm:ss";
    private static final String DATE_PATH_URL_FORMAT = "yyyy/MM/dd";
    private static final String COMMA = ",";
    private static final Locale LOCALE = Locale.ENGLISH;
    private static SimpleDateFormat sdfDate = new SimpleDateFormat(DATE_FORMAT, LOCALE);
    private static SimpleDateFormat sdfDay = new SimpleDateFormat(DAY_FORMAT, LOCALE);
    private static SimpleDateFormat sdfTime = new SimpleDateFormat(TIME_FORMAT, LOCALE);
    private static SimpleDateFormat sdfDatePathUrl = new SimpleDateFormat(DATE_PATH_URL_FORMAT, LOCALE);

    public static final String EXT_MP3 = ".mp3";
    public static final String EXT_ORIGINAL_MP3 = ".original.mp3";

    public static final String URL_SEPARATOR = "/";
    public static final String FILE_SEPARATOR = "_";
    public static final String LINE = System.lineSeparator();

    public static final String BASE_URL_OLD = "https://media.deejay.it/legacy/audio/";
    public static final String BASE_URL = "https://media.deejay.it/";
    public static final String EPISODES = "/episodes/";
    public static final String BASE_PATH = "D:\\musica\\reloaded\\";
    public static final String DOWNLOADS_CSV = "D:\\musica\\reloaded\\downloads.csv";

    public static final String UNKNOWN = "Unknown";


    public static String getDateFormat(Date date) {
        return sdfDate.format(date);
    }

    public static String getDayFormat(Date date) {
        return sdfDay.format(date).toUpperCase();
    }

    public static String getTimeFormat(Date date) {
        return sdfTime.format(date);
    }

    public static String getDateCompleteFormat(Date date) {
        return getDateFormat(date) + " " + getTimeFormat(date);
    }

    private static Date parseTime(String date) throws ParseException {
        return sdfTime.parse(date);
    }

    public static Date parseDate(String date) throws ParseException {
        return sdfDate.parse(date);
    }

    public static String getNowCompleteFormat() {
        Date now = new Date();
        return getDateCompleteFormat(now);
    }

    public static void banner(String message) {
        log.info(mark(message));
    }

    protected static String decorator() {
        final char[] decorator = new char[DECORATOR_WIDTH];
        Arrays.fill(decorator, '=');
        return (new String(decorator)) + LINE;
    }

    protected static String mark(final String message) {
        return LINE +
                decorator() +
                message + LINE +
                decorator();
    }

    public static Date dateLastDownload() {
        String res = getLastLine(DOWNLOADS_CSV);
        String[] array = res.split(COMMA);
        String dateString = array[0];
        try {
            return parseDate(dateString);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
        }
        return new Date();
    }

    protected static String getLastLine(String path) {
        List<String> result = new ArrayList<>();
        FileReader in = null;
        try {
            in = new FileReader(path);
        } catch (FileNotFoundException e) {
            log.error(e);
        }
        try (BufferedReader reader = new BufferedReader(in)) {
            String line = reader.readLine();
            while (line != null) {
                result.add(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return result.get(result.size() - 1);
    }

    public static Date calculateNextDay(Date now) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, 1);
        return cal.getTime();
    }

    public static void disableLoggerJAudioTagger() {
        java.util.logging.Logger globalLogger = java.util.logging.Logger.getLogger("");
        Handler[] handlers = globalLogger.getHandlers();
        for (Handler handler : handlers) {
            globalLogger.removeHandler(handler);
        }
    }

    public static String getDatePathUrlFormat(Date date) {
        return sdfDatePathUrl.format(date);
    }

    public static String getVersion(Date date) {
        // v1 --> fino al 2019/05/30 circa
        // v2 --> fino al 08/03/2021 compreso
        // v3 --> dal 09/03/2021

        Calendar v1Date = new GregorianCalendar();
        v1Date.set(Calendar.DAY_OF_MONTH,30);
        v1Date.set(Calendar.MONTH,4);
        v1Date.set(Calendar.YEAR,2019);

        Calendar v2Date = new GregorianCalendar();
        v2Date.set(Calendar.DAY_OF_MONTH,8);
        v2Date.set(Calendar.MONTH,2);
        v2Date.set(Calendar.YEAR,2021);

        if(date.before(v1Date.getTime()))
            return "v1";
        if(date.after(v2Date.getTime()))
            return "v3";
        else
            return "v2";
    }
}
