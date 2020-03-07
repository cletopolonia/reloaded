package it.dev.cleto.spring.entities;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum DaysTime {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY(false),
    SUNDAY(false);

    private boolean isWorkingDay;

    DaysTime() {
        this.isWorkingDay = true;
    }

    DaysTime(final boolean isWorkingDay) {
        this.isWorkingDay = isWorkingDay;
    }

    public static List<DaysTime> toList(DaysTime day) {
        return Arrays.asList(day);
    }

    public boolean isWorkingDay() {
        return isWorkingDay;
    }

    public static List<DaysTime> workingDay() {
        return Arrays.stream(DaysTime.values()).filter(DaysTime::isWorkingDay).collect(Collectors.toList());
    }
}


