package nl.rws.re.facts;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by Md. Mainul Hasan Patwary on 01-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */

public class Vraag {
    private String vraagId;
    private String antwoord;
    private String conversionId;

    public Vraag() {
        this(null);
    }

    public Vraag(String vraagId){
        this(vraagId,null);
    }

    public Vraag(String vraagId, String conversionId) {
        this(vraagId,conversionId,null);
    }

    public Vraag(String vraagId,String conversionId, String antwoord) {
        this.vraagId = vraagId;
        this.conversionId = conversionId;
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

    public String getConversionId() {
        return conversionId;
    }

    public void setConversionId(String conversionId) {
        this.conversionId = conversionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Vraag)) return false;

        Vraag vraag = (Vraag) o;

        return new EqualsBuilder()
                .append(getVraagId(), vraag.getVraagId())
                .append(getAntwoord(), vraag.getAntwoord())
                .append(getConversionId(), vraag.getConversionId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getVraagId())
                .append(getAntwoord())
                .append(getConversionId())
                .toHashCode();
    }
}
