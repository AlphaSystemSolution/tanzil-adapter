package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Chapter;

import java.util.List;

import static com.alphasystem.tanzil.SearchOption.NONE;

/**
 * @author sali
 */
public final class TanzilTool {

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
        return search(searchString, NONE, script);
    }

    public List<Chapter> search(String searchString, SearchOption searchOption, QuranScript script) {
        return xQueryTool.executeSearch(searchString, searchOption, script);
    }

}
