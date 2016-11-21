
package com.alphasystem.tanzil.meta.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for sura complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sura"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="index" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="ayas" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="start" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="order" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="rukus" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="tname" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="ename" use="required" type="{http://www.w3.org/2001/XMLSchema}string" /&gt;
 *       &lt;attribute name="type" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *             &lt;enumeration value="Meccan"/&gt;
 *             &lt;enumeration value="Medinan"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sura")
public class Chapter {

    @XmlAttribute(name = "index", required = true)
    protected int chapterNumber;
    @XmlAttribute(name = "ayas", required = true)
    protected int verseCount;
    @XmlAttribute(name = "start", required = true)
    protected int start;
    @XmlAttribute(name = "order", required = true)
    protected int order;
    @XmlAttribute(name = "rukus", required = true)
    protected int rukus;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "tname", required = true)
    protected String translatedName;
    @XmlAttribute(name = "ename", required = true)
    protected String englishName;
    @XmlAttribute(name = "type", required = true)
    protected Type type;

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
     * Gets the value of the verseCount property.
     * 
     */
    public int getVerseCount() {
        return verseCount;
    }

    /**
     * Sets the value of the verseCount property.
     * 
     */
    public void setVerseCount(int value) {
        this.verseCount = value;
    }

    /**
     * Gets the value of the start property.
     * 
     */
    public int getStart() {
        return start;
    }

    /**
     * Sets the value of the start property.
     * 
     */
    public void setStart(int value) {
        this.start = value;
    }

    /**
     * Gets the value of the order property.
     * 
     */
    public int getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     */
    public void setOrder(int value) {
        this.order = value;
    }

    /**
     * Gets the value of the rukus property.
     * 
     */
    public int getRukus() {
        return rukus;
    }

    /**
     * Sets the value of the rukus property.
     * 
     */
    public void setRukus(int value) {
        this.rukus = value;
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

    /**
     * Gets the value of the translatedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTranslatedName() {
        return translatedName;
    }

    /**
     * Sets the value of the translatedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTranslatedName(String value) {
        this.translatedName = value;
    }

    /**
     * Gets the value of the englishName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnglishName() {
        return englishName;
    }

    /**
     * Sets the value of the englishName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnglishName(String value) {
        this.englishName = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setType(Type value) {
        this.type = value;
    }

    public Chapter withChapterNumber(int value) {
        setChapterNumber(value);
        return this;
    }

    public Chapter withVerseCount(int value) {
        setVerseCount(value);
        return this;
    }

    public Chapter withStart(int value) {
        setStart(value);
        return this;
    }

    public Chapter withOrder(int value) {
        setOrder(value);
        return this;
    }

    public Chapter withRukus(int value) {
        setRukus(value);
        return this;
    }

    public Chapter withName(String value) {
        setName(value);
        return this;
    }

    public Chapter withTranslatedName(String value) {
        setTranslatedName(value);
        return this;
    }

    public Chapter withEnglishName(String value) {
        setEnglishName(value);
        return this;
    }

    public Chapter withType(Type value) {
        setType(value);
        return this;
    }


    /**
     * <p>Java class for null.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * <p>
     * <pre>
     * &lt;simpleType&gt;
     *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
     *     &lt;enumeration value="Meccan"/&gt;
     *     &lt;enumeration value="Medinan"/&gt;
     *   &lt;/restriction&gt;
     * &lt;/simpleType&gt;
     * </pre>
     * 
     */
    @XmlType(name = "")
    @XmlEnum
    public enum Type {

        @XmlEnumValue("Meccan")
        MECCAN("Meccan"),
        @XmlEnumValue("Medinan")
        MEDINAN("Medinan");
        private final String value;

        Type(String v) {
            value = v;
        }

        public String value() {
            return value;
        }

        public static Type fromValue(String v) {
            for (Type c: Type.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
            throw new IllegalArgumentException(v);
        }

    }

}
