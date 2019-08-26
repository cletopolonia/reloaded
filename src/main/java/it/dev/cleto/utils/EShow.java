package it.dev.cleto.utils;

import lombok.Getter;

import java.util.List;

@Getter
public enum EShow {

    CR31("chiamate_roma_triuno_triuno", "cr31", EDays.workingDay(), false, true),
    DJCI("deejay_chiama_italia", "djci", EDays.workingDay(), false, true),
    TROP("tropical_pizza", "niki", EDays.workingDay(), false, true),
    DJFC("deejay_football_club", "djfc", EDays.toList(EDays.SATURDAY), false, true),
    CORD("cordialmente", "cord", EDays.toList(EDays.MONDAY), false, false),
    PINO("pinocchio", "pino", EDays.workingDay(), false, false),
    DJTC("deejay_training_center", "djtc", EDays.toList(EDays.SUNDAY), true, true),
    ;

    private String partialUrl;
    private String name;
    private List<EDays> days;
    private boolean enabled;
    private boolean hasOldUrl;

    EShow(String partialUrl, String name, List<EDays> days, boolean hasOldUrl, boolean enabled) {
        this.partialUrl = partialUrl;
        this.name = name;
        this.days = days;
        this.enabled = enabled;
        this.hasOldUrl = hasOldUrl;
    }
//
//    EShow(String partialUrl, String name) {
//        this.partialUrl = partialUrl;
//        this.name = name;
//        this.days = EDays.workingDay();
//        this.enabled = true;
//    }
}
