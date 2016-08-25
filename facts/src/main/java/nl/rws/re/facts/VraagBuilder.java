package nl.rws.re.facts.dakkapelx;

/**
 * Created by Md. Mainul Hasan Patwary on 01-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class VraagBuilder {
    private final Vraag vraag;

    public VraagBuilder() {
        this.vraag = new Vraag();
    }

    public VraagBuilder metVraagId(String vraagId) {
        this.vraag.setVraagId(vraagId);
        return this;
    }

    public VraagBuilder metAntwoord(String antwoord) {
        this.vraag.setAntwoord(antwoord);
        return this;
    }

    public Vraag build() {
        return this.vraag;
    }
}
