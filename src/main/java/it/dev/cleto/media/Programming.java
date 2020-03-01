package it.dev.cleto.media;

import com.google.common.collect.Lists;
import it.dev.cleto.utils.EShow;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class Programming {
    private Date date;
    private List<MP3Show> mp3Shows;

    public Programming(Date date, List<MP3Show> mp3Shows) {
        this.date = date;
        this.mp3Shows = mp3Shows;
    }

    public Programming(Date date) {
        this.date = date;
        this.mp3Shows = Lists.newArrayList();
        Arrays.stream(EShow.values()).forEach(eShow -> this.mp3Shows.add(new MP3Show(eShow, this.date)));
    }

    public void execute() {
        mp3Shows.forEach(MP3Show::process);
    }
}
