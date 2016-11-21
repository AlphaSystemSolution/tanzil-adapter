
package com.alphasystem.tanzil.meta.model;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.alphasystem.tanzil.meta.model package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Quran_QNAME = new QName("http://www.tanzil.net", "quran");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.alphasystem.tanzil.meta.model
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Prostration }
     * 
     */
    public Prostration createProstration() {
        return new Prostration();
    }

    /**
     * Create an instance of {@link Chapter }
     * 
     */
    public Chapter createChapter() {
        return new Chapter();
    }

    /**
     * Create an instance of {@link Document }
     * 
     */
    public Document createDocument() {
        return new Document();
    }

    /**
     * Create an instance of {@link Part }
     * 
     */
    public Part createPart() {
        return new Part();
    }

    /**
     * Create an instance of {@link Quarter }
     * 
     */
    public Quarter createQuarter() {
        return new Quarter();
    }

    /**
     * Create an instance of {@link Station }
     * 
     */
    public Station createStation() {
        return new Station();
    }

    /**
     * Create an instance of {@link Section }
     * 
     */
    public Section createSection() {
        return new Section();
    }

    /**
     * Create an instance of {@link Page }
     * 
     */
    public Page createPage() {
        return new Page();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Document }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.tanzil.net", name = "quran")
    public JAXBElement<Document> createQuran(Document value) {
        return new JAXBElement<Document>(_Quran_QNAME, Document.class, null, value);
    }

}
