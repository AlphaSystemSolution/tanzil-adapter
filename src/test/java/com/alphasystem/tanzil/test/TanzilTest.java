package com.alphasystem.tanzil.test;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.tanzil.TanzilTool;
import com.alphasystem.tanzil.TranslationTool;
import com.alphasystem.tanzil.model.Chapter;
import com.alphasystem.tanzil.model.Verse;
import org.testng.annotations.Test;

import java.util.List;

import static com.alphasystem.tanzil.QuranScript.QURAN_SIMPLE_ENHANCED;
import static com.alphasystem.tanzil.TranslationScript.SAHIH;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class TanzilTest {

    private TanzilTool tanzilTool = TanzilTool.getInstance();
    private TranslationTool translationTool = TranslationTool.getInstance();

    @Test
    public void testRetrieveChapter() {
        Integer chapterNumber = 3;
        Chapter chapter = tanzilTool.getChapter(chapterNumber, QURAN_SIMPLE_ENHANCED);
        assertEquals(chapter.getChapterNumber(), chapterNumber);
        log(format("Chapter Number: %s, Number of Verses: %s", chapterNumber, chapter.getVerses().size()), true);
    }

    @Test(dependsOnMethods = {"testRetrieveChapter"})
    public void testRetrieveVerse() {
        Integer chapterNumber = 3;
        Integer verseNumber = 119;
        Verse verse = tanzilTool.getVerse(chapterNumber, verseNumber, QURAN_SIMPLE_ENHANCED);
        log(format("Chapter Number: %s, Verse Number: %s", verse.getChapterNumber(), verse.getVerseNumber()), true);
        String text = verse.getText();
        String[] tokens = text.split(" ");
        log(format("Number Of tokens: %s", tokens.length), true);
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            if (token.length() <= 1) {
                log(format("Token Number: %s - %s", (i + 1), ArabicLetterType.getByUnicode(token.charAt(0))), true);
            }
        }
        log(format("Number of tokens after merge: %s", verse.getTokens().size()), true);
    }

    @Test(dependsOnMethods = {"testRetrieveVerse"})
    public void testQuranicPunctuations() {
        int start = 1;
        int end = 114;
        for (int chapterNumber = start; chapterNumber <= end; chapterNumber++) {
            Chapter chapter = tanzilTool.getChapter(chapterNumber, QURAN_SIMPLE_ENHANCED);
            log(format("Chapter Number: %s", chapterNumber), true);
            List<Verse> verses = chapter.getVerses();
            verses.forEach(verse -> {
                String text = verse.getText();
                String[] tokens = text.split(" ");
                int index = 1;
                for (String token : tokens) {
                    if (token.length() <= 1) {
                        ArabicLetterType letter = ArabicLetterType.getByUnicode(token.charAt(0));
                        log(format("    Verse Number: %s, Token Index: %s, Letter: %s",
                                verse.getVerseNumber(), index, letter), true);
                    }
                    index++;
                }
                log(format("    Total Number of tokens for verse %s are %s", verse.getVerseNumber(), tokens.length), true);
                log(format("    Total Number of tokens for verse %s are %s", verse.getVerseNumber(),
                        verse.getTokens().size()), true);
            });
        }
    }

    @Test(dependsOnMethods = {"testQuranicPunctuations"})
    public void testGetTranslation() {
        Verse verse = translationTool.getVerse(18, 10, SAHIH);
        log(format("%s: %s", verse.getChapterNumber(), verse.getText()), true);
    }

    @Test(dependsOnMethods = {"testGetTranslation"})
    public void testGetTranslationrange() {
        Verse verse = translationTool.getVerse(18, 1, 10, SAHIH);
        //verses.forEach(verse -> log(format("%s: %s", verse.getChapterNumber(), verse.getText()), true));
        log(format("%s:%s, %s", verse.getChapterNumber(), verse.getVerseNumber(), verse.getText()), true);
    }
}
