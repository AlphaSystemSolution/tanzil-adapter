package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Chapter;

import java.util.List;

/**
 * @author sali
 */
public final class TanzilTool {

    public static final String QURANIC_SCRIPT = "quranic_script";
    private static final String CHAPTER_NUMBER_VARIABLE_NAME = "chapterNumber";
    private static final String VERSE_RANGE_XPATH = "chapters[@chapterNumber = $chapterNumber]/verses[@verseNumber >= $fromVerse and @verseNumber <= $toVerse]";
    private static final String SEARCH_XPATH = "chapters/verses[contains(@text, $searchString)]";

    private static TanzilTool instance;
    private XQueryTool xQueryTool = XQueryTool.getInstance();

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

    /**
     * @param chapterNumber
     * @param script
     * @return
     */
    public Chapter getChapter(int chapterNumber, QuranScript script) {
        return xQueryTool.executeGetChapterByChapterNumberQuery(chapterNumber, script);
    }

    /**
     * @param chapterNumber
     * @param verseNumber
     * @return
     */
    public Chapter getVerse(int chapterNumber, int verseNumber, QuranScript script) {
        return xQueryTool.executeGetSingleVerseQuery(chapterNumber, verseNumber, script);
    }

    public Chapter getVerseRange(int chapterNumber, int fromVerse, int toVerse, QuranScript script) {
        return xQueryTool.executeGetVerseRangeQuery(chapterNumber, fromVerse, toVerse, script);
    }

    public List<Chapter> search(String searchString, QuranScript script) {
        return xQueryTool.executeSearch(searchString, script);
    }

}
