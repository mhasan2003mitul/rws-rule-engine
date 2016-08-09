package nl.rws.re.rules.test.dakkapel;

import nl.rws.re.facts.dakkapelx.*;
import nl.rws.re.facts.error.Error;
import nl.rws.re.facts.error.ErrorMessage;
import org.hamcrest.Matchers;
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
    private final static String KIE_SESSION_NAME = "dakkapelExSession";
    private KieSession kieSession;

    private Vraag vraag1;
    private Vraag vraag2;

    private Grondslag grondslag1;
    private Grondslag grondslag2;

    @Before
    public void setUp() {
        KieServices kieServices = KieServices.Factory.get();

        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        kieSession = kieContainer.newKieSession(KIE_SESSION_NAME);

        kieSession.addEventListener(new DebugAgendaEventListener());

        vraag1 = new VraagBuilder().metVraagId("1").build();
        vraag2 = new VraagBuilder().metVraagId("2").build();

        grondslag1 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.DAKPANNEN_RONDOM.toString())
                .metGrondslagType(GrondslagType.WAT_WERKWOORD.name())
                .metVragen(new Vraag[]{vraag1}).build();
        grondslag2 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.OP_EEN_SCHUIN_DAK.toString())
                .metGrondslagType(GrondslagType.WAAR.name())
                .metVragen(new Vraag[]{vraag2}).build();
    }

    @Test
    public void testIsDefinitieHeeftGrondslags(){

        kieSession.insert(grondslag1);

        kieSession.fireAllRules();

        for(Object object: kieSession.getObjects()){
            if(object instanceof Error){
                Assert.assertThat(ErrorMessage.DEFINITIE_HEEFT_GEEN_GRONDSLAG.toString(), Matchers.equalTo(((Error) object).getMessage()));
            }
        }
    }

    @Test
    public void testIsDakpannenRondomGrondslagsVragenHeeftAntwoord(){
        kieSession.insert(grondslag1);
        kieSession.insert(grondslag2);

        kieSession.fireAllRules();

        Assert.assertThat(true, Matchers.equalTo(kieSession.getObjects().toArray()[0] instanceof GebruikerVragen));
        Assert.assertThat(1, Matchers.equalTo(((GebruikerVragen)kieSession.getObjects().toArray()[0]).getGebruikerVragen().length));
        Assert.assertThat("1", Matchers.equalTo(((GebruikerVragen)kieSession.getObjects().toArray()[0]).getGebruikerVragen()[0].getVraagId()));
    }

}
