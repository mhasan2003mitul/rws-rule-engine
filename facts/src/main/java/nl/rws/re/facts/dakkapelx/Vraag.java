package nl.rws.re.facts.dakkapelx;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by Md. Mainul Hasan Patwary on 01-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */

public class Vraag {
    private String vraagId;
    private String antwoord;

    public Vraag() {
    }

    public Vraag(String vraagId, String antwoord) {
        this.vraagId = vraagId;
        this.antwoord = antwoord;
    }

    public String getVraagId() {
        return vraagId;
    }

    public void setVraagId(String vraagId) {
        this.vraagId = vraagId;
    }

    public String getAntwoord() {
        return antwoord;
    }

    public void setAntwoord(String antwoord) {
        this.antwoord = antwoord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Vraag)) return false;

        Vraag vraag = (Vraag) o;

        return new EqualsBuilder()
                .append(getVraagId(), vraag.getVraagId())
                .append(getAntwoord(), vraag.getAntwoord())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getVraagId())
                .append(getAntwoord())
                .toHashCode();
    }
}
