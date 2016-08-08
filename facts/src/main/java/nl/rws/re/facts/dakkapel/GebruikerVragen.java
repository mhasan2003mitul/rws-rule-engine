package nl.rws.re.facts.dakkapel;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Md. Mainul Hasan Patwary on 04-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class GebruikerVragen {
    private List<GebruikerVragenVoorElkGrondslag> gebruikerVragenVoorElkGrondslags;

    public GebruikerVragen() {
        gebruikerVragenVoorElkGrondslags = new ArrayList<>();
    }

    public GebruikerVragen(List<GebruikerVragenVoorElkGrondslag> gebruikerVragenVoorElkGrondslags) {
        this.gebruikerVragenVoorElkGrondslags = gebruikerVragenVoorElkGrondslags;
    }

    public List<GebruikerVragenVoorElkGrondslag> getGebruikerVragenVoorElkGrondslags() {
        return gebruikerVragenVoorElkGrondslags;
    }

    public void setGebruikerVragenVoorElkGrondslags(List<GebruikerVragenVoorElkGrondslag> gebruikerVragenVoorElkGrondslags) {
        this.gebruikerVragenVoorElkGrondslags = gebruikerVragenVoorElkGrondslags;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof GebruikerVragen)) return false;

        GebruikerVragen that = (GebruikerVragen) o;

        return new EqualsBuilder()
                .append(getGebruikerVragenVoorElkGrondslags(), that.getGebruikerVragenVoorElkGrondslags())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getGebruikerVragenVoorElkGrondslags())
                .toHashCode();
    }
}
