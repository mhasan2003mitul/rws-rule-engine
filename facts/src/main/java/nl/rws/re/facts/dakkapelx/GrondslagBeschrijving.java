package nl.rws.re.facts.dakkapelx;

/**
 * Created by Md. Mainul Hasan Patwary on 09-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public enum GrondslagBeschrijving {
    DAKPANNEN_RONDOM("Dakpannen rondom"),OP_EEN_SCHUIN_DAK("Op een schuin dak");
    private String grondslagBeschrijving;

    private GrondslagBeschrijving(String grondslagBeschrijving){
        this.grondslagBeschrijving = grondslagBeschrijving;
    }

    @Override
    public String toString() {
        return this.grondslagBeschrijving;
    }
}
