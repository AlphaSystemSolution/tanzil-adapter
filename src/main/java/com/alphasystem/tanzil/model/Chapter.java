//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2016.11.21 at 09:14:51 AM EST
//


package com.alphasystem.tanzil.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>Java class for sura complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="sura"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="aya" type="{http://www.tanzil.net}aya" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="index" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sura", propOrder = {
        "verses"
})
public class Chapter {

    @XmlElement(name = "aya", required = true)
    protected List<Verse> verses;
    @XmlAttribute(name = "index", required = true)
    protected int chapterNumber;
    @XmlAttribute(name = "name", required = true)
    protected String name;

    /**
     * Gets the value of the verses property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the verses property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVerses().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Verse }
     *
     *
     */
    public List<Verse> getVerses() {
        if (verses == null) {
            verses = new ArrayList<Verse>();
        }
        return this.verses;
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
     * Gets the value of the name property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setName(String value) {
        this.name = value;
    }

    public Chapter withVerses(Verse... values) {
        if (values!= null) {
            for (Verse value: values) {
                getVerses().add(value);
            }
        }
        return this;
    }

    public Chapter withVerses(Collection<Verse> values) {
        if (values!= null) {
            getVerses().addAll(values);
        }
        return this;
    }

    public Chapter withChapterNumber(int value) {
        setChapterNumber(value);
        return this;
    }

    public Chapter withName(String value) {
        setName(value);
        return this;
    }

}
