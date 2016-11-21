
package com.alphasystem.tanzil.meta.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * <p>Java class for quran complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="quran"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="suras" type="{http://www.tanzil.net}suras"/&gt;
 *         &lt;element name="juzs" type="{http://www.tanzil.net}juzs"/&gt;
 *         &lt;element name="hizbs" type="{http://www.tanzil.net}hizbs"/&gt;
 *         &lt;element name="manzils" type="{http://www.tanzil.net}manzils"/&gt;
 *         &lt;element name="rukus" type="{http://www.tanzil.net}rukus"/&gt;
 *         &lt;element name="pages" type="{http://www.tanzil.net}pages"/&gt;
 *         &lt;element name="sajdas" type="{http://www.tanzil.net}sajdas"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "quran", propOrder = {
    "chapters",
    "parts",
    "groups",
    "stations",
    "sections",
    "pages",
    "prostration"
})
public class Document {

    @XmlElementWrapper(name = "suras", required = true)
    @XmlElement(name = "sura", namespace = "http://www.tanzil.net")
    protected List<Chapter> chapters;
    @XmlElementWrapper(name = "juzs", required = true)
    @XmlElement(name = "juz", namespace = "http://www.tanzil.net")
    protected List<Part> parts;
    @XmlElementWrapper(name = "hizbs", required = true)
    @XmlElement(name = "quarter", namespace = "http://www.tanzil.net")
    protected List<Quarter> groups;
    @XmlElementWrapper(name = "manzils", required = true)
    @XmlElement(name = "manzil", namespace = "http://www.tanzil.net")
    protected List<Station> stations;
    @XmlElementWrapper(name = "rukus", required = true)
    @XmlElement(name = "ruku", namespace = "http://www.tanzil.net")
    protected List<Section> sections;
    @XmlElementWrapper(required = true)
    @XmlElement(name = "page", namespace = "http://www.tanzil.net")
    protected List<Page> pages;
    @XmlElementWrapper(name = "sajdas", required = true)
    @XmlElement(name = "sajda", namespace = "http://www.tanzil.net")
    protected List<Prostration> prostration;

    public List<Chapter> getChapters() {
        if (chapters == null) {
            chapters = new ArrayList<Chapter>();
        }
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public List<Part> getParts() {
        if (parts == null) {
            parts = new ArrayList<Part>();
        }
        return parts;
    }

    public void setParts(List<Part> parts) {
        this.parts = parts;
    }

    public List<Quarter> getGroups() {
        if (groups == null) {
            groups = new ArrayList<Quarter>();
        }
        return groups;
    }

    public void setGroups(List<Quarter> groups) {
        this.groups = groups;
    }

    public List<Station> getStations() {
        if (stations == null) {
            stations = new ArrayList<Station>();
        }
        return stations;
    }

    public void setStations(List<Station> stations) {
        this.stations = stations;
    }

    public List<Section> getSections() {
        if (sections == null) {
            sections = new ArrayList<Section>();
        }
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

    public List<Page> getPages() {
        if (pages == null) {
            pages = new ArrayList<Page>();
        }
        return pages;
    }

    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    public List<Prostration> getProstration() {
        if (prostration == null) {
            prostration = new ArrayList<Prostration>();
        }
        return prostration;
    }

    public void setProstration(List<Prostration> prostration) {
        this.prostration = prostration;
    }

    public Document withChapters(Chapter... values) {
        if (values!= null) {
            for (Chapter value: values) {
                getChapters().add(value);
            }
        }
        return this;
    }

    public Document withChapters(Collection<Chapter> values) {
        if (values!= null) {
            getChapters().addAll(values);
        }
        return this;
    }

    public Document withChapters(List<Chapter> chapters) {
        setChapters(chapters);
        return this;
    }

    public Document withParts(Part... values) {
        if (values!= null) {
            for (Part value: values) {
                getParts().add(value);
            }
        }
        return this;
    }

    public Document withParts(Collection<Part> values) {
        if (values!= null) {
            getParts().addAll(values);
        }
        return this;
    }

    public Document withParts(List<Part> parts) {
        setParts(parts);
        return this;
    }

    public Document withGroups(Quarter... values) {
        if (values!= null) {
            for (Quarter value: values) {
                getGroups().add(value);
            }
        }
        return this;
    }

    public Document withGroups(Collection<Quarter> values) {
        if (values!= null) {
            getGroups().addAll(values);
        }
        return this;
    }

    public Document withGroups(List<Quarter> groups) {
        setGroups(groups);
        return this;
    }

    public Document withStations(Station... values) {
        if (values!= null) {
            for (Station value: values) {
                getStations().add(value);
            }
        }
        return this;
    }

    public Document withStations(Collection<Station> values) {
        if (values!= null) {
            getStations().addAll(values);
        }
        return this;
    }

    public Document withStations(List<Station> stations) {
        setStations(stations);
        return this;
    }

    public Document withSections(Section... values) {
        if (values!= null) {
            for (Section value: values) {
                getSections().add(value);
            }
        }
        return this;
    }

    public Document withSections(Collection<Section> values) {
        if (values!= null) {
            getSections().addAll(values);
        }
        return this;
    }

    public Document withSections(List<Section> sections) {
        setSections(sections);
        return this;
    }

    public Document withPages(Page... values) {
        if (values!= null) {
            for (Page value: values) {
                getPages().add(value);
            }
        }
        return this;
    }

    public Document withPages(Collection<Page> values) {
        if (values!= null) {
            getPages().addAll(values);
        }
        return this;
    }

    public Document withPages(List<Page> pages) {
        setPages(pages);
        return this;
    }

    public Document withProstration(Prostration... values) {
        if (values!= null) {
            for (Prostration value: values) {
                getProstration().add(value);
            }
        }
        return this;
    }

    public Document withProstration(Collection<Prostration> values) {
        if (values!= null) {
            getProstration().addAll(values);
        }
        return this;
    }

    public Document withProstration(List<Prostration> prostration) {
        setProstration(prostration);
        return this;
    }

}
