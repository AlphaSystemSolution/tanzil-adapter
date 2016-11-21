package com.alphasystem.tanzil;

import com.alphasystem.tanzil.meta.model.Chapter;
import com.alphasystem.tanzil.meta.model.Document;
import com.alphasystem.util.JAXBTool;
import org.apache.commons.jxpath.BasicVariables;
import org.apache.commons.jxpath.JXPathContext;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.io.UncheckedIOException;

import static com.alphasystem.util.AppUtil.getUrl;
import static org.apache.commons.jxpath.JXPathContext.newContext;

/**
 * @author sali
 */
public class MetaTool {

    private static final String CHAPTER_XPATH = "chapters[@chapterNumber = $chapterNumber]";
    private static final String CHAPTER_NUMBER_VARIABLE_NAME = "chapterNumber";

    private static MetaTool instance;

    public static synchronized MetaTool getInstance() {
        if (instance == null) {
            try {
                try {
                    instance = new MetaTool();
                } catch (JAXBException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
        return instance;
    }

    private final JXPathContext jxPathContext;

    private MetaTool() throws IOException, JAXBException {
        JAXBTool jaxbTool = new JAXBTool();
        jxPathContext = newContext(jaxbTool.unmarshal(Document.class, getUrl("tanzil.quran-data.xml")));
    }

    public Chapter getChapter(int chapterNumber) {
        final BasicVariables variables = new BasicVariables();
        variables.declareVariable(CHAPTER_NUMBER_VARIABLE_NAME, chapterNumber);
        jxPathContext.setVariables(variables);
        return (Chapter) jxPathContext.getValue(CHAPTER_XPATH, Chapter.class);
    }
}
