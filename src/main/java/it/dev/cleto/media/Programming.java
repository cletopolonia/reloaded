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
    private ArrayList<MP3Show> mp3Shows;

    public Programming(Date date, ArrayList<MP3Show> mp3Shows) {
        this.date = date;
        this.mp3Shows = mp3Shows;
    }

    public Programming(Date date) {
        this.date = date;
        this.mp3Shows = new ArrayList<>();
        Arrays.stream(EShow.values()).forEach(eShow -> this.mp3Shows.add(new MP3Show(eShow, this.date)));
    }

    public void execute() {
        mp3Shows.stream().forEach(s -> s.process());
    }
}
