package nl.rws.re.facts.dakkapelx;

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
    private Vraag gebruikerVragen[];

    public GebruikerVragen() {
    }

    public GebruikerVragen(Vraag[] gebruikerVragen) {
        this.gebruikerVragen = gebruikerVragen;
    }

    public GebruikerVragen(List<Object> gebruikerVragen) {
        this.gebruikerVragen = new Vraag[gebruikerVragen.size()];
        for (int i = 0;i<gebruikerVragen.size();i++) {
            this.gebruikerVragen[i] = (Vraag)gebruikerVragen.get(i);
        }
    }

    public Vraag[] getGebruikerVragen() {
        return gebruikerVragen;
    }

    public void setGebruikerVragen(Vraag[] gebruikerVragen) {
        this.gebruikerVragen = gebruikerVragen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof GebruikerVragen)) return false;

        GebruikerVragen that = (GebruikerVragen) o;

        return new EqualsBuilder()
                .append(getGebruikerVragen(), that.getGebruikerVragen())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getGebruikerVragen())
                .toHashCode();
    }
}
