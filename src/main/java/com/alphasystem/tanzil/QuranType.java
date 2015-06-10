//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-833 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.09 at 11:32:37 PM EDT 
//


package com.alphasystem.tanzil;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>Java class for QuranType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QuranType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.alphasystem.com/tanzil}sura" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "quran")
@XmlType(name = "QuranType", propOrder = {
    "sura"
})
public class QuranType {

    @XmlElement(required = true)
    protected List<SuraType> sura;

    /**
     * Gets the value of the sura property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sura property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSura().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SuraType }
     * 
     * 
     */
    public List<SuraType> getSura() {
        if (sura == null) {
            sura = new ArrayList<SuraType>();
        }
        return this.sura;
    }

    public QuranType withSura(SuraType... values) {
        if (values!= null) {
            for (SuraType value: values) {
                getSura().add(value);
            }
        }
        return this;
    }

    public QuranType withSura(Collection<SuraType> values) {
        if (values!= null) {
            getSura().addAll(values);
        }
        return this;
    }

    /**
     * Sets the value of the sura property.
     * 
     * @param sura
     *     allowed object is
     *     {@link SuraType }
     *     
     */
    public void setSura(List<SuraType> sura) {
        this.sura = sura;
    }

}
