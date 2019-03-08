package it.dev.cleto.media;

import it.dev.cleto.report.Report;
import it.dev.cleto.report.Row;
import it.dev.cleto.utils.EDays;
import it.dev.cleto.utils.EShow;
import it.dev.cleto.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;
import org.farng.mp3.MP3File;
import org.farng.mp3.TagConstant;
import org.farng.mp3.TagException;
import org.farng.mp3.TagOptionSingleton;
import org.farng.mp3.id3.AbstractID3v2;
import org.farng.mp3.id3.AbstractID3v2Frame;
import org.farng.mp3.id3.FrameBodyTIT2;
import org.farng.mp3.id3.ID3v1;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class MP3Show {
    String url;
    String path;
    String name;
    int durationMp3;
    Date date;
    EShow eshow;
    String durationDownloadInSec;

    Logger log = Logger.getLogger(MP3Show.class);

    public MP3Show(EShow eShow, Date date) {
        this.date = date;
        this.eshow = eShow;
        this.url = createUrl(eshow);
        this.name = createName(eshow);
        this.path = createPath();
    }

    public void execute() {
        try {
            if (validate()) {
                download();
                resetTag();
                Report report = new Report();
                report.addRow(createRow());
                // todo switch check on csv
                // todo remove .original
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

    protected void resetTag() {
        File file = new File(getPath());
        MP3File mp3file = null;
        try {
            mp3file = new MP3File(file);
            TagOptionSingleton.getInstance().setDefaultSaveMode(TagConstant.MP3_FILE_SAVE_WRITE);
            ID3v1 id3v1 = mp3file.getID3v1Tag();
            AbstractID3v2 id3v2 = mp3file.getID3v2Tag();
            AbstractID3v2Frame frame = id3v2.getFrame(Utils.TITLE_TAG);
            resetTitle(file.getName(), id3v1, id3v2, frame);
            // todo reset other fields
            mp3file.save();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (TagException e) {
            log.error(e.getMessage(), e);
        }
    }

    protected void resetTitle(String title, ID3v1 id3v1, AbstractID3v2 id3v2, AbstractID3v2Frame frame) {
        id3v2.setSongTitle(title);
        ((FrameBodyTIT2) frame.getBody()).setText(title);
        id3v1.setSongTitle(title);
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
                + Utils.FILE_SEPARATOR + getName() + Utils.MP3;
    }

    protected String createUrl(EShow eShow) {
        return Utils.BASE_URL + eShow.getPartialUrl() + Utils.URL_SEPARATOR
                + Utils.getDateFormat(getDate()) + Utils.MP3;
    }

}
