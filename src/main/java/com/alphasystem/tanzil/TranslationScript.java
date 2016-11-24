package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Language;

import static com.alphasystem.tanzil.model.Language.ENGLISH;
import static java.lang.String.format;

/**
 * @author sali
 */
public enum TranslationScript implements ScriptSupport {

    SAHIH(ENGLISH, "sahih");

    private final Language language;
    private final String script;

    TranslationScript(Language language, String script) {
        this.language = language;
        this.script = script;
    }

    @Override
    public String getScript() {
        return script;
    }

    public Language getLanguage() {
        return language;
    }

    @Override
    public String getPath() {
        return format("tanzil.translations.%s.%s.xml", getLanguage().getValue(), getScript());
    }
}
