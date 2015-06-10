package com.alphasystem.tanzil.test;

import com.alphasystem.tanzil.QuranType;
import com.alphasystem.tanzil.SuraType;
import org.eclipse.persistence.jaxb.JAXBContext;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import static com.alphasystem.util.AppUtil.getResourceAsStream;
import static org.testng.Reporter.log;

/**
 * @author sali
 */
public class TanzilTest {

    @Test
    public void testRetrieve() {
        try {
            JAXBContext jaxbContext = (JAXBContext) JAXBContext.newInstance(QuranType.class.getPackage()
                    .getName());
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            QuranType o = (QuranType) unmarshaller.unmarshal(getResourceAsStream("tanzil.quran-simple.xml"));
            log(""+o.getSura().get(0).getAya().size());
            SuraType sura = jaxbContext.getValueByXPath(o, "quran/sura[@index='107']", null, SuraType.class);
            log("////// " + sura, true);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        //QuranType quranType = unmarshal(QuranType.class, getUrl("tanzil.quran-simple.xml"));
    }
}
