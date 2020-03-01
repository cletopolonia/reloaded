package it.dev.cleto.report;

import it.dev.cleto.media.MP3Show;
import it.dev.cleto.utils.Utils;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;

@Getter
@Setter
@ToString
public class Row {

    private String name;
    private String url;
    private String downloadInSec;
    private String path;
    private String dimensionInMB;
    private String timestamp;
    private String durationMp3InMins;


    public Row(MP3Show mp3Show) {
        File file = new File(mp3Show.getPath());
        this.name = mp3Show.getName();
        this.path = mp3Show.getPath();
        this.url = mp3Show.getUrl();
        this.downloadInSec = mp3Show.getDurationDownloadInSec();
        this.dimensionInMB = calculateDimension(file);
        this.timestamp = Utils.getNowCompleteFormat();
        this.durationMp3InMins = mp3Show.getDurationMp3() + "";
    }

    private String calculateDimension(File file) {
        return "" + file.length() / (1024 * 1024);
    }

}
