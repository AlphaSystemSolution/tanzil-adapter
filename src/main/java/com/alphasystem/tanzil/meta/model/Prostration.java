
package com.alphasystem.tanzil.meta.model;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for sajda complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sajda"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="index" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="sura" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="aya" use="required" type="{http://www.w3.org/2001/XMLSchema}int" /&gt;
 *       &lt;attribute name="type" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token"&gt;
 *             &lt;enumeration value="recommended"/&gt;
 *             &lt;enumeration value="obligatory"/&gt;
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
@XmlType(name = "sajda")
public class Prostration {

    @XmlAttribute(name = "index", required = true)
    protected int prostrationNumber;
    @XmlAttribute(name = "sura", required = true)
    protected int chapterNumber;
    @XmlAttribute(name = "aya", required = true)
    protected int verseNumber;
    @XmlAttribute(name = "type", required = true)
    protected Type type;

    /**
     * Gets the value of the prostrationNumber property.
     * 
     */
    public int getProstrationNumber() {
        return prostrationNumber;
    }

    /**
     * Sets the value of the prostrationNumber property.
     * 
     */
    public void setProstrationNumber(int value) {
        this.prostrationNumber = value;
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

    public Prostration withProstrationNumber(int value) {
        setProstrationNumber(value);
        return this;
    }

    public Prostration withChapterNumber(int value) {
        setChapterNumber(value);
        return this;
    }

    public Prostration withVerseNumber(int value) {
        setVerseNumber(value);
        return this;
    }

    public Prostration withType(Type value) {
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
     *     &lt;enumeration value="recommended"/&gt;
     *     &lt;enumeration value="obligatory"/&gt;
     *   &lt;/restriction&gt;
     * &lt;/simpleType&gt;
     * </pre>
     * 
     */
    @XmlType(name = "")
    @XmlEnum
    public enum Type {

        @XmlEnumValue("recommended")
        RECOMMENDED("recommended"),
        @XmlEnumValue("obligatory")
        OBLIGATORY("obligatory");
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
