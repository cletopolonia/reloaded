package it.dev.cleto.utils;

import lombok.Getter;

import java.util.List;

@Getter
public enum EShow {

    // OLD urls
    OLD_CR31("cr31", true, false, EDays.workingDays(), "chiamate_roma_triuno_triuno"),
    OLD_DJCI("djci", true, false, EDays.workingDays(), "deejay_chiama_italia"),
    OLD_EPCC("epcc", true, false, EDays.workingDays(), "catteland"),
    OLD_TROP("niki", true, false, EDays.workingDays(), "tropical_pizza"),
    OLD_PINO("pino", true, false, EDays.workingDays(), "pinocchio"),
    OLD_DJFC("djfc", true, false, EDays.toList(EDays.SATURDAY), "deejay_football_club"),
    OLD_DJTC("djtc", true, false, EDays.toList(EDays.SUNDAY), "deejay_training_center"),
    OLD_CORD("cord", true, false, EDays.toList(EDays.MONDAY), "cordialmente"),

    // disabled
    EPCC("epcc", false, false, EDays.workingDays(), "catteland"),
    PINO("pino", false, false, EDays.workingDays(), "pinocchio"),
    TROP("niki", false, false, EDays.workingDays(), "tropical_pizza"),
    CORD("cord", false, false, EDays.toList(EDays.MONDAY), "cordialmente"),

    // current
    CR31("cr31", false, true, EDays.workingDays(), "chiamate_roma_triuno_triuno"),
    DJCI("djci", false, true, EDays.workingDays(), "deejay_chiama_italia"),
    S_CA("s_ca", false, true, EDays.workingDays(), "summer_camp"),
    DJFC("djfc", false, true, EDays.toList(EDays.SATURDAY), "deejay_football_club"),
    DJTC("djtc", false, true, EDays.toList(EDays.SUNDAY), "deejay_training_center"),
    ;

    private String name;
    private boolean enabled;
    private boolean hasOldUrl;
    private List<EDays> days;
    private String partialUrl;

    EShow(String name, boolean hasOldUrl, boolean enabled, List<EDays> days, String partialUrl) {
        this.partialUrl = partialUrl;
        this.name = name;
        this.days = days;
        this.enabled = enabled;
        this.hasOldUrl = hasOldUrl;
    }
}
