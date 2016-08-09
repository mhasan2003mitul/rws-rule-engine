package nl.rws.re.facts.dakkapelx;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Md. Mainul Hasan Patwary on 03-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
@XmlRootElement
public class Conversie {
    @XmlElement
    private String conversieAntwoord;

    public Conversie() {
    }

    public Conversie(String conversieAntwoord) {
        this.conversieAntwoord = conversieAntwoord;
    }

    public String getConversieAntwoord() {
        return conversieAntwoord;
    }

    public void setConversieAntwoord(String conversieAntwoord) {
        this.conversieAntwoord = conversieAntwoord;
    }
}
