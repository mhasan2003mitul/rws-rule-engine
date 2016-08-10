package nl.rws.re.facts.dakkapelx;

/**
 * Created by Md. Mainul Hasan Patwary on 09-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public enum GrondslagBeschrijving {
    DAKPANNEN_RONDOM("Dakpannen rondom"),OP_EEN_SCHUIN_DAK("Op een schuin dak"),OP_EEN_ACHTERDAKVLAK("Op een achterdakvlak"),
    OP_EEN_ZIJDAKVLAK("Op een zijdakvlak"),NAAR_OPENBAAR_TOEGELIJK_GEBIED_GEKEERD("Naar openbaar toegelijk gebied gekeerd");

    private String grondslagBeschrijving;

    private GrondslagBeschrijving(String grondslagBeschrijving){
        this.grondslagBeschrijving = grondslagBeschrijving;
    }

    @Override
    public String toString() {
        return this.grondslagBeschrijving;
    }
}
