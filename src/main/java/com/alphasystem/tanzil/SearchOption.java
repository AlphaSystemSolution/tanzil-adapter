package com.alphasystem.tanzil;

/**
 * @author sali
 */
public enum SearchOption {

    REMOVE_DIACRITIC("Remove Diacritic"), NONE("None");

    private final String description;

    SearchOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
