package nl.rws.re.facts.error;

/**
 * Created by Md. Mainul Hasan Patwary on 09-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public enum ErrorMessage {
    DEFINITIE_HEEFT_GEEN_GRONDSLAG("Definitie regels heeft geen grondslags informatie"),
    NIET_ZICHTBAAR_ZIJDAKVLAK("Niet zichtbaar zijdakvlak heeft geen grondslags informatie"),
    NIET_ZICHTBAAR_DAKVLAK("Niet zichtbaar dakvlak heeft geen grondslags informatie"),
    ALGEMENE_MSG("Geen grondslag informatie toevoegen");
    private String message;

    private ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return this.message;
    }
}
