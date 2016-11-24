package com.alphasystem.tanzil.model;

/**
 * @author sali
 */
public enum Language {

    ENGLISH("en", "English");

    private final String description;
    private final String value;

    Language(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
