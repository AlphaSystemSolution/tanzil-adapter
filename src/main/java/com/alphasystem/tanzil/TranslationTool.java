package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Chapter;
import com.alphasystem.tanzil.model.Document;
import com.alphasystem.tanzil.model.TranslationDocument;
import com.alphasystem.tanzil.model.Verse;
import com.alphasystem.util.JAXBTool;
import org.apache.commons.jxpath.JXPathContext;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.alphasystem.util.AppUtil.getUrl;
import static java.lang.String.format;

/**
 * @author sali
 */
public class TranslationTool {

    private static final String CHAPTER_NUMBER_VARIABLE_NAME = "chapterNumber";
    private static final String VERSE_NUMBER_VARIABLE_NAME = "verseNumber";
    private static final String CHAPTER_XPATH = "sura[@chapterNumber = $chapterNumber]";
    private static final String VERSE_XPATH = "sura[@chapterNumber = $chapterNumber]/verses[@verseNumber = $verseNumber]";

    private static TranslationTool instance = new TranslationTool();
    private static Map<TranslationScript, TranslationDocument> documentMap = new HashMap<>();
    private JAXBTool jaxbTool = new JAXBTool();

    private TranslationTool() {
        jaxbTool.withUnMarshallerListener(new Unmarshaller.Listener() {

            @Override
            public void afterUnmarshal(Object target, Object parent) {
                super.afterUnmarshal(target, parent);
                Chapter chapter = null;
                if (parent != null && Chapter.class.isAssignableFrom(parent.getClass())) {
                    chapter = (Chapter) parent;
                }
                Verse verse = null;
                if (target != null && Verse.class.isAssignableFrom(target.getClass())) {
                    verse = (Verse) target;
                }
                if (chapter != null && verse != null) {
                    verse.setChapterNumber(chapter.getChapterNumber());
                }
            }

        });
    }

    public static TranslationTool getInstance() {
        return instance;
    }

    private TranslationDocument getDocument(TranslationScript script) {
        TranslationDocument translationDocument = documentMap.get(script);
        if (translationDocument == null) {
            try {
                Document document = jaxbTool.unmarshal(Document.class, getUrl(script.getPath()));
                JXPathContext jxPathContext = JXPathContext.newContext(document);
                translationDocument = new TranslationDocument(document, jxPathContext);
                documentMap.put(script, translationDocument);
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }
        }
        if (translationDocument == null) {
            throw new NullPointerException(format("No document found for script {%s}", script.getPath()));
        }
        return translationDocument;
    }

    /**
     * Returns the entire chapter for given <code>chapterNumber</code> and for {@link TranslationScript}.
     *
     * @param chapterNumber chapter number
     * @param script        translation for given script
     * @return {@link Chapter}
     * @throws NullPointerException if script is null or there is no such document
     */
    public Chapter getChapter(int chapterNumber, TranslationScript script) throws NullPointerException {
        TranslationDocument translationDocument = getDocument(script);
        JXPathContext jxPathContext = translationDocument.getJxPathContext();
        jxPathContext.getVariables().declareVariable(CHAPTER_NUMBER_VARIABLE_NAME, chapterNumber);
        return (Chapter) jxPathContext.getValue(CHAPTER_XPATH);
    }

    /**
     * @param chapterNumber chapter number
     * @param verseNumber   vserse numer
     * @param script        translation for given script
     * @return {@link Verse}
     * @throws NullPointerException if script is null or there is no such document
     */
    public Verse getVerse(int chapterNumber, int verseNumber, TranslationScript script) throws NullPointerException {
        TranslationDocument translationDocument = getDocument(script);
        JXPathContext jxPathContext = translationDocument.getJxPathContext();
        jxPathContext.getVariables().declareVariable(CHAPTER_NUMBER_VARIABLE_NAME, chapterNumber);
        jxPathContext.getVariables().declareVariable(VERSE_NUMBER_VARIABLE_NAME, verseNumber);
        return (Verse) jxPathContext.getValue(VERSE_XPATH);
    }
}
