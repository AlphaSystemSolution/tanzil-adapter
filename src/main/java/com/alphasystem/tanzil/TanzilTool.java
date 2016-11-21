package com.alphasystem.tanzil;

import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.tanzil.model.Chapter;
import com.alphasystem.tanzil.model.ContextHolder;
import com.alphasystem.tanzil.model.Document;
import com.alphasystem.tanzil.model.Verse;
import com.alphasystem.util.JAXBTool;
import org.apache.commons.jxpath.JXPathContext;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.util.*;

import static com.alphasystem.arabic.model.ArabicWord.concatenateWithSpace;
import static com.alphasystem.arabic.model.ArabicWord.fromUnicode;
import static com.alphasystem.tanzil.QuranScript.QURAN_SIMPLE;
import static com.alphasystem.util.AppUtil.getUrl;
import static java.lang.String.format;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author sali
 */
public final class TanzilTool {

    public static final String QURANIC_SCRIPT = "quranic_script";
    private static final String CHAPTER_NUMBER_VARIABLE_NAME = "chapterNumber";
    private static final String VERSE_NUMBER_VARIABLE_NAME = "verseNumber";
    private static final String CHAPTER_XPATH = "chapters[@chapterNumber = $chapterNumber]";
    private static final String VERSE_XPATH = "chapters[@chapterNumber = $chapterNumber]/verses[@verseNumber = $verseNumber]";
    // private static final String VERSE_RANGE_XPATH = "sura[@chapterNumber = $chapterNumber]/verses[position() >= number($fromVerse) and not (position() > number($toVerse))]";
    private static final String VERSE_RANGE_XPATH = "chapters[@chapterNumber = $chapterNumber]/verses[@verseNumber >= $fromVerse and @verseNumber <= $toVerse]";
    private static final QuranScript DEFAULT_SCRIPT = QURAN_SIMPLE;
    public static QuranScript script;
    private static TanzilTool instance;
    private static Map<QuranScript, ContextHolder> documentMap = new HashMap<>();

    private JAXBTool jaxbTool = new JAXBTool();
    private ContextHolder metaContext;

    /*
     * Do not let any one instantiate this class
     */
    private TanzilTool() {
        getMetaContext();
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
                    loadTokens(verse);
                }
            }

        });
    }

    private void loadTokens(Verse verse) {
        List<ArabicWord> tokens = verse.getTokens();
        String[] _tokens = verse.getText().split(" ");
        for (int i = 0; i < _tokens.length; i++) {
            String token = _tokens[i];
            if (isBlank(token)) {
                continue;
            }
            token = token.trim();
            ArabicWord word = fromUnicode(token);
            if (token.length() == 1) {
                // one of punctuation character
                // logic is to merge punctuation with the previous token,
                // but if it is the first token then merge it with next token
                if (i == 0) {
                    String nextToken = _tokens[++i].trim();
                    if (nextToken.length() == 1) {
                        System.out.println(String.format("Two punctuation together??? For CN: %s, VN: %s",
                                verse.getChapterNumber(), verse.getVerseNumber()));
                    }
                    tokens.add(concatenateWithSpace(word, fromUnicode(nextToken)));
                } else {
                    int lastIndex = tokens.size() - 1;
                    ArabicWord lastWord = tokens.get(lastIndex);
                    tokens.set(lastIndex, concatenateWithSpace(lastWord, word));
                }
            } else {
                tokens.add(word);
            }
        }
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

    private ContextHolder getMetaContext(){
        if(metaContext == null){

        }
        return metaContext;
    }

    public Document getDocument( QuranScript script){
        return getContext(script).getDocument();
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

    @SuppressWarnings("unchecked")
    public List<Verse> getVerseRange(int chapterNumber, int fromVerse, int toVerse, QuranScript script){
        JXPathContext jxPathContext = getContext(script).getJxPathContext();
        jxPathContext.getVariables().declareVariable(CHAPTER_NUMBER_VARIABLE_NAME, chapterNumber);
        jxPathContext.getVariables().declareVariable("fromVerse", fromVerse);
        jxPathContext.getVariables().declareVariable("toVerse", toVerse);
        final Iterator<Verse> iterate = jxPathContext.iterate(VERSE_RANGE_XPATH);
        List<Verse> verses = new ArrayList<>();
        while (iterate.hasNext()){
            verses.add(iterate.next());
        }
        return verses;
    }
}
