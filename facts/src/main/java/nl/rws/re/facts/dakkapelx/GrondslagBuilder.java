package nl.rws.re.facts.dakkapelx;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Md. Mainul Hasan Patwary on 01-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class GrondslagBuilder {
    private final Grondslag grondslag;

    public GrondslagBuilder(){
        this.grondslag = new Grondslag();
    }

    public GrondslagBuilder metGrondslagBeschrijving(String grondslagBeschrijven){
        this.grondslag.setGrondslagBeschrijving(grondslagBeschrijven);
        return this;
    }

    public GrondslagBuilder metGrondslagType(String grondslagType){
        this.grondslag.setGrondslagType(grondslagType);
        return this;
    }

    public GrondslagBuilder metVragen(Vraag[] vragen){
        this.grondslag.setVragen(vragen);
        return this;
    }

    public Grondslag build(){
        return grondslag;
    }
}
