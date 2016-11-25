package com.alphasystem.tanzil;

import com.alphasystem.tanzil.model.Chapter;
import com.alphasystem.tanzil.model.Document;
import com.alphasystem.util.JAXBTool;
import net.sf.saxon.s9api.*;

import javax.xml.bind.JAXBException;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.alphasystem.tanzil.QuranScript.QURAN_SIMPLE_CLEAN;
import static com.alphasystem.tanzil.SearchOption.NONE;
import static com.alphasystem.util.AppUtil.getResourceAsStream;
import static com.alphasystem.util.AppUtil.readResource;
import static java.lang.String.format;

/**
 * @author sali
 */
class XQueryTool {

    private static final String GET_CHAPTERS_QUERY = "getChapters";
    private static final String GET_CHAPTER_BY_CHAPTER_NUMBER_QUERY = "getChapterByChapterNumber";
    private static final String GET_VERSE_RANGE_QUERY = "getVerseRange";
    private static final String GET_SINGLE_VERSE_QUERY = "getSingleVerse";
    private static final String SEARCH_QUERY = "search";
    private static final String SEARCH_NO_DIACRITIC_QUERY = "searchNoDiacritic";
    private static final String DOC_VARIABLE_NAME = "doc";
    private static final String DOC_CLEAN_VARIABLE_NAME = "docClean";
    private static final String CHAPTER_NUMBER_VARIABLE_NAME = "chapterNumber";
    private static final String FROM_VERSE_VARIABLE_NAME = "fromVerse";
    private static final String TO_VERSE_VARIABLE_NAME = "toVerse";
    private static final String VERSE_NUMBER_VARIABLE_NAME = "verseNumber";
    private static final String SEARCH_STRING_VARIABLE_NAME = "searchString";

    private static XQueryTool instance;
    private JAXBTool jaxbTool = new JAXBTool();
    private final DocumentBuilder documentBuilder;
    private final Map<ScriptSupport, XdmNode> documentMap = new LinkedHashMap<>();
    private final XQueryEvaluator getChapters;
    private final XQueryEvaluator getChapterByChapterNumber;
    private final XQueryEvaluator getVerseRange;
    private final XQueryEvaluator getSingleVerse;
    private final XQueryEvaluator search;
    private final XQueryEvaluator searchNoDiacritic;

    private XQueryTool() throws RuntimeException {
        Processor processor = new Processor(false);
        documentBuilder = processor.newDocumentBuilder();

        XQueryCompiler xQueryCompiler = processor.newXQueryCompiler();

        getChapters = getCompiledQuery(xQueryCompiler, GET_CHAPTERS_QUERY);
        getChapterByChapterNumber = getCompiledQuery(xQueryCompiler, GET_CHAPTER_BY_CHAPTER_NUMBER_QUERY);
        getVerseRange = getCompiledQuery(xQueryCompiler, GET_VERSE_RANGE_QUERY);
        getSingleVerse = getCompiledQuery(xQueryCompiler, GET_SINGLE_VERSE_QUERY);
        search = getCompiledQuery(xQueryCompiler, SEARCH_QUERY);
        searchNoDiacritic = getCompiledQuery(xQueryCompiler, SEARCH_NO_DIACRITIC_QUERY);

        getDocument(QURAN_SIMPLE_CLEAN);
    }

    public static synchronized XQueryTool getInstance() throws RuntimeException {
        if (instance == null) {
            instance = new XQueryTool();
        }
        return instance;
    }

    private static XQueryEvaluator getCompiledQuery(final XQueryCompiler xQueryCompiler, String queryName)
            throws RuntimeException {
        String query;
        final String resourcePath = format("queries.%s.xq", queryName);
        try {
            query = readResource(resourcePath);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(format("Unable to read resource {%s}", resourcePath), e);
        }
        try {
            XQueryExecutable xQueryExecutable = xQueryCompiler.compile(query);
            return xQueryExecutable.load();
        } catch (SaxonApiException e) {
            throw new RuntimeException(format("Unable to load query {%s}", query), e);
        }
    }

