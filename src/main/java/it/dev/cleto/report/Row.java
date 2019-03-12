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


    public Row(MP3Show MP3Show) {
        File file = new File(MP3Show.getPath());
        this.name = MP3Show.getName();
        this.path = MP3Show.getPath();
        this.url = MP3Show.getUrl();
        this.downloadInSec = MP3Show.getDurationDownloadInSec();
        this.dimensionInMB = calculateDimension(file);
        this.timestamp = Utils.getNowCompleteFormat();
        this.durationMp3InMins = MP3Show.getDurationMp3() + "";
    }

    private String calculateDimension(File file) {
        return "" + file.length() / (1024 * 1024);
    }

}
