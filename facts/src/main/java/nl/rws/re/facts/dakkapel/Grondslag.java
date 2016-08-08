package nl.rws.re.facts.dakkapel;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Md. Mainul Hasan Patwary on 01-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
@XmlRootElement(namespace = "http://dakkapel.facts.re.rws.nl")
@XmlSeeAlso({Map.class,List.class})
public class Grondslag {
    private String grondslagBeschrijving;
    private String grondslagType;
    private ArrayList<Vraag> vragen;
    private Map<Vraag,Conversie> conversiePerVragen;
    private String definitieveBeslissing;

    public Grondslag() {
        this.vragen = new ArrayList<>();
        this.conversiePerVragen = new HashMap<>();
    }

    public Grondslag(String grondslagBeschrijving, String grondslagType, ArrayList<Vraag> vragen, Map<Vraag, Conversie> conversiePerVragen, String definitieveBeslissing) {
        this.grondslagBeschrijving = grondslagBeschrijving;
        this.grondslagType = grondslagType;
        this.vragen = vragen;
        this.conversiePerVragen = conversiePerVragen;
        this.definitieveBeslissing = definitieveBeslissing;
    }

    public String getGrondslagBeschrijving() {
        return grondslagBeschrijving;
    }

    @XmlElement
    public void setGrondslagBeschrijving(String grondslagBeschrijving) {
        this.grondslagBeschrijving = grondslagBeschrijving;
    }

    public String getGrondslagType() {
        return grondslagType;
    }

    @XmlElement
    public void setGrondslagType(String grondslagType) {
        this.grondslagType = grondslagType;
    }

    public List<Vraag> getVragen() {
        return vragen;
    }

    @XmlElement
    public void setVragen(ArrayList<Vraag> vragen) {
        this.vragen = vragen;
    }

    public Map<Vraag, Conversie> getConversiePerVragen() {
        return conversiePerVragen;
    }

    @XmlElement
    public void setConversiePerVragen(Map<Vraag, Conversie> conversiePerVragen) {
        this.conversiePerVragen = conversiePerVragen;
    }

    public String getDefinitieveBeslissing() {
        return definitieveBeslissing;
    }

    @XmlElement
    public void setDefinitieveBeslissing(String definitieveBeslissing) {
        this.definitieveBeslissing = definitieveBeslissing;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Grondslag)) return false;

        Grondslag grondslag = (Grondslag) o;

        return new EqualsBuilder()
                .append(getGrondslagBeschrijving(), grondslag.getGrondslagBeschrijving())
                .append(getGrondslagType(), grondslag.getGrondslagType())
                .append(getVragen(), grondslag.getVragen())
                .append(getConversiePerVragen(), grondslag.getConversiePerVragen())
                .append(getDefinitieveBeslissing(),grondslag.getDefinitieveBeslissing())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getGrondslagBeschrijving())
                .append(getGrondslagType())
                .append(getVragen())
                .append(getConversiePerVragen())
                .toHashCode();
    }
}
