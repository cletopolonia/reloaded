package it.dev.cleto.report;

import it.dev.cleto.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
public class Report {

    private static final String NAME = "name";
    private static final String TIMESTAMP = "timestamp";
    private static final String URL = "url";
    private static final String DURATION_DOWNLOAD = "download (in secs)";
    private static final String PATH = "path";
    private static final String DIMENSION = "MB";
    private static final String DURATION_SONG = "min";
    private static final String LINE = System.lineSeparator();
    private static final String SEPARATOR = ",";

    Logger log = Logger.getLogger(Report.class);

    private File report;

    public Report() {
        report = new File(Utils.DOWNLOADS_CSV);
        if (!report.exists()) {
            try {
                report.createNewFile();
                writeUsingOutputStream(header());
                log.info("csv report created");
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public String header() {
        final String[] properties = new String[]{TIMESTAMP, PATH, NAME, DURATION_DOWNLOAD, DIMENSION, DURATION_SONG, URL};
        return Arrays.asList(properties).stream().collect(Collectors.joining(SEPARATOR)) + LINE;
    }

    protected String toColumn(final Object object) {
        return Optional.ofNullable(object).map(o -> o + SEPARATOR).orElse(empty());
    }

    protected String empty() {
        return SEPARATOR;
    }

    private void writeUsingOutputStream(String data) {
        OutputStream os = null;
        try {
            os = new FileOutputStream(getReport());
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }

    public void addRow(Row row) throws IOException {
        BufferedWriter output = new BufferedWriter(new FileWriter(Utils.DOWNLOADS_CSV, true));
        output.append(rowBuilder(row));
        output.append(LINE);
        output.close();
    }

    protected String rowBuilder(Row row) {
        final StringBuilder builder = new StringBuilder();
        builder.append(Optional.ofNullable(row.getTimestamp()).map(string -> toColumn(string)).orElse(empty()));
        builder.append(Optional.ofNullable(row.getPath()).map(string -> toColumn(string)).orElse(empty()));
        builder.append(Optional.ofNullable(row.getName()).map(string -> toColumn(string)).orElse(empty()));
        builder.append(Optional.ofNullable(row.getDownloadInSec()).map(string -> toColumn(string)).orElse(empty()));
        builder.append(Optional.ofNullable(row.getDimensionInMB()).map(string -> toColumn(string)).orElse(empty()));
        builder.append(Optional.ofNullable(row.getDurationMp3InMins()).map(string -> toColumn(string)).orElse(empty()));
        builder.append(Optional.ofNullable(row.getUrl()).orElse(empty()));
        return builder.toString();
    }
}
