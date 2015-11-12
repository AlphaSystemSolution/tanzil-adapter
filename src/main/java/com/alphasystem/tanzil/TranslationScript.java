package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Language;

import static com.alphasystem.tanzil.model.Language.ENGLISH;
import static java.lang.String.format;

/**
 * @author sali
 */
public enum TranslationScript {

    SAHIH(ENGLISH, "sahih");

    private final Language language;
    private final String script;

    TranslationScript(Language language, String script) {
        this.language = language;
        this.script = script;
    }

    public String getScript() {
        return script;
    }

    public Language getLanguage() {
        return language;
    }

    public String getPath() {
        return format("tanzil.translations.%s.%s.xml", getLanguage().getValue(), getScript());
    }
}
