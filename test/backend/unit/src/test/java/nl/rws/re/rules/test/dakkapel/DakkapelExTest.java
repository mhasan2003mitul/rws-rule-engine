package nl.rws.re.rules.test.dakkapel;

import nl.rws.re.facts.dakkapelx.*;
import nl.rws.re.facts.error.Error;
import nl.rws.re.facts.error.ErrorMessage;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by Md. Mainul Hasan Patwary on 09-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class DakkapelExTest {
    private String kieSessionName;
    private KieSession kieSession;

    private Vraag vraag1;
    private Vraag vraag2;
    private Vraag vraag3;
    private Vraag vraag4;
    private Vraag vraag5;

    private Grondslag grondslag1;
    private Grondslag grondslag2;
    private Grondslag grondslag3;
    private Grondslag grondslag4;
    private Grondslag grondslag5;


    @Before
    public void setUp() {

        vraag1 = new VraagBuilder().metVraagId("1").build();
        vraag2 = new VraagBuilder().metVraagId("2").build();
        vraag3 = new VraagBuilder().metVraagId("3").build();
        vraag4 = new VraagBuilder().metVraagId("4").build();
        vraag5 = new VraagBuilder().metVraagId("5").build();

        grondslag1 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.DAKPANNEN_RONDOM.toString())
                .metGrondslagType(GrondslagType.WAT_WERKWOORD.name())
                .metVragen(new Vraag[]{vraag1}).build();
        grondslag2 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.OP_EEN_SCHUIN_DAK.toString())
                .metGrondslagType(GrondslagType.WAAR.name())
                .metVragen(new Vraag[]{vraag2}).build();
        grondslag3 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.OP_EEN_ACHTERDAKVLAK.toString())
                .metGrondslagType(GrondslagType.WAAR.name())
                .metVragen(new Vraag[]{vraag3}).build();
        grondslag4 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.OP_EEN_ZIJDAKVLAK.toString())
                .metGrondslagType(GrondslagType.WAAR.name())
                .metVragen(new Vraag[]{vraag3}).build();
        grondslag5 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.NAAR_OPENBAAR_TOEGELIJK_GEBIED_GEKEERD.toString())
                .metGrondslagType(GrondslagType.HOE.name())
                .metVragen(new Vraag[]{vraag4}).build();
    }

    private KieSession getKieSession(String sessionName) {
        KieServices kieServices = KieServices.Factory.get();

        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        kieSession = kieContainer.newKieSession(sessionName);

        kieSession.addEventListener(new DebugAgendaEventListener());

        return kieSession;
    }

    @Test
    public void testIsDefinitieHeeftGrondslags() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        kieSession.insert(grondslag1);

        kieSession.fireAllRules();

        Error error = null;
        for (Object object : kieSession.getObjects()) {
            if (object instanceof Error) {
                error = (Error) object;
            }
        }

        Assert.assertThat(error, Matchers.notNullValue());
        Assert.assertThat(ErrorMessage.DEFINITIE_HEEFT_GEEN_GRONDSLAG.toString(), Matchers.equalTo(error.getMessage()));
    }

    @Test
    public void testIsDakpannenRondomGrondslagsVragenHeeftAntwoord() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        kieSession.insert(grondslag1);
        kieSession.insert(grondslag2);

        kieSession.fireAllRules();

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(GebruikerVragen.class));
        Assert.assertThat(1, Matchers.equalTo(((GebruikerVragen) kieSession.getObjects().toArray()[0]).getGebruikerVragen().length));
        Assert.assertThat("1", Matchers.equalTo(((GebruikerVragen) kieSession.getObjects().toArray()[0]).getGebruikerVragen()[0].getVraagId()));
    }

    @Test
    public void testIsOpEenSchuinDakVragenHeeftAntwoord() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        vraag1.setAntwoord(Antwoord.JA.name());
        kieSession.insert(grondslag1);
        kieSession.insert(grondslag2);

        kieSession.fireAllRules();

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(GebruikerVragen.class));
        Assert.assertThat(1, Matchers.equalTo(((GebruikerVragen) kieSession.getObjects().toArray()[0]).getGebruikerVragen().length));
        Assert.assertThat("2", Matchers.equalTo(((GebruikerVragen) kieSession.getObjects().toArray()[0]).getGebruikerVragen()[0].getVraagId()));
    }

    @Test
    public void testDefinitieRuleJAAntwoord() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        vraag1.setAntwoord(Antwoord.JA.name());
        kieSession.insert(grondslag1);
        vraag2.setAntwoord(Antwoord.JA.name());
        kieSession.insert(grondslag2);

        kieSession.fireAllRules();

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(RuleResultaat.class));
        Assert.assertThat(Antwoord.JA.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }

    @Test
    public void testDefinitieRuleNEEAntwoord() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        vraag1.setAntwoord(Antwoord.NEE.name());
        kieSession.insert(grondslag1);
        vraag2.setAntwoord(Antwoord.JA.name());
        kieSession.insert(grondslag2);

        kieSession.fireAllRules();

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(RuleResultaat.class));
        Assert.assertThat(Antwoord.NEE.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }


    @Test
    public void testIsNietZichtbaarZijdakvlakHeeftGrondslags() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        kieSession.insert(grondslag4);

        kieSession.fireAllRules();

        Error error = null;
        for (Object object : kieSession.getObjects()) {
            if (object instanceof Error) {
                error = (Error) object;
            }
        }

        Assert.assertThat(error,Matchers.notNullValue());
        Assert.assertThat(ErrorMessage.NIET_ZICHTBAAR_ZIJDAKVLAK.toString(), Matchers.equalTo(error.getMessage()));
    }

    @Test
    public void testIsOpeenzijdakvlakGrondslagsVragenHeeftAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        kieSession.insert(grondslag4);
        kieSession.insert(grondslag5);

        kieSession.fireAllRules();

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(GebruikerVragen.class));
        Assert.assertThat(1, Matchers.equalTo(((GebruikerVragen) kieSession.getObjects().toArray()[0]).getGebruikerVragen().length));
        Assert.assertThat("3", Matchers.equalTo(((GebruikerVragen) kieSession.getObjects().toArray()[0]).getGebruikerVragen()[0].getVraagId()));
    }

    @Test
    public void testIsOpenbaarToegankelijkHeeftAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        vraag3.setAntwoord(DakkapelKant.ZIJKANT.name());
        kieSession.insert(grondslag4);
        kieSession.insert(grondslag5);

        kieSession.fireAllRules();

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(GebruikerVragen.class));
        Assert.assertThat(1, Matchers.equalTo(((GebruikerVragen) kieSession.getObjects().toArray()[0]).getGebruikerVragen().length));
        Assert.assertThat("4", Matchers.equalTo(((GebruikerVragen) kieSession.getObjects().toArray()[0]).getGebruikerVragen()[0].getVraagId()));
    }

    @Test
    public void testNietZichtbaarZijdakvlakRuleJAAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        vraag3.setAntwoord(DakkapelKant.ZIJKANT.name());
        kieSession.insert(grondslag4);
        vraag4.setAntwoord(Antwoord.NEE.name());
        kieSession.insert(grondslag5);

        kieSession.fireAllRules();

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(RuleResultaat.class));
        Assert.assertThat(Antwoord.JA.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }

    @Test
    public void testNietZichtbaarZijdakvlakRuleNEEAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        vraag3.setAntwoord(DakkapelKant.ZIJKANT.name());
        kieSession.insert(grondslag4);
        vraag4.setAntwoord(Antwoord.JA.name());
        kieSession.insert(grondslag5);

        kieSession.fireAllRules();

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(RuleResultaat.class));
        Assert.assertThat(Antwoord.NEE.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }

    @Test
    public void testIsNietZichtbaarDakvlakHeeftGrondslags() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        vraag3.setAntwoord(DakkapelKant.ZIJKANT.name());
        kieSession.insert(grondslag4);
        vraag4.setAntwoord(Antwoord.JA.name());
        kieSession.insert(grondslag5);

        kieSession.fireAllRules();

        Error error = null;
        for (Object object : kieSession.getObjects()) {
            System.out.print(object.toString());
            if (object instanceof Error) {
                error = (Error)object;
            }
        }

        Assert.assertThat(error,Matchers.notNullValue());
        Assert.assertThat(ErrorMessage.NIET_ZICHTBAAR_DAKVLAK.toString(), Matchers.equalTo(((Error) error).getMessage()));
    }

    @Test
    public void testIsNietZichtbaarDakvlakGrondslagsVragenHeeftAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        vraag3.setAntwoord(DakkapelKant.ZIJKANT.name());
        kieSession.insert(grondslag4);
        vraag4.setAntwoord(Antwoord.JA.name());
        kieSession.insert(grondslag5);

        grondslag3.setVragen(new Vraag[]{new Vraag(vraag3.getVraagId(), null)});
        kieSession.insert(grondslag3);

        kieSession.fireAllRules();

        GebruikerVragen gebruikerVragen = null;
        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof GebruikerVragen) {
                gebruikerVragen = (GebruikerVragen) obj;
            }
        }

        Assert.assertThat(gebruikerVragen, Matchers.notNullValue());
        Assert.assertThat(1, Matchers.equalTo(((GebruikerVragen) gebruikerVragen).getGebruikerVragen().length));
        Assert.assertThat("3", Matchers.equalTo(((GebruikerVragen) gebruikerVragen).getGebruikerVragen()[0].getVraagId()));

    }

    @Test
    public void testNietZichtbaarDakvlakRuleJAAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        vraag3.setAntwoord(DakkapelKant.ZIJKANT.name());
        kieSession.insert(grondslag4);
        vraag4.setAntwoord(Antwoord.JA.name());
        kieSession.insert(grondslag5);

        Vraag vraag = new VraagBuilder().metVraagId(vraag3.getVraagId()).metAntwoord(DakkapelKant.ACHTERKANT.name()).build();

        grondslag3.setVragen(new Vraag[]{vraag});

        kieSession.insert(grondslag3);

        kieSession.fireAllRules();

        for(Object obj: kieSession.getObjects()){
            System.out.println(obj.toString());
            if(obj instanceof  Error){
                System.out.println(((Error)obj).getMessage());
            }else if(obj instanceof  RuleResultaat){
                System.out.println(((RuleResultaat)obj).getResultaat());
            }else if(obj instanceof  Grondslag){
                System.out.println(((Grondslag)obj).getGrondslagBeschrijving());
            }
        }

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(NietZichtbaarDakvlakRuleResultaat.class));
        Assert.assertThat(Antwoord.JA.name(), Matchers.equalTo(((NietZichtbaarDakvlakRuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }

    @Test
    public void testNietZichtbaarDakvlakRuleNEEAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        vraag3.setAntwoord(DakkapelKant.VOORKANT.name());
        kieSession.insert(grondslag4);
        vraag4.setAntwoord(Antwoord.JA.name());
        kieSession.insert(grondslag5);

        kieSession.insert(grondslag3);

        kieSession.fireAllRules();

        for(Object obj: kieSession.getObjects()){
            System.out.println(obj.toString());
            if(obj instanceof  Error){
                System.out.println(((Error)obj).getMessage());
            }else if(obj instanceof  RuleResultaat){
                System.out.println(((RuleResultaat)obj).getResultaat());
            }else if(obj instanceof  Grondslag){
                System.out.println(((Grondslag)obj).getGrondslagBeschrijving());
            }
        }

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(NietZichtbaarDakvlakRuleResultaat.class));
        Assert.assertThat(Antwoord.NEE.name(), Matchers.equalTo(((NietZichtbaarDakvlakRuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }

    @After
    public void tearDown() {
        if (kieSession != null) {
            kieSession.dispose();
        }
    }
}
