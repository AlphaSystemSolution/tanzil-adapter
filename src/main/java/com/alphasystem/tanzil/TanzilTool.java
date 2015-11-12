package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Chapter;
import com.alphasystem.tanzil.model.ContextHolder;
import com.alphasystem.tanzil.model.Document;
import com.alphasystem.tanzil.model.Verse;
import com.alphasystem.util.JAXBTool;
import org.apache.commons.jxpath.JXPathContext;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.alphasystem.arabic.model.ArabicWord.fromUnicode;
import static com.alphasystem.tanzil.QuranScript.QURAN_SIMPLE;
import static com.alphasystem.util.AppUtil.getUrl;
import static java.lang.String.format;

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
    private static Map<QuranScript, ContextHolder> documentMap = new HashMap<>();

    private JAXBTool jaxbTool = new JAXBTool();

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
    }

    public static synchronized TanzilTool getInstance() {
        if (instance == null) {
            instance = new TanzilTool();
        }
        return instance;
    }

    private ContextHolder getContext(QuranScript script) {
        ContextHolder contextHolder = documentMap.get(script);
        if (contextHolder == null) {
            try {
                Document document = jaxbTool.unmarshal(Document.class, getUrl(script.getPath()));
                JXPathContext jxPathContext = JXPathContext.newContext(document);
                contextHolder = new ContextHolder(document, jxPathContext);
                documentMap.put(script, contextHolder);
            } catch (IOException | JAXBException e) {
                e.printStackTrace();
            }
        }
        if (contextHolder == null) {
            throw new NullPointerException(format("No document found for script {%s}", script.getPath()));
        }
        return contextHolder;
    }

    /**
     * @param chapterNumber
     * @param script
     * @return
     */
    public Chapter getChapter(int chapterNumber, QuranScript script) {
        JXPathContext jxPathContext = getContext(script).getJxPathContext();
        jxPathContext.getVariables().declareVariable(CHAPTER_NUMBER_VARIABLE_NAME, chapterNumber);
        return (Chapter) jxPathContext.getValue(CHAPTER_XPATH);
    }

    /**
     * @param chapterNumber
     * @param verseNumber
     * @return
     */
    public Verse getVerse(int chapterNumber, int verseNumber, QuranScript script) {
        JXPathContext jxPathContext = getContext(script).getJxPathContext();
        jxPathContext.getVariables().declareVariable(CHAPTER_NUMBER_VARIABLE_NAME, chapterNumber);
        jxPathContext.getVariables().declareVariable(VERSE_NUMBER_VARIABLE_NAME, verseNumber);
        return (Verse) jxPathContext.getValue(VERSE_XPATH);
    }
}
