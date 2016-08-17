package nl.rws.re.facts.dakkapelx;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by Md. Mainul Hasan Patwary on 09-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class RuleResultaat {
    private String resultaatVoor;
    private String resultaat;

    public RuleResultaat() {
    }

    public RuleResultaat(String resultaatVoor, String resultaat) {
        this.resultaatVoor = resultaatVoor;
        this.resultaat = resultaat;
    }

    public String getResultaat() {
        return resultaat;
    }

    public void setResultaat(String resultaat) {
        this.resultaat = resultaat;
    }

    public String getResultaatVoor() {
        return resultaatVoor;
    }

    public void setResultaatVoor(String resultaatVoor) {
        this.resultaatVoor = resultaatVoor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof RuleResultaat)) return false;

        RuleResultaat that = (RuleResultaat) o;

        return new EqualsBuilder()
                .append(getResultaatVoor(), that.getResultaatVoor())
                .append(getResultaat(), that.getResultaat())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getResultaatVoor())
                .append(getResultaat())
                .toHashCode();
    }

    public enum ResultaatVoor{
        DEFINITIE,NIET_ZICHTBAAR_ZIJDAKVLAK,
        NIET_ZICHTBAAR_DAKVLAK,DAKKAPEL_IN_ACHTERDAKVLAK,
        ZICHTBAAR_OF_NIET,VOORWAARDEN_VERGUNNINGVRIJ
    }

}
