package it.dev.cleto.media;

import it.dev.cleto.report.Report;
import it.dev.cleto.report.Row;
import it.dev.cleto.utils.EDays;
import it.dev.cleto.utils.EShow;
import it.dev.cleto.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.farng.mp3.AbstractMP3Tag;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class MP3Show {
    private String url;
    private String path;
    private String name;
    private int durationMp3;
    private Date date;
    private EShow eshow;
    private String durationDownloadInSec;

    private static Logger log = Logger.getLogger(MP3Show.class);

    public MP3Show(EShow eShow, Date date) {
        this.date = date;
        this.eshow = eShow;
        this.url = createUrl(eshow);
        this.name = createName(eshow);
        this.path = createPath();
    }

    public MP3Show(EShow eShow, String date, String url) throws ParseException {
        this.date = Utils.parseDate(date);
        this.eshow = eShow;
        this.url = url;
        this.name = eShow.getName();
        this.path = createPath();
    }

    public void process() {
        try {
            if (validate()) {
                download();
                Report report = new Report();
                report.addRow(createRow());
                resetTags();
                removeOriginal();
            }
        } catch (FileNotFoundException e) {
            log.error("   missing mp3: " + e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    protected Row createRow() {
        return new Row(this);
    }

    public void resetTags() throws IOException {
        File file = new File(getPath());
        MP3File mp3file = null;
        try {
            mp3file = new MP3File(file);
            if (mp3file == null) return;
            resetTagID3v1Tag(mp3file);
            updateTagID3v2Tag(file.getName(), mp3file);
            mp3file.save();
        } catch (TagException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void resetTagID3v1Tag(MP3File mp3file) throws IOException {
        if (mp3file.getID3v1Tag() == null) return;
        mp3file.delete(mp3file.getID3v1Tag());
    }

    private void updateTagID3v2Tag(String name, MP3File mp3file) {
        AbstractMP3Tag id3v2Tag = mp3file.getID3v2Tag();
        if (id3v2Tag == null) return;
        id3v2Tag.setSongTitle(name);
        id3v2Tag.setLeadArtist(Utils.UNKNOWN);
        id3v2Tag.setSongGenre(Utils.UNKNOWN);
        mp3file.setID3v2Tag(id3v2Tag);
    }

    protected int calculateDuration() {
        int duration = 0;
        try {
            AudioFile audioFile = AudioFileIO.read(new File(getPath()));
            duration = audioFile.getAudioHeader().getTrackLength() / 60;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return duration;
    }

    protected void removeOriginal() {
        File original = new File(createPathOriginal());
        if (original.exists()) {
            original.delete();
        }
    }

    protected boolean validate() {
        return isEnabled() && isAvailable() && isAlreadyDownloaded();
    }

    protected boolean isAvailable() {
        String dayFormat = Utils.getDayFormat(getDate());
        EDays day = EDays.valueOf(dayFormat);
        return getEshow().getDays().contains(day);
    }

    protected boolean isEnabled() {
        return getEshow().isEnabled();
    }

    protected boolean isAlreadyDownloaded() {
        File mp3 = new File(getPath());
        return !findStringInFile(Utils.DOWNLOADS_CSV, getPath()) && !mp3.exists();
    }

    protected void download() throws IOException {
        try {
            Instant start = Instant.now();
            final URL url = new URL(getUrl());
            BufferedInputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(getPath());
            log.info("  downloading: " + getUrl() + " start at " + Utils.getTimeFormat(Date.from(start)));
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            Instant finish = Instant.now();
            log.info("  downloaded:  " + getUrl() + " finish at " + Utils.getTimeFormat(Date.from(finish)));
            long duration = Duration.between(start, finish).toMillis();
            this.durationDownloadInSec = TimeUnit.MILLISECONDS.toSeconds(duration) + "";
            this.durationMp3 = calculateDuration();
            log.info("  file created: " + getPath() + " in " + getDurationDownloadInSec() + " secs.");
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        }
    }

    protected boolean findStringInFile(String pathFile, String str) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(pathFile));
            String line = in.readLine();
            while (line != null) {
                if (line.contains(str)) return true;
                line = in.readLine();
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return false;
    }

    protected String createName(EShow eShow) {
        return eShow.getName();
    }

    protected String createPath() {
        return Utils.BASE_PATH + Utils.getDateFormat(getDate())
                + Utils.FILE_SEPARATOR + getName() + Utils.EXT_MP3;
    }

    protected String createUrlOld(EShow eShow) {
        return Utils.BASE_URL + eShow.getPartialUrl() + Utils.URL_SEPARATOR
                + Utils.getDateFormat(getDate()) + Utils.EXT_MP3;
    }

    protected String createUrl(EShow eShow) {
        Date date = getDate();
        return Utils.BASE_URL + Utils.getDatePathUrlFormat(date) + Utils.EPISODES
                + eShow.getPartialUrl() + Utils.URL_SEPARATOR
                + Utils.getDateFormat(date) + Utils.EXT_MP3;
    }

    protected String createPathOriginal() {
        return Utils.BASE_PATH + Utils.getDateFormat(getDate())
                + Utils.FILE_SEPARATOR + getName() + Utils.EXT_ORIGINAL_MP3;
    }

}
