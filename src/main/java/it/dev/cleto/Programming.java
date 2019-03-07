package it.dev.cleto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Getter
@Setter
public class Programming {
    private Date date;
    private ArrayList<Show> showList;

    public Programming(Date date, ArrayList<Show> showList) {
        this.date = date;
        this.showList = showList;
    }

    public Programming(Date date) {
        this.date = date;
        this.showList = new ArrayList<>();
//        showList.add(new Show(EShow.CR31, date));
//        showList.add(new Show(EShow.DJCI, date));
//        showList.add(new Show(EShow.TROP, date));
//        showList.add(new Show(EShow.DJFC, date));
//        showList.add(new Show(EShow.CORD, date));
//        showList.add(new Show(EShow.PINO, date));

        Arrays.stream(EShow.values()).forEach(eShow -> this.showList.add(new Show(eShow, this.date)));
//        this.showList = showList;
    }

    public void execute() {
        showList.stream().forEach(s -> s.execute());
    }
}