    Document getChapters() throws RuntimeException {
        getChapters.setExternalVariable(new QName(DOC_VARIABLE_NAME), getDocument("tanzil.quran-data.xml"));
        try {
            final XdmValue result = getChapters.evaluate();
            return jaxbTool.unmarshal(Document.class, new ByteArrayInputStream(result.toString().getBytes()));
        } catch (SaxonApiException e) {
            throw new RuntimeException("Error running query {getChapters}", e);
        } catch (JAXBException e) {
            throw new RuntimeException("Error converting document", e);
        }
    }

    <S extends Enum<S> & ScriptSupport> Document executeGetChapterByChapterNumberQuery(int chapterNumber, S script)
            throws RuntimeException {
        getChapterByChapterNumber.setExternalVariable(new QName(DOC_VARIABLE_NAME), getDocument(script));
        getChapterByChapterNumber.setExternalVariable(new QName(CHAPTER_NUMBER_VARIABLE_NAME), new XdmAtomicValue(chapterNumber));
        try {
            final XdmValue result = getChapterByChapterNumber.evaluate();
            return jaxbTool.unmarshal(Document.class, new ByteArrayInputStream(result.toString().getBytes()));
        } catch (SaxonApiException e) {
            throw new RuntimeException(format("Error running query {getChapterByChapterNumber} for chapterNumber {%s} and script {%s}",
                    chapterNumber, script), e);
        } catch (JAXBException e) {
            throw new RuntimeException(format("Error converting document for chapterNumber {%s} and script {%s}",
                    chapterNumber, script), e);
        }
    }

    <S extends Enum<S> & ScriptSupport> Document executeGetVerseRangeQuery(int chapterNumber, int fromVerse, int toVerse,
                                                                           S script) throws RuntimeException {
        getVerseRange.setExternalVariable(new QName(DOC_VARIABLE_NAME), getDocument(script));
        getVerseRange.setExternalVariable(new QName(CHAPTER_NUMBER_VARIABLE_NAME), new XdmAtomicValue(chapterNumber));
        getVerseRange.setExternalVariable(new QName(FROM_VERSE_VARIABLE_NAME), new XdmAtomicValue(fromVerse));
        getVerseRange.setExternalVariable(new QName(TO_VERSE_VARIABLE_NAME), new XdmAtomicValue(toVerse));
        try {
            final XdmValue result = getVerseRange.evaluate();
            return jaxbTool.unmarshal(Document.class, new ByteArrayInputStream(result.toString().getBytes()));
        } catch (SaxonApiException e) {
            throw new RuntimeException(format("Error running query {getVerseRange} for chapterNumber {%s}, fromVerse {%s}, toVerse {%s} and script {%s}",
                    chapterNumber, fromVerse, toVerse, script), e);
        } catch (JAXBException e) {
            throw new RuntimeException(format("Error converting document for chapterNumber {%s} and script {%s}",
                    chapterNumber, script), e);
        }
    }

    <S extends Enum<S> & ScriptSupport> Document executeGetSingleVerseQuery(int chapterNumber, int verseNumber, S script)
            throws RuntimeException {
        getSingleVerse.setExternalVariable(new QName(DOC_VARIABLE_NAME), getDocument(script));
        getSingleVerse.setExternalVariable(new QName(CHAPTER_NUMBER_VARIABLE_NAME), new XdmAtomicValue(chapterNumber));
        getSingleVerse.setExternalVariable(new QName(VERSE_NUMBER_VARIABLE_NAME), new XdmAtomicValue(verseNumber));
        try {
            final XdmValue result = getSingleVerse.evaluate();
            return jaxbTool.unmarshal(Document.class, new ByteArrayInputStream(result.toString().getBytes()));
        } catch (SaxonApiException e) {
            throw new RuntimeException(format("Error running query {executeGetSingleVerseQuery} for chapterNumber {%s}, verseNumber {%s}, and script {%s}",
                    chapterNumber, verseNumber, script), e);
        } catch (JAXBException e) {
            throw new RuntimeException(format("Error converting document for chapterNumber {%s} and script {%s}",
                    chapterNumber, script), e);
        }
    }

