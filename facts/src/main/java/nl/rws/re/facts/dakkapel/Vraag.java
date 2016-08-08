package nl.rws.re.facts.dakkapel;

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
@XmlRootElement
public class Vraag {
    @XmlElement
    private String vraag;
    @XmlElement
    private List<Vraag> afhankelijkheden;
    @XmlElement
    private String antwoordType;
    @XmlElement
    private String antwoordSource;
    @XmlElement
    private String antwoord;

    public Vraag(){

    }

    public Vraag(String vraag, List<Vraag> afhankelijkheden, String antwoordType, String antwoordSource, String antwoord) {
        this.vraag = vraag;
        this.afhankelijkheden = afhankelijkheden;
        this.antwoordType = antwoordType;
        this.antwoordSource = antwoordSource;
        this.antwoord = antwoord;
    }

    public String getVraag() {
        return vraag;
    }

    public void setVraag(String vraag) {
        this.vraag = vraag;
    }

    public List<Vraag> getAfhankelijkheden() {
        return afhankelijkheden;
    }

    public void setAfhankelijkheden(List<Vraag> afhankelijkheden) {
        this.afhankelijkheden = afhankelijkheden;
    }

    public String getAntwoordType() {
        return antwoordType;
    }

    public void setAntwoordType(String antwoordType) {
        this.antwoordType = antwoordType;
    }

    public String getAntwoordSource() {
        return antwoordSource;
    }

    public void setAntwoordSource(String antwoordSource) {
        this.antwoordSource = antwoordSource;
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

        Vraag vraag1 = (Vraag) o;

        return new EqualsBuilder()
                .append(getVraag(), vraag1.getVraag())
                .append(getAfhankelijkheden(), vraag1.getAfhankelijkheden())
                .append(getAntwoordType(), vraag1.getAntwoordType())
                .append(getAntwoordSource(), vraag1.getAntwoordSource())
                .append(getAntwoord(), vraag1.getAntwoord())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getVraag())
                .append(getAfhankelijkheden())
                .append(getAntwoordType())
                .append(getAntwoordSource())
                .append(getAntwoord())
                .toHashCode();
    }
}
