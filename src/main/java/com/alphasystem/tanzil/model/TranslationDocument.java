package com.alphasystem.tanzil.model;

import org.apache.commons.jxpath.JXPathContext;

/**
 * @author sali
 */
public class TranslationDocument {

    private final JXPathContext jxPathContext;
    private final Document document;

    public TranslationDocument(Document document, JXPathContext jxPathContext) {
        this.document = document;
        this.jxPathContext = jxPathContext;
    }

    public Document getDocument() {
        return document;
    }

    public JXPathContext getJxPathContext() {
        return jxPathContext;
    }
}
