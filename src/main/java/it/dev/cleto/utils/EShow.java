package it.dev.cleto.utils;

import lombok.Getter;

import java.util.List;

@Getter
public enum EShow {

    CR31("cr31", EDays.workingDay(),  true, "chiamate_roma_triuno_triuno"),
    DJCI("djci", EDays.workingDay(),  false, "deejay_chiama_italia"),
    EPCC("epcc", EDays.workingDay(),  false, "catteland"),
    TROP("niki", EDays.workingDay(),  false, "tropical_pizza"),
    PINO("pino", EDays.workingDay(),  false, "pinocchio"),
    DJFC("djfc", EDays.toList(EDays.SATURDAY),  false, "deejay_football_club"),
    DJTC("djtc", EDays.toList(EDays.SUNDAY),  false, "deejay_training_center"),
    CORD("cord", EDays.toList(EDays.MONDAY), false, "cordialmente"),
    ;

    private String partialUrl;
    private String name;
    private List<EDays> days;
    private boolean enabled;


    EShow(String name, List<EDays> days, boolean enabled, String partialUrl) {
        this.partialUrl = partialUrl;
        this.name = name;
        this.days = days;
        this.enabled = enabled;
    }
}
