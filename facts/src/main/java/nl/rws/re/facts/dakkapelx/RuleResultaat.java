package nl.rws.re.facts.dakkapelx;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Created by Md. Mainul Hasan Patwary on 09-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class RuleResultaat {
    private String resultaat;

    public RuleResultaat() {
    }

    public RuleResultaat(String resultaat) {
        this.resultaat = resultaat;
    }

    public String getResultaat() {
        return resultaat;
    }

    public void setResultaat(String resultaat) {
        this.resultaat = resultaat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof RuleResultaat)) return false;

        RuleResultaat that = (RuleResultaat) o;

        return new EqualsBuilder()
                .append(getResultaat(), that.getResultaat())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getResultaat())
                .toHashCode();
    }
}
