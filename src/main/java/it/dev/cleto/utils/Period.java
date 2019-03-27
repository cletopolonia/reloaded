package it.dev.cleto.utils;

import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;
import java.util.Date;

@Getter
@Setter
public class Period {

    private Date start;
    private Date end;

    public Period() throws ParseException {
        this.start = Utils.dateLastDownload();
        this.end = Utils.parseDate(Utils.getDateFormat(new Date()));
        validatePeriod(start, end);
    }

    public Period(Date start, Date end) {
        validatePeriod(start, end);
        this.start = start;
        this.end = end;
    }

    public Period(String from, String to) throws ParseException {
        this.start = Utils.parseDate(from);
        this.end = Utils.parseDate(to);
        validatePeriod(start, end);
    }

    private static void validatePeriod(Date start, Date end) {
        if (start.after(end))
            throw new RuntimeException("Invalid period - start[" + Utils.getDateFormat(start) + "] end[" + Utils.getDateFormat(end) + "]");
    }

    public void increaseStartDate() {
        this.start = Utils.calculateNextDay(this.getStart());
    }

    public boolean continueDownload() {
        Date start = this.getStart();
        Date end = this.getEnd();
        return start.before(end) || start.equals(end);
    }
}
