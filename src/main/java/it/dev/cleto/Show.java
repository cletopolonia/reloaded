package it.dev.cleto;

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
    String name;
    Date date;
    EShow eshow;

    Logger log = Logger.getLogger(Show.class);

    public Show(EShow eShow, Date date) {
        this.date = date;
        this.eshow = eShow;
        this.url = createUrl(this.eshow);
        this.name = createName(this.eshow);
    }

    public void execute() {
        if (validate()) {
            download();
            resetTag();
        }
    }

    protected void resetTag() {
        File file = new File(getName());
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
        return !findStringInFile(Utils.DOWNLOADS, getName());
    }

    protected void download() {
        try {
            Instant start = Instant.now();
            final URL url = new URL(getUrl());
            BufferedInputStream in = new BufferedInputStream(url.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(getName());
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            Instant finish = Instant.now();
            long duration = Duration.between(start, finish).toMillis();
            log.info("  file downloaded: " + getName() + " in " + TimeUnit.MILLISECONDS.toSeconds(duration));
        } catch (FileNotFoundException e) {
            log.error(e.getMessage(), e);
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
        } catch (IOException e) {
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
        return Utils.BASE_PATH + Utils.getDateFormat(getDate())
                + Utils.FILE_SEPARATOR + eShow.getName() + Utils.MP3;
    }

    protected String createUrl(EShow eShow) {
        return Utils.BASE_URL + eShow.getPartialUrl() + Utils.URL_SEPARATOR
                + Utils.getDateFormat(getDate()) + Utils.MP3;
    }
}
