//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.09 at 11:32:37 PM EDT 
//


package com.alphasystem.tanzil;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.alphasystem.tanzil package. 
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

    private final static QName _Quran_QNAME = new QName("quran");
//    private final static QName _Sura_QNAME = new QName("http://www.alphasystem.com/tanzil", "sura");
//    private final static QName _Aya_QNAME = new QName("http://www.alphasystem.com/tanzil", "aya");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.alphasystem.tanzil
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AyaType }
     * 
     */
    public AyaType createAyaType() {
        return new AyaType();
    }

    /**
     * Create an instance of {@link QuranType }
     * 
     */
    public QuranType createQuranType() {
        return new QuranType();
    }

    /**
     * Create an instance of {@link SuraType }
     * 
     */
    public SuraType createSuraType() {
        return new SuraType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QuranType }{@code >}}
     * 
     */
    @XmlElementDecl(name = "quran")
    public JAXBElement<QuranType> createQuran(QuranType value) {
        return new JAXBElement<QuranType>(_Quran_QNAME, QuranType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SuraType }{@code >}}
     * 
     */
//    @XmlElementDecl(namespace = "http://www.alphasystem.com/tanzil", name = "sura")
//    public JAXBElement<SuraType> createSura(SuraType value) {
//        return new JAXBElement<SuraType>(_Sura_QNAME, SuraType.class, null, value);
//    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AyaType }{@code >}}
     * 
     */
//    @XmlElementDecl(namespace = "http://www.alphasystem.com/tanzil", name = "aya")
//    public JAXBElement<AyaType> createAya(AyaType value) {
//        return new JAXBElement<AyaType>(_Aya_QNAME, AyaType.class, null, value);
//    }

}