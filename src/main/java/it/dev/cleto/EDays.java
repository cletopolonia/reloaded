package it.dev.cleto;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum EDays {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY(false),
    SUNDAY(false);

    private boolean isWorkingDay;

    EDays() {
        this.isWorkingDay = true;
    }

    EDays(final boolean isWorkingDay) {
        this.isWorkingDay = isWorkingDay;
    }

    public static List<EDays> toList(EDays day) {
        return Arrays.asList(day);
    }

    public boolean isWorkingDay() {
        return isWorkingDay;
    }

    public static final List<EDays> workingDay() {
        return Arrays.stream(EDays.values()).filter(EDays::isWorkingDay).collect(Collectors.toList());
    }

}
