package com.alphasystem.tanzil.test;

import com.alphasystem.arabic.model.ArabicLetterType;
import com.alphasystem.arabic.model.ArabicWord;
import com.alphasystem.tanzil.TanzilTool;
import com.alphasystem.tanzil.TranslationTool;
import com.alphasystem.tanzil.model.Chapter;
import com.alphasystem.tanzil.model.Document;
import com.alphasystem.tanzil.model.Verse;
import org.testng.annotations.Test;

import java.util.List;

import static com.alphasystem.arabic.model.ArabicLetterType.*;
import static com.alphasystem.tanzil.QuranScript.*;
import static com.alphasystem.tanzil.SearchOption.NONE;
import static com.alphasystem.tanzil.SearchOption.REMOVE_DIACRITIC;
import static com.alphasystem.tanzil.TranslationScript.SAHIH;
import static java.lang.String.format;
import static org.testng.Assert.assertEquals;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class TanzilTest {

    private static final String SEARCH_STRING = ArabicWord.getWord(KAF, TA, ALIF, BA).toUnicode();
    private TanzilTool tanzilTool = TanzilTool.getInstance();
    private TranslationTool translationTool = TranslationTool.getInstance();

    @Test
    public void testGetChapters() {
        final Document document = tanzilTool.getChapters();
        final List<Chapter> chapters = document.getChapters();
        assertEquals(chapters.size(), 114);
    }

    @Test(dependsOnMethods = {"testGetChapters"})
    public void testRetrieveChapter() {
        int chapterNumber = 3;
        final Document document = tanzilTool.getChapter(chapterNumber, QURAN_SIMPLE_ENHANCED);
        Chapter chapter = document.getChapters().get(0);
        assertEquals(chapter.getChapterNumber(), chapterNumber);
        log(format("Chapter Number: %s, Number of Verses: %s", chapterNumber, chapter.getVerses().size()), true);
    }

    @Test(dependsOnMethods = {"testRetrieveChapter"})
    public void testRetrieveVerse() {
        Integer chapterNumber = 3;
        Integer verseNumber = 119;
        final Document document = tanzilTool.getVerse(chapterNumber, verseNumber, QURAN_SIMPLE_ENHANCED);
        final Chapter chapter = document.getChapters().get(0);
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
        int chapterNumber = 18;
        int fromVerse = 50;
        int toVerse = 97;
        int size = toVerse - fromVerse + 1;
        final Document document = tanzilTool.getVerseRange(chapterNumber, fromVerse, toVerse, QURAN_SIMPLE_ENHANCED);
        final Chapter chapter = document.getChapters().get(0);
        final List<Verse> verses = chapter.getVerses();
        assertEquals(verses.size(), size);
        assertEquals(verses.get(0).getVerseNumber(), fromVerse);
        assertEquals(verses.get(verses.size() - 1).getVerseNumber(), toVerse);
    }

    @Test(dependsOnMethods = {"testRetrieveVerseRange"})
    public void testQuranicPunctuations() {
        int start = 1;
        int end = 114;
        for (int chapterNumber = start; chapterNumber <= end; chapterNumber++) {
            final Document document = tanzilTool.getChapter(chapterNumber, QURAN_SIMPLE_ENHANCED);
            Chapter chapter = document.getChapters().get(0);
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
        final Document document = translationTool.getVerse(18, 10, SAHIH);
        Chapter chapter = document.getChapters().get(0);
        final Verse verse = chapter.getVerses().get(0);
        log(format("%s: %s", chapter.getChapterNumber(), verse.getText()), true);
    }

    @Test(dependsOnMethods = {"testGetTranslation"})
    public void search() {
        final Document document = tanzilTool.search(SEARCH_STRING, QURAN_SIMPLE_CLEAN);
        final List<Chapter> chapters = document.getChapters();
        assertEquals(chapters.isEmpty(), false);
        int totalNumOfVerses = 0;
        for (Chapter chapter : chapters) {
            totalNumOfVerses += chapter.getVerses().size();
        }
        log("" + totalNumOfVerses + " : " + chapters.size(), true);
        chapters.forEach(chapter -> {
            final List<Verse> verses = chapter.getVerses();
            verses.forEach(verse -> log(format("%s:%s", chapter.getChapterNumber(), verse.getVerseNumber()), true));
        });
    }

    @Test(dependsOnMethods = {"search"})
    public void searchUthmaniRemoveDiacritic() {
        final Document document = tanzilTool.search(SEARCH_STRING, REMOVE_DIACRITIC, QURAN_UTHMANI);
        final List<Chapter> chapters = document.getChapters();
        int totalNumOfVerses = 0;
        for (Chapter chapter : chapters) {
            totalNumOfVerses += chapter.getVerses().size();
        }
        log("" + totalNumOfVerses + " : " + chapters.size(), true);
        chapters.forEach(chapter -> {
            final List<Verse> verses = chapter.getVerses();
            verses.forEach(verse -> log(format("%s:%s", chapter.getChapterNumber(), verse.getVerseNumber()), true));
        });
    }

    @Test(dependsOnMethods = {"searchUthmaniRemoveDiacritic"})
    public void searchUthmani() {
        final Document document = tanzilTool.search(SEARCH_STRING, NONE, QURAN_UTHMANI);
        final List<Chapter> chapters = document.getChapters();
        assertEquals(chapters.isEmpty(), true);
    }


}
