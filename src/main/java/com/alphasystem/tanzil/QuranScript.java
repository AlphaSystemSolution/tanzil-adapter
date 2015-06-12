package com.alphasystem.tanzil;

/**
 * @author sali
 */
public enum QuranScript {

    QURAN_SIMPLE("quran-simple"),

    QURAN_SIMPLE_CLEAN("quran-simple-clean"),

    QURAN_SIMPLE_ENHANCED("quran-simple-enhanced"),

    QURAN_SIMPLE_MIN("quran-simple-min"),

    QURAN_UTHMANI("quran-uthmani"),

    QURAN_UTHMANI_MIN("quran-uthmani-min");

    private final String script;

    QuranScript(String script) {
        this.script = script;

    }

    public String getScript() {
        return script;
    }
}
