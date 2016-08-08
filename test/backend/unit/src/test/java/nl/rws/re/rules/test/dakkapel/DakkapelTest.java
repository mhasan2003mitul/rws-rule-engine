package nl.rws.re.rules.test.dakkapel;

import nl.rws.re.facts.dakkapel.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.*;

/**
 * Created by Md. Mainul Hasan Patwary on 01-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class DakkapelTest {

    private final static String KIE_SESSION_NAME = "dakkapelSession";
    private KieSession kieSession;
    private List<Step> steps;

    Vraag vraag1;
    Vraag vraag2;
    Vraag vraag3;
    Vraag vraag4;
    Vraag vraag5;
    Vraag vraag6;
    Vraag vraag7;
    Vraag vraag8;
    Vraag vraag9;
    Vraag vraag10;
    Vraag vraag11;
    Vraag vraag12;
    Vraag vraag13;
    Vraag vraag14;


    @Before
    public void setUp() {
        KieServices kieServices = KieServices.Factory.get();

        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        kieSession = kieContainer.newKieSession(KIE_SESSION_NAME);

        kieSession.addEventListener(new DebugAgendaEventListener());

        vraag1 = new VraagBuilder().metVraag("Zijn er rondom de dakkapel dakpannen aanwezig?").metAntwoordType(AntwoordType.BOOLEAN.name()).build();
        vraag2 = new VraagBuilder().metVraag("Bouwt u de dakkapel op een schuin dak?").metAntwoordType(AntwoordType.BOOLEAN.name()).metAfhankelijkheden(Arrays.asList(vraag1)).build();
        vraag3 = new VraagBuilder().metVraag("Waar wordt de dakkapel gebouwd?").metAntwoordType(AntwoordType.CATEGORIE.name()).build();
        vraag4 = new VraagBuilder().metVraag("Kijkt de zijkant van de dakkapel uit over openbaar toegankelijk gebied?").metAntwoordType(AntwoordType.BOOLEAN.name()).metAfhankelijkheden(Arrays.asList(vraag3)).build();
        vraag5 = new VraagBuilder().metVraag("Heeft de dakkapel een plat dak?").metAntwoordType(AntwoordType.BOOLEAN.name()).build();
        vraag6 = new VraagBuilder().metVraag("Is de dakkapel hoger dan 1,75 meter?").metAntwoordType(AntwoordType.BOOLEAN.name()).metAfhankelijkheden(Arrays.asList(vraag5)).build();
        vraag7 = new VraagBuilder().metVraag("Wat is de afstand tussen de onderkant van de dakkapel en de dakvoet?").metAntwoordType(AntwoordType.CATEGORIE.name()).build();
        vraag8 = new VraagBuilder().metVraag("Is de afstand tussen de bovenkant van de dakkapel en de nok van het dak 0,5 meter of minder?").metAntwoordType(AntwoordType.BOOLEAN.name()).metAfhankelijkheden(Arrays.asList(vraag7)).build();
        vraag9 = new VraagBuilder().metVraag("Is op ieder punt van de dakkapel de afstand tot de zijkanten van het dak groter dan 0,5 meter?").metAntwoordType(AntwoordType.BOOLEAN.name()).metAfhankelijkheden(Arrays.asList(vraag8)).build();
        vraag10 = new VraagBuilder().metAntwoordType(AntwoordType.EXTERNAL.name()).build();
        vraag11 = new VraagBuilder().metVraag("Wordt de dakkapel gebouwd op een woonwagen?").metAntwoordType(AntwoordType.BOOLEAN.name()).build();
        vraag12 = new VraagBuilder().metVraag("Wordt de dakkapel gebouwd op een tijdelijk bouwwerk?").metAntwoordType(AntwoordType.BOOLEAN.name()).metAfhankelijkheden(Arrays.asList(vraag11)).build();
        vraag13 = new VraagBuilder().metVraag("Wordt de dakkapel gebouwd op een recreatiewoning?").metAntwoordType(AntwoordType.BOOLEAN.name()).metAfhankelijkheden(Arrays.asList(vraag12)).build();
        vraag14 = new VraagBuilder().metVraag(Antwoord.JA.name()).metAntwoordType(AntwoordType.CONSTANTE.name()).build();
    }

    @Test
    public void testHoeVeelStepHeeftVragen() {

        maaktBomen();

        for (Step step : steps) {
            kieSession.insert(step);
        }

        kieSession.fireAllRules();

        for (Object object : kieSession.getObjects()) {
            if (object instanceof GebruikerVragen) {
                GebruikerVragen gebruikerVragen = (GebruikerVragen) object;
                Assert.assertEquals(gebruikerVragen.getGebruikerVragenVoorElkGrondslags().size(), 2);
//                for (GebruikerVragenVoorElkGrondslag gebruikerVragenVoorElkGrondslag : gebruikerVragen.getGebruikerVragenVoorElkGrondslags()) {
//                    for (Map.Entry<Grondslag, List<Vraag>> entry : gebruikerVragenVoorElkGrondslag.getGebruikerGrondslagVragenMap().entrySet()) {
//                        System.out.println(entry.getKey().getGrondslagBeschrijving());
//                        for (Vraag vraag : entry.getValue()) {
//                            System.out.println("\t\t\t\t" + vraag.getVraag());
//                        }
//                    }
//                }
            }
        }
    }

    @Test
    public void testHoeVeelGrondslagHeeftVragen() {

        maaktBomen();

        for (Step step : steps) {
            kieSession.insert(step);
        }

        kieSession.fireAllRules();

        for (Object object : kieSession.getObjects()) {
            if (object instanceof GebruikerVragen) {
                GebruikerVragen gebruikerVragen = (GebruikerVragen) object;
                Assert.assertEquals(gebruikerVragen.getGebruikerVragenVoorElkGrondslags().get(0).getGebruikerGrondslagVragenMap().size(), 3);
                Assert.assertEquals(gebruikerVragen.getGebruikerVragenVoorElkGrondslags().get(1).getGebruikerGrondslagVragenMap().size(), 2);
            }
        }
    }

    @Test
    public void testEindResultaatVanRootStep() {

        vraag1.setAntwoord(Antwoord.JA.name());
        vraag2.setAntwoord(Antwoord.JA.name());
        vraag3.setAntwoord("Achterkant");
        vraag4.setAntwoord(Antwoord.NEE.name());

        maaktBomen();

        for (Step step : steps) {
            kieSession.insert(step);
        }

        kieSession.fireAllRules();

        for (Object object : kieSession.getObjects()) {
            if (object instanceof Step) {
                Step step = (Step) object;
                System.out.println(step.getStepBeschrijven());
                Assert.assertEquals(step.getDefinitieveBeslissing(), Antwoord.JA.name());
            }
        }
    }

    @After
    public void tearDown() {
        if (kieSession != null) {
            kieSession.dispose();
        }
    }

    private void maaktBomen() {
        Map<Vraag, Conversie> vraagConversieMap = new HashMap<>();
        vraagConversieMap.put(vraag1, new Conversie(Antwoord.JA.name()));
        Grondslag grondslag1 = new GrondslagBuilder().metGrondslagBeschrijving("Dakpannen rondom").metGrondslagType(GrondslagType.WAT_WERKWOORD.name()).metVragen(Arrays.asList(vraag1)).metConversiePerVragen(vraagConversieMap).build();
        vraagConversieMap = new HashMap<>();
        vraagConversieMap.put(vraag2, new Conversie(Antwoord.JA.name()));
        Grondslag grondslag2 = new GrondslagBuilder().metGrondslagBeschrijving("Op een schuin dak").metGrondslagType(GrondslagType.WAAR.name()).metVragen(Arrays.asList(vraag2)).metConversiePerVragen(vraagConversieMap).build();
        vraagConversieMap = new HashMap<>();
        vraagConversieMap.put(vraag3, new Conversie("Achterkant"));
        Grondslag grondslag3 = new GrondslagBuilder().metGrondslagBeschrijving("Op een achterdakvlak").metGrondslagType(GrondslagType.WAAR.name()).metVragen(Arrays.asList(vraag3)).metConversiePerVragen(vraagConversieMap).build();
        vraagConversieMap = new HashMap<>();
        vraagConversieMap.put(vraag3, new Conversie("Zijkant"));
        Grondslag grondslag4 = new GrondslagBuilder().metGrondslagBeschrijving("Op een zijdakvlak").metGrondslagType(GrondslagType.WAAR.name()).metVragen(Arrays.asList(vraag3)).metConversiePerVragen(vraagConversieMap).build();
        vraagConversieMap = new HashMap<>();
        vraagConversieMap.put(vraag4, new Conversie(Antwoord.NEE.name()));
        Grondslag grondslag5 = new GrondslagBuilder().metGrondslagBeschrijving("Naar openbaar toegankelijk gebied gekeerd").metGrondslagType(GrondslagType.HOE.name()).metVragen(Arrays.asList(vraag4)).metConversiePerVragen(vraagConversieMap).build();
//        Grondslag grondslag6 = new GrondslagBuilder().withGrondslagBeschrijving("Voorzien van een plat dak").withGrondslagType(GrondslagType.WAT_WERKWOORD).withVragen(Arrays.asList(vraag5)).build();
//        Grondslag grondslag7 = new GrondslagBuilder().withGrondslagBeschrijving("Hoger dan 1.75m, Gemeten vanaf de voet van de dakkapel").withGrondslagType(GrondslagType.WAT_WERKWOORD).withVragen(Arrays.asList(vraag6)).build();
//        Grondslag grondslag8 = new GrondslagBuilder().withGrondslagBeschrijving("Onderzijde meer dan 0,5 en minder dan 1 m boven de dakvoet.").withGrondslagType(GrondslagType.WAT_WERKWOORD).withVragen(Arrays.asList(vraag7)).build();
//        Grondslag grondslag9 = new GrondslagBuilder().withGrondslagBeschrijving("Bovenzijde meer dan 1 m onder de daknok").withGrondslagType(GrondslagType.WAT_WERKWOORD).withVragen(Arrays.asList(vraag8)).build();
//        Grondslag grondslag10 = new GrondslagBuilder().withGrondslagBeschrijving("Zijkanten meer dan 0,5 m van de zijkanten van het dakvlak").withGrondslagType(GrondslagType.WAT_WERKWOORD).withVragen(Arrays.asList(vraag9)).build();
//        Grondslag grondslag11 = new GrondslagBuilder().withGrondslagBeschrijving("Woonwagen").withGrondslagType(GrondslagType.WAAR).withVragen(Arrays.asList(vraag10,vraag11)).build();
//        Grondslag grondslag12 = new GrondslagBuilder().withGrondslagBeschrijving("Een gebouw waarvoor in de omgevingsvergunning voor het bouwen is bepaald dat het slechts voor een bepaalde periode in stand mag worden gehouden").withGrondslagType(GrondslagType.WAAR).withVragen(Arrays.asList(vraag12)).build();
//        Grondslag grondslag13 = new GrondslagBuilder().withGrondslagBeschrijving("Een bouwwerk ten behoeve van recreatief nachtverblijf door één huishouden").withGrondslagType(GrondslagType.WAAR).withVragen(Arrays.asList(vraag10,vraag13)).build();
//        Grondslag grondslag14 = new GrondslagBuilder().withGrondslagBeschrijving("Op een achterdakvlak").withGrondslagType(GrondslagType.WAAR).withVragen(Arrays.asList(vraag3)).build();
//        Grondslag grondslag15 = new GrondslagBuilder().withGrondslagBeschrijving("Op een zijdakvlak").withGrondslagType(GrondslagType.WAAR).withVragen(Arrays.asList(vraag3)).build();
//        Grondslag grondslag16 = new GrondslagBuilder().withGrondslagBeschrijving("Naar openbaar toegankelijk gebied gekeerd").withGrondslagType(GrondslagType.HOE).withVragen(Collections.EMPTY_LIST).build();
//        Grondslag grondslag17 = new GrondslagBuilder().withGrondslagBeschrijving("Op een voordakvlak").withGrondslagType(GrondslagType.WAAR).withVragen(Arrays.asList(vraag3)).build();
//        Grondslag grondslag18 = new GrondslagBuilder().withGrondslagBeschrijving("Voorzien van een plat dak").withGrondslagType(GrondslagType.WAT_WERKWOORD).withVragen(Arrays.asList(vraag5)).build();
//        Grondslag grondslag19 = new GrondslagBuilder().withGrondslagBeschrijving("Hoger dan 1,75 m, Gemeten vanaf de voet van de dakkapel").withGrondslagType(GrondslagType.WAT_WERKWOORD).withVragen(Arrays.asList(vraag6)).build();
//        Grondslag grondslag20 = new GrondslagBuilder().withGrondslagBeschrijving("Onderzijde meer dan 0,5 en minder dan 1 m boven de dakvoet.").withGrondslagType(GrondslagType.WAT_WERKWOORD).withVragen(Arrays.asList(vraag7)).build();
//        Grondslag grondslag21 = new GrondslagBuilder().withGrondslagBeschrijving("Bovenzijde meer dan 1 m onder de daknok").withGrondslagType(GrondslagType.WAT_WERKWOORD).withVragen(Arrays.asList(vraag8)).build();
//        Grondslag grondslag22 = new GrondslagBuilder().withGrondslagBeschrijving("Zijkanten meer dan 0,5 m van de zijkanten van het dakvlak").withGrondslagType(GrondslagType.WAT_WERKWOORD).withVragen(Arrays.asList(vraag9)).build();
//        Grondslag grondslag23 = new GrondslagBuilder().withGrondslagBeschrijving("Redelijke eisen van welstand zijn van toepassing").withGrondslagType(GrondslagType.WANNEER).withVragen(Arrays.asList(vraag14)).build();

        List<Grondslag> grondslags = Arrays.asList(grondslag1, grondslag2);

        Map<Step, Conversie> stepConversieMap = new HashMap<>();
        Map<Grondslag, Conversie> grondslagConversieMap = new HashMap<>();
        grondslagConversieMap.put(grondslag1, new Conversie(Antwoord.JA.name()));
        grondslagConversieMap.put(grondslag2, new Conversie((Antwoord.JA.name())));

        Map<Step, String> stepConditieMap = new HashMap<>();
        Map<Grondslag, String> grondslagConditieMap = new HashMap<>();
        grondslagConditieMap.put(grondslag1, Conditie.EN.name());
        grondslagConditieMap.put(grondslag2, Conditie.EN.name());

        Step step1 = new StepBuilder().metStepBeschrijven("Definitie").metGrondslags(grondslags).metGrondslagConversie(grondslagConversieMap).metGrondslagConditie(grondslagConditieMap).build();

        stepConversieMap = new HashMap<>();
        grondslagConversieMap = new HashMap<>();
        grondslagConversieMap.put(grondslag4, new Conversie(Antwoord.JA.name()));
        grondslagConversieMap.put(grondslag5, new Conversie((Antwoord.NEE.name())));

        grondslagConditieMap = new HashMap<>();
        grondslagConditieMap.put(grondslag4, Conditie.EN.name());
        grondslagConditieMap.put(grondslag5, Conditie.EN.name());

        grondslags = Arrays.asList(grondslag4, grondslag5);

        Step step2 = new StepBuilder().metStepBeschrijven("Niet zichtbaar zijdakvlak").metGrondslags(grondslags).metGrondslagConversie(grondslagConversieMap).metGrondslagConditie(grondslagConditieMap).build();

        stepConversieMap = new HashMap<>();
        stepConversieMap.put(step2, new Conversie(Antwoord.JA.name()));
        grondslagConversieMap = new HashMap<>();
        grondslagConversieMap.put(grondslag3, new Conversie(Antwoord.JA.name()));

        stepConditieMap = new HashMap<>();
        stepConditieMap.put(step2, Conditie.OF.name());
        grondslagConditieMap = new HashMap<>();
        grondslagConditieMap.put(grondslag3, Conditie.OF.name());

        grondslags = Arrays.asList(grondslag3);

        List<Step> lokalSteps = Arrays.asList(step2);

        Step step3 = new StepBuilder().metStepBeschrijven("Niet zichtbaar dakvlak").metSteps(lokalSteps).metGrondslags(grondslags).metStepConversie(stepConversieMap)
                .metStepConditie(stepConditieMap).metGrondslagConditie(grondslagConditieMap).metGrondslagConversie(grondslagConversieMap).build();

        stepConversieMap = new HashMap<>();
        stepConversieMap.put(step3, new Conversie(Antwoord.JA.name()));
        grondslagConversieMap = new HashMap<>();

        stepConditieMap = new HashMap<>();
        stepConditieMap.put(step3, Conditie.EN.name());

        lokalSteps = Arrays.asList(step3);

        Step step4 = new StepBuilder().metStepBeschrijven("Dakkapel in achterdakvlak of niet naar openbaar toegankellijk gebled gekeerd zijdakvlak").metSteps(lokalSteps)
                .metStepConversie(stepConversieMap).metStepConditie(stepConditieMap).build();


        stepConversieMap = new HashMap<>();
        stepConversieMap.put(step4, new Conversie(Antwoord.JA.name()));
        grondslagConversieMap = new HashMap<>();

        stepConditieMap = new HashMap<>();
        stepConditieMap.put(step4, Conditie.OF.name());

        lokalSteps = Arrays.asList(step4);

        Step step5 = new StepBuilder().metStepBeschrijven("Zichtbaar of niet").metSteps(lokalSteps).
                metStepConversie(stepConversieMap).metStepConditie(stepConditieMap).build();

        stepConversieMap = new HashMap<>();
        stepConversieMap.put(step5, new Conversie(Antwoord.JA.name()));
        grondslagConversieMap = new HashMap<>();

        stepConditieMap = new HashMap<>();
        stepConditieMap.put(step5, Conditie.EN.name());

        lokalSteps = Arrays.asList(step5);

        Step step6 = new StepBuilder().metStepBeschrijven("Voorwaarden vergunningvrij").metSteps(lokalSteps).
                metStepConversie(stepConversieMap).metStepConditie(stepConditieMap).build();

        steps = Arrays.asList(step1, step6);
    }
}
