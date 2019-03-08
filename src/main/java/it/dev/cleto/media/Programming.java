package it.dev.cleto.media;

import it.dev.cleto.utils.EShow;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Getter
@Setter
public class Programming {
    private Date date;
    private ArrayList<MP3Show> MP3ShowList;

    public Programming(Date date, ArrayList<MP3Show> MP3ShowList) {
        this.date = date;
        this.MP3ShowList = MP3ShowList;
    }

    public Programming(Date date) {
        this.date = date;
        this.MP3ShowList = new ArrayList<>();
        Arrays.stream(EShow.values()).forEach(eShow -> this.MP3ShowList.add(new MP3Show(eShow, this.date)));
    }

    public void execute() {
        MP3ShowList.stream().forEach(s -> s.execute());
    }
}
