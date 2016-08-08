package nl.rws.re.frontend;

import nl.rws.re.backend.*;
import nl.rws.re.backend.Grondslag;
import nl.rws.re.backend.Step;
import nl.rws.re.backend.Vraag;
import nl.rws.re.facts.dakkapel.*;
import nl.rws.re.facts.dakkapel.GebruikerVragen;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.*;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 07-Jul-16.
 */
@Path("")
public class DakkapelService {

    private static Logger LOGGER = Logger.getLogger(DakkapelService.class);

    Vraag vraag1;
    Vraag vraag2;

    @GET
    public String result() {
        ObjectFactory objectFactory = new ObjectFactory();

        nl.rws.re.backend.DakkapelService dakkapelService = new nl.rws.re.backend.DakkapelService();

//        vraag1 = new VraagBuilder().metVraag("Zijn er rondom de dakkapel dakpannen aanwezig?").metAntwoordType(AntwoordType.BOOLEAN.name()).build();
        vraag1 = objectFactory.createVraag();
        vraag1.setVraag(objectFactory.createVraagVraag("Zijn er rondom de dakkapel dakpannen aanwezig?"));
        vraag1.setAntwoordType(objectFactory.createVraagAntwoordType(AntwoordType.BOOLEAN.name()));

//        vraag2 = new VraagBuilder().metVraag("Bouwt u de dakkapel op een schuin dak?").metAntwoordType(AntwoordType.BOOLEAN.name()).metAfhankelijkheden(Arrays.asList(vraag1)).build();
        vraag2 = objectFactory.createVraag();
        vraag2.setVraag(objectFactory.createVraagVraag("Bouwt u de dakkapel op een schuin dak?"));
        vraag2.setAntwoordType(objectFactory.createVraagAntwoord(AntwoordType.BOOLEAN.name()));

        java.util.Map<Vraag, Conversie> vraagConversieMap = new HashMap<>();
        vraagConversieMap.put(vraag1, new Conversie(Antwoord.JA.name()));
//        Grondslag grondslag1 = new GrondslagBuilder().metGrondslagBeschrijving("Dakpannen rondom").metGrondslagType(GrondslagType.WAT_WERKWOORD.name())
// .metVragen(Arrays.asList(vraag1)).metConversiePerVragen(vraagConversieMap).build();
        Grondslag grondslag1 = objectFactory.createGrondslag();
        grondslag1.setGrondslagBeschrijving(objectFactory.createGrondslagGrondslagBeschrijving("Dakpannen rondom"));
        grondslag1.setGrondslagType(objectFactory.createGrondslagGrondslagBeschrijving(GrondslagType.WAT_WERKWOORD.name()));
        grondslag1.setVragen(objectFactory.createGrondslagVragen(Arrays.asList(vraag1)));
//        grondslag1.setConversiePerVragen(objectFactory.createGrondslagConversiePerVragen());
        vraagConversieMap = new HashMap<>();
        vraagConversieMap.put(vraag2, new Conversie(Antwoord.JA.name()));
//        Grondslag grondslag2 = new GrondslagBuilder().metGrondslagBeschrijving("Op een schuin dak").metGrondslagType(GrondslagType.WAAR.name()).metVragen(Arrays.asList(vraag2)).metConversiePerVragen(vraagConversieMap).build();

//        List<Grondslag> grondslags = Arrays.asList(grondslag1, grondslag2);
//
//        Map<nl.rws.re.facts.dakkapel.Step, Conversie> stepConversieMap = new HashMap<>();
//        Map<Grondslag, Conversie> grondslagConversieMap = new HashMap<>();
//        grondslagConversieMap.put(grondslag1, new Conversie(Antwoord.JA.name()));
//        grondslagConversieMap.put(grondslag2, new Conversie((Antwoord.JA.name())));
//
//        Map<nl.rws.re.facts.dakkapel.Step, String> stepConditieMap = new HashMap<>();
//        Map<Grondslag, String> grondslagConditieMap = new HashMap<>();
//        grondslagConditieMap.put(grondslag1, Conditie.EN.name());
//        grondslagConditieMap.put(grondslag2, Conditie.EN.name());

        Step step = objectFactory.createStep();

//        nl.rws.re.facts.dakkapel.Step step1 = new StepBuilder().metStepBeschrijven("Definitie").metGrondslags(grondslags).metGrondslagConversie(grondslagConversieMap).metGrondslagConditie(grondslagConditieMap).build();
        step.setStepBeschrijven(objectFactory.createStepStepBeschrijven("Definitie"));
        step.setGrondslags(objectFactory.createStepGrondslags(grondslag1));
//        step.setGrondslagConvertieMap(objectFactory.createStepGrondslagConvertieMap(grondslagConversieMap));
//        step.setGrondslagConditieMap(grondslagConditieMap);

        DakkapelRequest dakkapelRequest = new DakkapelRequest();
        dakkapelRequest.getStep().add(step);

        List<nl.rws.re.backend.GebruikerVragen> gebruikerVragens = dakkapelService.getDakkapelServiceHttpSoap11Endpoint().dakkapelService(dakkapelRequest).getGebruikerVragen();

//        GebruikerVragen gebruikerVragen = (GebruikerVragen) gebruikerVragens.get(0).getGebruikerVragenVoorElkGrondslags().getValue();

        System.out.println(gebruikerVragens.get(0).getGebruikerVragenVoorElkGrondslags().getValue().toString());



        return "Hello ";
    }
}