    private <S extends Enum<S> & ScriptSupport> Document executeSearch(String searchString, S script)
            throws RuntimeException {
        search.setExternalVariable(new QName(DOC_VARIABLE_NAME), getDocument(script));
        search.setExternalVariable(new QName(SEARCH_STRING_VARIABLE_NAME), new XdmAtomicValue(searchString));
        try {
            final XdmValue result = search.evaluate();
            final Document document = jaxbTool.unmarshal(Document.class, new ByteArrayInputStream(result.toString().getBytes()));
            final List<Chapter> chapters = document.getChapters();
            if (chapters == null || chapters.isEmpty()) {
                return document;
            }
            final Iterator<Chapter> iterator = chapters.iterator();
            while (iterator.hasNext()) {
                final Chapter chapter = iterator.next();
                if (chapter.getVerses().isEmpty()) {
                    iterator.remove();
                }
            }
            return document;
        } catch (SaxonApiException e) {
            throw new RuntimeException(format("Error running query {search} for searchString {%s} and script {%s}",
                    searchString, script), e);
        } catch (JAXBException e) {
            throw new RuntimeException(format("Error converting document for searchString {%s} and script {%s}",
                    searchString, script), e);
        }
    }

    <S extends Enum<S> & ScriptSupport> Document executeSearch(String searchString, SearchOption searchOption,
                                                               S script) throws RuntimeException {
        searchOption = (searchOption == null) ? NONE : searchOption;
        if (searchOption.equals(NONE) || QURAN_SIMPLE_CLEAN.equals(script)) {
            // SearchOption "NONE" means search given string in given script
            return executeSearch(searchString, script);
        }
        // if SearchOption is "REMOVE_DIACRITIC" then gets the search result first

        searchNoDiacritic.setExternalVariable(new QName(DOC_CLEAN_VARIABLE_NAME), getDocument(QURAN_SIMPLE_CLEAN));
        searchNoDiacritic.setExternalVariable(new QName(DOC_VARIABLE_NAME), getDocument(script));
        searchNoDiacritic.setExternalVariable(new QName(SEARCH_STRING_VARIABLE_NAME), new XdmAtomicValue(searchString));

        try {
            final XdmValue result = searchNoDiacritic.evaluate();
            final Document document = jaxbTool.unmarshal(Document.class, new ByteArrayInputStream(result.toString().getBytes()));
            final List<Chapter> chapters = document.getChapters();
            final Iterator<Chapter> iterator = chapters.iterator();
            while (iterator.hasNext()) {
                final Chapter chapter = iterator.next();
                if (chapter.getVerses().isEmpty()) {
                    iterator.remove();
                }
            }
            return document;
        } catch (SaxonApiException e) {
            throw new RuntimeException(format("Error running query {searchNoDiacritic} for searchString {%s} and script {%s}",
                    searchString, script), e);
        } catch (JAXBException e) {
            throw new RuntimeException(format("Error converting document for searchString {%s} and script {%s}",
                    searchString, script), e);
        }

    }

    private <S extends Enum<S> & ScriptSupport> XdmNode getDocument(S script) throws RuntimeException {
        XdmNode document = documentMap.get(script);
        if (document == null) {
            try {
                document = documentBuilder.build(new StreamSource(getResourceAsStream(script.getPath())));
            } catch (SaxonApiException e) {
                throw new RuntimeException(format("Error loading document for {%s}", script), e);
            }
            documentMap.put(script, document);
        }
        return document;
    }

    private XdmNode getDocument(String path) throws RuntimeException {
        try {
            return documentBuilder.build(new StreamSource(getResourceAsStream(path)));
        } catch (SaxonApiException e) {
            throw new RuntimeException(format("Error loading document for {%s}", path), e);
        }
    }

}
