package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Document;

import static com.alphasystem.tanzil.SearchOption.NONE;

/**
 * @author sali
 */
public final class TanzilTool {

    private static TanzilTool instance;
    private XQueryTool xQueryTool = XQueryTool.getInstance();
    private TranslationTool translationTool = TranslationTool.getInstance();

    /*
     * Do not let any one instantiate this class
     */
    private TanzilTool() {
    }

    public static synchronized TanzilTool getInstance() {
        if (instance == null) {
            instance = new TanzilTool();
        }
        return instance;
    }

    public Document getChapters() {
        return xQueryTool.getChapters();
    }

    /**
     * @param chapterNumber
     * @param script
     * @return
     */
    public Document getChapter(int chapterNumber, QuranScript script) {
        return xQueryTool.executeGetChapterByChapterNumberQuery(chapterNumber, script);
    }

    /**
     * @param chapterNumber
     * @param verseNumber
     * @return
     */
    public Document getVerse(int chapterNumber, int verseNumber, QuranScript script) {
        return xQueryTool.executeGetSingleVerseQuery(chapterNumber, verseNumber, script);
    }

    public Document getVerseRange(int chapterNumber, int fromVerse, int toVerse, QuranScript script) {
        return xQueryTool.executeGetVerseRangeQuery(chapterNumber, fromVerse, toVerse, script);
    }

    public Document getVerseRange(int chapterNumber, int fromVerse, int toVerse, QuranScript script,
                                  TranslationScript translationScript) {
        return xQueryTool.executeGetVerseRangeQuery(chapterNumber, fromVerse, toVerse, script, translationScript);
    }

    public Document search(String searchString, QuranScript script) {
        return search(searchString, NONE, script);
    }

    public Document search(String searchString, QuranScript script, TranslationScript translationScript) {
        return search(searchString, NONE, script, translationScript);
    }

    public Document search(String searchString, SearchOption searchOption, QuranScript script) {
        return xQueryTool.executeSearch(searchString, searchOption, script);
    }

    public Document search(String searchString, SearchOption searchOption, QuranScript script, TranslationScript translationScript) {
        return xQueryTool.executeSearch(searchString, searchOption, script, translationScript);
    }

}
