package com.alphasystem.tanzil.test;

import com.alphasystem.tanzil.TanzilTool;
import com.alphasystem.tanzil.model.Chapter;
import com.alphasystem.tanzil.model.Verse;
import org.testng.annotations.Test;

import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class TanzilTest {

    private TanzilTool tanzilTool = TanzilTool.getInstance();

    @Test
    public void testRetrieveChapter() {
        Integer chapterNumber = 1;
        Chapter chapter = tanzilTool.getChapter(chapterNumber);
        assertEquals(chapter.getChapterNumber(), chapterNumber);
        log(format("Chapter Number: %s, Number of Verses: %s", chapterNumber, chapter.getVerses().size()), true);
    }

    @Test
    public void testRetrieveVerse(){
        Integer chapterNumber = 1;
        Integer verseNumber = 2;
        Verse verse = tanzilTool.getVerse(chapterNumber, verseNumber);
        log(format("Verse Number: %s", verse.getVerseNumber()), true);
    }
}
