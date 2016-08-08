package nl.rws.re.facts.dakkapel;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Md. Mainul Hasan Patwary on 03-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class GebruikerVragenVoorElkGrondslag {
    private Map<Grondslag,List<Vraag>> gebruikerGrondslagVragenMap;

    public GebruikerVragenVoorElkGrondslag() {
        gebruikerGrondslagVragenMap = new HashMap<>();
    }

    public GebruikerVragenVoorElkGrondslag(Map<Grondslag, List<Vraag>> gebruikerGrondslagVragenMap) {
        this.gebruikerGrondslagVragenMap = gebruikerGrondslagVragenMap;
    }

    public Map<Grondslag, List<Vraag>> getGebruikerGrondslagVragenMap() {
        return gebruikerGrondslagVragenMap;
    }

    public void setGebruikerGrondslagVragenMap(Map<Grondslag, List<Vraag>> gebruikerGrondslagVragenMap) {
        this.gebruikerGrondslagVragenMap = gebruikerGrondslagVragenMap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof GebruikerVragenVoorElkGrondslag)) return false;

        GebruikerVragenVoorElkGrondslag that = (GebruikerVragenVoorElkGrondslag) o;

        return new EqualsBuilder()
                .append(getGebruikerGrondslagVragenMap(), that.getGebruikerGrondslagVragenMap())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getGebruikerGrondslagVragenMap())
                .toHashCode();
    }
}
