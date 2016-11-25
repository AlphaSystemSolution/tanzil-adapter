package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Document;

/**
 * @author sali
 */
public class TranslationTool {

    private static TranslationTool instance = new TranslationTool();
    private XQueryTool xQueryTool = XQueryTool.getInstance();

    private TranslationTool() {
    }

    public static TranslationTool getInstance() {
        return instance;
    }

    /**
     * Returns the entire chapter for given <code>chapterNumber</code> and for {@link TranslationScript}.
     *
     * @param chapterNumber chapter number
     * @param script        translation for given script
     * @return {@link Document}
     * @throws RuntimeException if script is null or there is no such document
     */
    public Document getChapter(int chapterNumber, TranslationScript script) throws RuntimeException {
        return xQueryTool.executeGetChapterByChapterNumberQuery(chapterNumber, script);
    }

    /**
     * @param chapterNumber chapter number
     * @param verseNumber   vserse numer
     * @param script        translation for given script
     * @return {@link Document}
     * @throws RuntimeException if script is null or there is no such document
     */
    public Document getVerse(int chapterNumber, int verseNumber, TranslationScript script) throws RuntimeException {
        return xQueryTool.executeGetSingleVerseQuery(chapterNumber, verseNumber, script);
    }

    public Document getVerseRange(int chapterNumber, int firstVerseNumber, int lastVerseNumber, TranslationScript script)
            throws RuntimeException {
        return xQueryTool.executeGetVerseRangeQuery(chapterNumber, firstVerseNumber, lastVerseNumber, script);
    }
}
