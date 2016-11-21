
package com.alphasystem.tanzil.meta.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ruku complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ruku"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="index" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="sura" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="aya" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ruku")
public class Section {

    @XmlAttribute(name = "index", required = true)
    protected int sectionNumber;
    @XmlAttribute(name = "sura", required = true)
    protected int chapterNumber;
    @XmlAttribute(name = "aya", required = true)
    protected int verseNumber;

    /**
     * Gets the value of the sectionNumber property.
     * 
     */
    public int getSectionNumber() {
        return sectionNumber;
    }

    /**
     * Sets the value of the sectionNumber property.
     * 
     */
    public void setSectionNumber(int value) {
        this.sectionNumber = value;
    }

    /**
     * Gets the value of the chapterNumber property.
     * 
     */
    public int getChapterNumber() {
        return chapterNumber;
    }

    /**
     * Sets the value of the chapterNumber property.
     * 
     */
    public void setChapterNumber(int value) {
        this.chapterNumber = value;
    }

    /**
     * Gets the value of the verseNumber property.
     * 
     */
    public int getVerseNumber() {
        return verseNumber;
    }

    /**
     * Sets the value of the verseNumber property.
     * 
     */
    public void setVerseNumber(int value) {
        this.verseNumber = value;
    }

    public Section withSectionNumber(int value) {
        setSectionNumber(value);
        return this;
    }

    public Section withChapterNumber(int value) {
        setChapterNumber(value);
        return this;
    }

    public Section withVerseNumber(int value) {
        setVerseNumber(value);
        return this;
    }

}
