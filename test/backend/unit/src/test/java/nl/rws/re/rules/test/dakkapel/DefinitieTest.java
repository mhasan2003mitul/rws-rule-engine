package nl.rws.re.rules.test.dakkapel;

import nl.rws.re.facts.dakkapelx.*;
import nl.rws.re.facts.dakkapelx.Error;
import nl.rws.re.facts.dakkapelx.ErrorMessage;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Collection;

/**
 * Created by Md. Mainul Hasan Patwary on 11-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class DefinitieTest {
    private String kieSessionName;
    private KieSession kieSession;

    private Vraag vraag1;
    private Vraag vraag2;

    private Grondslag grondslag1;
    private Grondslag grondslag2;

    @Before
    public void setUp() {

        vraag1 = new VraagBuilder().metVraagId("1").build();
        vraag2 = new VraagBuilder().metVraagId("2").build();

        grondslag1 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.DAKPANNEN_RONDOM.toString())
                .metGrondslagType(GrondslagType.WAT_WERKWOORD.name())
                .metVragen(new Vraag[]{vraag1}).build();
        grondslag2 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.OP_EEN_SCHUIN_DAK.toString())
                .metGrondslagType(GrondslagType.WAAR.name())
                .metVragen(new Vraag[]{vraag2}).build();
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

        printAllObjects(kieSession.getObjects());

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

        printAllObjects(kieSession.getObjects());

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

        printAllObjects(kieSession.getObjects());

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

        printAllObjects(kieSession.getObjects());

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

        printAllObjects(kieSession.getObjects());

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(RuleResultaat.class));
        Assert.assertThat(Antwoord.NEE.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }

    private void printAllObjects(Collection<? extends  Object> objects) {
        for(Object obj:objects){
            System.out.println(obj.toString());
            if(obj instanceof Error){
                System.out.println(((Error)obj).getMessage());
            }else if(obj instanceof RuleResultaat){
                System.out.println(((RuleResultaat)obj).getResultaat());
            }else if(obj instanceof Grondslag){
                System.out.println(((Grondslag)obj).getGrondslagBeschrijving());
            }else if(obj instanceof RuleResultaat){
                System.out.println(((RuleResultaat)obj).getResultaat());
            }else if(obj instanceof GebruikerVragen){
                System.out.println(((GebruikerVragen)obj).getGebruikerVragen()[0]);
            }
        }
    }

    @After
    public void tearDown() {
        if (kieSession != null) {
            kieSession.dispose();
        }
    }

}
