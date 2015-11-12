package com.alphasystem.tanzil.model;

/**
 * @author sali
 */
public enum Language {

    ENGLISH("en");

    private final String value;

    Language(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
