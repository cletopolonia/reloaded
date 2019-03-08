package it.dev.cleto.utils;

import lombok.Getter;

import java.util.List;

@Getter
public enum EShow {

    CR31("chiamate_roma_triuno_triuno", "cr31"),
    DJCI("deejay_chiama_italia", "djci"),
    TROP("tropical_pizza", "niki"),
    DJFC("deejay_football_club", "djfc", EDays.toList(EDays.SATURDAY), true),
    CORD("cordialmente", "cord", EDays.toList(EDays.MONDAY), false),
    PINO("pinocchio", "pino", EDays.workingDay(), false),
    ;

    private String partialUrl;
    private String name;
    private List<EDays> days;
    private boolean enabled;

    EShow(String partialUrl, String name, List<EDays> days, boolean enabled) {
        this.partialUrl = partialUrl;
        this.name = name;
        this.days = days;
        this.enabled = enabled;
    }

    EShow(String partialUrl, String name) {
        this.partialUrl = partialUrl;
        this.name = name;
        this.days = EDays.workingDay();
        this.enabled = true;
    }
}
