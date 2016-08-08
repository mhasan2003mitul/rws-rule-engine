package nl.rws.re.facts.dakkapel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Created by Md. Mainul Hasan Patwary on 03-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class DakkapelUtil {

    public static GebruikerVragen krijgtStepVragen(List<Step> stepen) {
        GebruikerVragen gebruikerVragen = new GebruikerVragen();
        for (Step step : stepen) {
            GebruikerVragenVoorElkGrondslag gebruikerGebruikerVragenElkGrondslag = crawlingStep(step);
            if (gebruikerGebruikerVragenElkGrondslag.getGebruikerGrondslagVragenMap().size() > 0) {
                gebruikerVragen.getGebruikerVragenVoorElkGrondslags().add(crawlingStep(step));
            }
        }
        return gebruikerVragen;
    }

    public static GebruikerVragenVoorElkGrondslag crawlingStep(Step step) {
        GebruikerVragenVoorElkGrondslag gebruikerGebruikerVragenElkGrondslag = new GebruikerVragenVoorElkGrondslag();

//        System.out.println(step.getStepBeschrijven());

        for (Step verderStep : step.getSteps()) {
            gebruikerGebruikerVragenElkGrondslag.getGebruikerGrondslagVragenMap().putAll(crawlingStep(verderStep).getGebruikerGrondslagVragenMap());
        }

        gebruikerGebruikerVragenElkGrondslag.getGebruikerGrondslagVragenMap().putAll(krijgtGrondslagVragen(step.getGrondslags()).getGebruikerGrondslagVragenMap());

//        System.out.println("Vragen Map Size: "+gebruikerGebruikerVragenElkGrondslag.getGebruikerGrondslagVragenMap().size());
//        for (Object entryObj : gebruikerGebruikerVragenElkGrondslag.getGebruikerGrondslagVragenMap().entrySet()) {
//            Entry<Grondslag, List<Vraag>> entry = (Entry<Grondslag, List<Vraag>>) entryObj;
//            System.out.println(entry.getKey().getGrondslagBeschrijving());
//            for (Vraag vraag : entry.getValue()) {
//                System.out.println("\t\t\t\t" + vraag.getVraag());
//            }
//        }

        return gebruikerGebruikerVragenElkGrondslag;
    }

    public static GebruikerVragenVoorElkGrondslag krijgtGrondslagVragen(List<Grondslag> grondslagen) {
        Map<Grondslag, List<Vraag>> grondslagVragen = new HashMap<>();
        for (Grondslag grondslag : grondslagen) {
            for (Vraag vraag : grondslag.getVragen()) {
                List<Vraag> vragen = heeftAfhangelijkheden(vraag);
                if (vragen.size() > 0) {
                    grondslagVragen.put(grondslag, vragen);
                }
            }
        }

//        for (Entry entry : grondslagVragen.entrySet()) {
//            System.out.println(((Grondslag) entry.getKey()).getGrondslagBeschrijving());
//            for (Vraag vraag : (List<Vraag>) entry.getValue()) {
//                System.out.println(vraag.getVraag());
//            }
//        }

        GebruikerVragenVoorElkGrondslag gv = new GebruikerVragenVoorElkGrondslag();
        gv.setGebruikerGrondslagVragenMap(grondslagVragen);
        return gv;
    }

    public static List<Vraag> heeftAfhangelijkheden(Vraag vraag) {
        List<Vraag> vragen = new ArrayList<>();
//        if (vraag.getAfhankelijkheden() != null && vraag.getAfhankelijkheden().size() > 0) {
//            for (Vraag afhankelijkhedenVraag : vraag.getAfhankelijkheden()) {
//                vragen.addAll(heeftAfhangelijkheden(afhankelijkhedenVraag));
//            }
//        }

        if (vraag.getAntwoord() == null) {
            vragen.add(vraag);
        }
        return vragen;
    }

    public void berekenenDenifitiefBeslissing(List<Step> stepen) {
        for (Step step : stepen) {
            gaVarderVoorBeslissing(step);
        }
    }

    public void berekenenDenifitiefBeslissing(Step step) {
        gaVarderVoorBeslissing(step);
    }

    public void gaVarderVoorBeslissing(Step step) {
        for (Step verderStep : step.getSteps()) {
            gaVarderVoorBeslissing(verderStep);
        }
        krijgtGrondslagBeslissing(step.getGrondslags());

        boolean resultaat = false;

        if (step.getGrondslags().size() > 0) {
            resultaat = step.getGrondslagConvertieMap().get(step.getGrondslags().get(0)).getConversieAntwoord().
                    equalsIgnoreCase(step.getGrondslags().get(0).getDefinitieveBeslissing());
        }
        for (Grondslag grondslag : step.getGrondslags()) {
            if (step.getGrondslagConditieMap().get(grondslag).equalsIgnoreCase(Conditie.EN.name())) {
                resultaat &= step.getGrondslagConvertieMap().get(grondslag).getConversieAntwoord().equalsIgnoreCase(grondslag.getDefinitieveBeslissing());
            } else {
                resultaat |= step.getGrondslagConvertieMap().get(grondslag).getConversieAntwoord().equalsIgnoreCase(grondslag.getDefinitieveBeslissing());
            }
        }

        if (step.getGrondslags().size() == 0 && step.getSteps().size() > 0) {
            resultaat = step.getStepConversieMap().get(step.getSteps().get(0)).getConversieAntwoord().
                    equalsIgnoreCase(step.getSteps().get(0).getDefinitieveBeslissing());
        }
        for (Step innerStep : step.getSteps()) {
            if (step.getStepConditieMap().get(innerStep).equalsIgnoreCase(Conditie.EN.name())) {
                resultaat &= step.getStepConversieMap().get(innerStep).getConversieAntwoord().equalsIgnoreCase(innerStep.getDefinitieveBeslissing());
            } else {
                resultaat |= step.getStepConversieMap().get(innerStep).getConversieAntwoord().equalsIgnoreCase(innerStep.getDefinitieveBeslissing());
            }
        }

        if (resultaat) {
            step.setDefinitieveBeslissing(Antwoord.JA.name());
        } else {
            step.setDefinitieveBeslissing(Antwoord.NEE.name());
        }

        System.out.println(step.getStepBeschrijven() + ":" + resultaat);
    }

    public void krijgtGrondslagBeslissing(List<Grondslag> grondslagen) {
        for (Grondslag grondslag : grondslagen) {
            for (Vraag vraag : grondslag.getVragen()) {
                if (grondslag.getConversiePerVragen().get(vraag).getConversieAntwoord().equalsIgnoreCase(vraag.getAntwoord())) {
                    grondslag.setDefinitieveBeslissing(Antwoord.JA.name());
                } else {
                    grondslag.setDefinitieveBeslissing(Antwoord.NEE.name());
                    break;
                }
            }
        }
    }

}
