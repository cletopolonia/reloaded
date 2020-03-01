package it.dev.cleto.utils;

import lombok.Getter;

import java.util.List;

@Getter
public enum EShow {

    OLD_CR31("cr31", EDays.workingDay(), true, false, "chiamate_roma_triuno_triuno"),
    OLD_DJCI("djci", EDays.workingDay(), true, false, "deejay_chiama_italia"),
    OLD_EPCC("epcc", EDays.workingDay(), true, true, "catteland"),
    OLD_TROP("niki", EDays.workingDay(), true, false, "tropical_pizza"),
    OLD_PINO("pino", EDays.workingDay(), true, false, "pinocchio"),
    OLD_DJFC("djfc", EDays.toList(EDays.SATURDAY), true, false, "deejay_football_club"),
    OLD_DJTC("djtc", EDays.toList(EDays.SUNDAY), true, false, "deejay_training_center"),
    OLD_CORD("cord", EDays.toList(EDays.MONDAY), true, false, "cordialmente"),
    CR31("cr31", EDays.workingDay(), false, true, "chiamate_roma_triuno_triuno"),
    DJCI("djci", EDays.workingDay(), false, true, "deejay_chiama_italia"),
    EPCC("epcc", EDays.workingDay(), false, true, "catteland"),
    TROP("niki", EDays.workingDay(), false, true, "tropical_pizza"),
    PINO("pino", EDays.workingDay(), false, false, "pinocchio"),
    DJFC("djfc", EDays.toList(EDays.SATURDAY), false, true, "deejay_football_club"),
    DJTC("djtc", EDays.toList(EDays.SUNDAY), false, true, "deejay_training_center"),
    CORD("cord", EDays.toList(EDays.MONDAY), false, false, "cordialmente"),
    ;

    private String partialUrl;
    private String name;
    private List<EDays> days;
    private boolean enabled;
    private boolean hasOldUrl;

    EShow(String name, List<EDays> days, boolean hasOldUrl, boolean enabled, String partialUrl) {
        this.partialUrl = partialUrl;
        this.name = name;
        this.days = days;
        this.enabled = enabled;
        this.hasOldUrl = hasOldUrl;
    }
}
