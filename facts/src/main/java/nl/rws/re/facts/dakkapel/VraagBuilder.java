package nl.rws.re.facts.dakkapel;

import java.util.List;

/**
 * Created by Md. Mainul Hasan Patwary on 01-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class VraagBuilder {
    private final Vraag vraag;

    public VraagBuilder(){
        this.vraag = new Vraag();
    }

    public VraagBuilder metVraag(String vraag){
        this.vraag.setVraag(vraag);
        return this;
    }

    public VraagBuilder metAfhankelijkheden(Vraag[] afhankelijkheden){
        this.vraag.setAfhankelijkheden(afhankelijkheden);
        return this;
    }

    public VraagBuilder metAntwoordType(String antwoordType){
        this.vraag.setAntwoordType(antwoordType);
        return this;
    }

    public VraagBuilder metAntwoordSource(String antwoordSource){
        this.vraag.setAntwoordSource(antwoordSource);
        return this;
    }

    public VraagBuilder metAntwoord(String antwoord){
        this.vraag.setAntwoord(antwoord);
        return this;
    }

    public Vraag build(){
        return this.vraag;
    }
}
