package it.dev.cleto.report;

import it.dev.cleto.Utils;
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
    private String dimension;
    private String timestamp;


    public Row(String name, String urlMp3, String path, long downloadDurationInSec) {
        File file = new File(path);
        this.name = name;
        this.path = path;
        this.url = urlMp3;
        this.downloadInSec = "" + downloadDurationInSec;
        this.dimension = "" + file.length();
        this.timestamp = Utils.getDateCompleteFormat();
    }


}
