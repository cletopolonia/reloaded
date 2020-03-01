package it.dev.cleto.utils;

import lombok.Getter;

import java.util.List;

@Getter
public enum EShow {

    OLD_EPCC("epcc", EDays.workingDay(), true, true, "catteland"),
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
