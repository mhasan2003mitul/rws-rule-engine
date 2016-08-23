package nl.rws.re.facts.dakkapelx;

/**
 * Created by Md. Mainul Hasan Patwary on 01-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public enum Antwoord {
    JA("true"), NEE("false"), UNKNOWN("unknown");

    private String value;

    private Antwoord(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
