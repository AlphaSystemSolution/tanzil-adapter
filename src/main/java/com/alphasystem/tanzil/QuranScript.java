package com.alphasystem.tanzil;

import static java.lang.String.format;

/**
 * @author sali
 */
public enum QuranScript implements ScriptSupport{

    QURAN_SIMPLE("quran-simple", "Simple"),

    QURAN_SIMPLE_CLEAN("quran-simple-clean", "Simple (Clean)"),

    QURAN_SIMPLE_ENHANCED("quran-simple-enhanced", "Simple (Enhanced)"),

    QURAN_SIMPLE_MIN("quran-simple-min", "Simple (Minimum)"),

    QURAN_UTHMANI("quran-uthmani", "Uthmani"),

    QURAN_UTHMANI_MIN("quran-uthmani-min", "Uthmani (Minimum)");

    private final String script;
    private final String description;

    QuranScript(String script, String description) {
        this.script = script;

        this.description = description;
    }

    @Override
    public String getScript() {
        return script;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getPath(){
        return format("tanzil.%s.xml", getScript());
    }
}
