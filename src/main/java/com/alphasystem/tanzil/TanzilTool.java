package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Chapter;
import com.alphasystem.tanzil.model.Document;
import com.alphasystem.tanzil.model.Verse;
import com.alphasystem.util.JAXBTool;
import org.apache.commons.jxpath.JXPathContext;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;

import static com.alphasystem.arabic.model.ArabicWord.fromUnicode;
import static com.alphasystem.tanzil.QuranScript.QURAN_SIMPLE;
import static com.alphasystem.util.AppUtil.getUrl;
import static java.lang.String.format;
import static java.lang.System.getProperty;

/**
 * @author sali
 */
public final class TanzilTool {

    public static final String QURANIC_SCRIPT = "quranic_script";
    private static final String CHAPTER_NUMBER_VARIABLE_NAME = "chapterNumber";
    private static final String VERSE_NUMBER_VARIABLE_NAME = "verseNumber";
    private static final String CHAPTER_XPATH = "sura[@chapterNumber = $chapterNumber]";
    private static final String VERSE_XPATH = "sura[@chapterNumber = $chapterNumber]/verses[@verseNumber = $verseNumber]";
    private static final QuranScript DEFAULT_SCRIPT = QURAN_SIMPLE;
    public static QuranScript script;
    private static TanzilTool instance;

    static {
        String quranic_script = getProperty(QURANIC_SCRIPT, DEFAULT_SCRIPT.name());
        try {
            script = QuranScript.valueOf(quranic_script);
        } catch (IllegalArgumentException e) {
            script = DEFAULT_SCRIPT;
        }
    }

    private JAXBTool jaxbTool = new JAXBTool();
    private JXPathContext jxPathContext;
    private Document document;
    /*
     * Do not let any one instantiate this class
     */
    private TanzilTool() {
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
                    String text = verse.getText();
                    verse.setVerse(fromUnicode(text));
                }
            }

        });
        readDocument();
    }

    public static synchronized TanzilTool getInstance() {
        if (instance == null) {
            instance = new TanzilTool();
        }
        return instance;
    }

    private void readDocument() {
        String resourcePath = format("tanzil.%s.xml", script.getScript());
        try {
            document = jaxbTool.unmarshal(Document.class, getUrl(resourcePath));
            jxPathContext = JXPathContext.newContext(document);

        } catch (IOException | JAXBException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param chapterNumber
     * @return
     */
    public Chapter getChapter(int chapterNumber) {
        jxPathContext.getVariables().declareVariable(CHAPTER_NUMBER_VARIABLE_NAME, chapterNumber);
        return (Chapter) jxPathContext.getValue(CHAPTER_XPATH);
    }

    /**
     * @param chapterNumber
     * @param verseNumber
     * @return
     */
    public Verse getVerse(int chapterNumber, int verseNumber) {
        jxPathContext.getVariables().declareVariable(CHAPTER_NUMBER_VARIABLE_NAME, chapterNumber);
        jxPathContext.getVariables().declareVariable(VERSE_NUMBER_VARIABLE_NAME, verseNumber);
        return (Verse) jxPathContext.getValue(VERSE_XPATH);
    }
}
