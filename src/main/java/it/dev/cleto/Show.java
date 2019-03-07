package it.dev.cleto;

import it.dev.cleto.report.Row;
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

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
public class Show {
    String url;
    String path;
    String name;
    Date date;
    EShow eshow;

    Logger log = Logger.getLogger(Show.class);

    public Show(EShow eShow, Date date) {
        this.date = date;
        this.eshow = eShow;
        this.url = createUrl(this.eshow);
        this.name = createName(this.eshow);
        this.path = createPath();
    }

    public void execute() {
        try {
            if (validate()) {
                download();
                resetTag();
                // todo add download file
                // todo report csv
                // todo rimozione .original
            }
        } catch (FileNotFoundException e) {
            log.error("   missing mp3: " + e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
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
        return !findStringInFile(Utils.DOWNLOADS, getPath());
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
            long downloadDurationInSec = TimeUnit.MILLISECONDS.toSeconds(duration);
            log.info("  file created: " + getPath() + " in " + downloadDurationInSec + " secs.");
            //Report report = new Report();
            Row row = new Row(getName(), getUrl(), getPath(), downloadDurationInSec);
            log.info(row);
            //report.addRow(row);
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
