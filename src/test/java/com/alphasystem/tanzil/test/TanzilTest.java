package com.alphasystem.tanzil.test;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.tanzil.MetaTool;
import com.alphasystem.tanzil.QuranScript;
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
import static org.testng.Assert.assertNotNull;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class TanzilTest {

    private TanzilTool tanzilTool = TanzilTool.getInstance();
    private MetaTool metaTool = MetaTool.getInstance();
    private TranslationTool translationTool = TranslationTool.getInstance();

    @Test
    public void testRetrieveChapter() {
        int chapterNumber = 3;
        Chapter chapter = tanzilTool.getChapter(chapterNumber, QURAN_SIMPLE_ENHANCED);
        assertEquals(chapter.getChapterNumber(), chapterNumber);
        log(format("Chapter Number: %s, Number of Verses: %s", chapterNumber, chapter.getVerses().size()), true);
    }

    @Test(dependsOnMethods = {"testRetrieveChapter"})
    public void testRetrieveVerse() {
        Integer chapterNumber = 3;
        Integer verseNumber = 119;
        final Chapter chapter = tanzilTool.getVerse(chapterNumber, verseNumber, QURAN_SIMPLE_ENHANCED);
        Verse verse = chapter.getVerses().get(0);
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
    public void testRetrieveVerseRange() {
        Integer chapterNumber = 18;
        Integer fromVerse = 50;
        Integer toVerse = 97;
        int size = toVerse - fromVerse + 1;
        final Chapter chapter = tanzilTool.getVerseRange(chapterNumber, fromVerse, toVerse, QURAN_SIMPLE_ENHANCED);
        final List<Verse> verses = chapter.getVerses();
        for (Verse verse : verses) {
            System.out.println(verse.getChapterNumber() + ":" + verse.getVerseNumber());
        }
        assertEquals(verses.size(), size);
    }

    @Test(dependsOnMethods = {"testRetrieveVerseRange"})
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
    public void testMetaChapter(){
        int chapterNumber = 2;
        final com.alphasystem.tanzil.meta.model.Chapter chapter = metaTool.getChapter(chapterNumber);
        assertNotNull(chapter);
        log(format("ChapterNumber: %s, VerseCount: %s", chapter.getChapterNumber(), chapter.getVerseCount()), true);
    }

    // @Test(dependsOnMethods = {"testGetTranslation"})
    @SuppressWarnings({"unused"})
    public void testGetTokens() {
        for (QuranScript quranScript : QuranScript.values()) {
            testGetTokens(8, quranScript);
        }
    }

    private void testGetTokens(int chapterNumber, QuranScript script) {
        Chapter chapter = tanzilTool.getChapter(chapterNumber, script);
        final List<Verse> verses = chapter.getVerses();
        int totalWords = 0;
        int totalLetters = 0;
        for (Verse verse : verses) {
            final List<ArabicWord> tokens = verse.getTokens();
            final int tokenCount = tokens.size();
            totalWords += tokenCount;
            for (ArabicWord token : tokens) {
                totalLetters += token.getLength();
            }
        }
        log(format("%s:- Total Words: %s, Total Letters: %s", script.name(), totalWords, totalLetters), true);
    }

}
