package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Language;

import static com.alphasystem.tanzil.model.Language.ENGLISH;
import static java.lang.String.format;

/**
 * @author sali
 */
public enum TranslationScript implements ScriptSupport {

    SAHIH(ENGLISH, "sahih", "Shaih International");

    private final Language language;
    private final String script;
    private final String description;

    TranslationScript(Language language, String script, String description) {
        this.language = language;
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

    public Language getLanguage() {
        return language;
    }

    @Override
    public String getPath() {
        return format("tanzil.translations.%s.%s.xml", getLanguage().getValue(), getScript());
    }
}
