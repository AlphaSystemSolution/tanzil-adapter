
package com.alphasystem.tanzil.meta.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for juz complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="juz"&gt;
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
@XmlType(name = "juz")
public class Part {

    @XmlAttribute(name = "index", required = true)
    protected int partNumber;
    @XmlAttribute(name = "sura", required = true)
    protected int chapterNumber;
    @XmlAttribute(name = "aya", required = true)
    protected int verseNumber;

    /**
     * Gets the value of the partNumber property.
     * 
     */
    public int getPartNumber() {
        return partNumber;
    }

    /**
     * Sets the value of the partNumber property.
     * 
     */
    public void setPartNumber(int value) {
        this.partNumber = value;
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

    public Part withPartNumber(int value) {
        setPartNumber(value);
        return this;
    }

    public Part withChapterNumber(int value) {
        setChapterNumber(value);
        return this;
    }

    public Part withVerseNumber(int value) {
        setVerseNumber(value);
        return this;
    }

}
