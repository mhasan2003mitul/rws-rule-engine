package nl.rws.re.facts.dakkapelx;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.xml.bind.annotation.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * Created by Md. Mainul Hasan Patwary on 01-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
@XmlRootElement(namespace = "http://dakkapel.facts.re.rws.nl")
@XmlAccessorType(XmlAccessType.FIELD)
public class Grondslag {
    private String grondslagBeschrijving;
    private String grondslagType;
    private Vraag[] vragen;
    private String definitieveBeslissing;

    public Grondslag() {
    }

    public String getGrondslagBeschrijving() {
        return grondslagBeschrijving;
    }

    public void setGrondslagBeschrijving(String grondslagBeschrijving) {
        this.grondslagBeschrijving = grondslagBeschrijving;
    }

    public String getGrondslagType() {
        return grondslagType;
    }

    public void setGrondslagType(String grondslagType) {
        this.grondslagType = grondslagType;
    }

    public Vraag[] getVragen() {
        return vragen;
    }

    public void setVragen(Vraag[] vragen) {
        this.vragen = vragen;
    }

    public String getDefinitieveBeslissing() {
        return definitieveBeslissing;
    }

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
                .append(getDefinitieveBeslissing(), grondslag.getDefinitieveBeslissing())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getGrondslagBeschrijving())
                .append(getGrondslagType())
                .append(getVragen())
                .append(getDefinitieveBeslissing())
                .toHashCode();
    }
}
