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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Md. Mainul Hasan Patwary on 09-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class VergunningvrijTest extends BaseTest{



    private Vraag vraag3;
    private Vraag vraag4;
    private Vraag vraag5;

    private Grondslag grondslag3;
    private Grondslag grondslag4;
    private Grondslag grondslag5;


    @Before
    public void setUp() {

        vraag3 = new VraagBuilder().metVraagId("3").build();
        vraag4 = new VraagBuilder().metVraagId("4").build();
        vraag5 = new VraagBuilder().metVraagId("5").build();

        grondslag3 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.OP_EEN_ACHTERDAKVLAK.toString())
                .metGrondslagType(GrondslagType.WAAR.name()).build();
        grondslag4 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.OP_EEN_ZIJDAKVLAK.toString())
                .metGrondslagType(GrondslagType.WAAR.name()).build();
        grondslag5 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.NAAR_OPENBAAR_TOEGELIJK_GEBIED_GEKEERD.toString())
                .metGrondslagType(GrondslagType.HOE.name()).build();
    }

    @Test
    public void testVergunningvrijRuleVraagId() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        kieSession.insert(grondslag3);
        kieSession.insert(grondslag4);
        kieSession.insert(grondslag5);

        kieSession.fireAllRules();

        printAllObjects(kieSession.getObjects());

        List<Vraag> vragen = new ArrayList<>();

        for(Object obj: kieSession.getObjects()){
            if(obj instanceof Vraag){
                vragen.add((Vraag) obj);
            }
        }
        Assert.assertThat(3,Matchers.equalTo(vragen.size()));
        Assert.assertThat("3", Matchers.isIn(new String[]{vragen.get(0).getVraagId(),vragen.get(1).getVraagId(),vragen.get(2).getVraagId()}));
        Assert.assertThat("4", Matchers.isIn(new String[]{vragen.get(0).getVraagId(),vragen.get(1).getVraagId(),vragen.get(2).getVraagId()}));
    }


    @Test
    public void testVergunningvrijRuleJAAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        vraag3.setAntwoord(DakkapelKant.ACHTERKANT.name());
        kieSession.insert(vraag3);
        vraag4.setAntwoord(Antwoord.NEE.name());
        kieSession.insert(vraag4);

        kieSession.fireAllRules();

        printAllObjects(kieSession.getObjects());

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(RuleResultaat.class));
        Assert.assertThat(RuleResultaat.ResultaatVoor.VOORWAARDEN_VERGUNNINGVRIJ.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaatVoor()));
        Assert.assertThat(Antwoord.JA.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }

    @Test
    public void testVergunningvrijRuleNeeAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        vraag3.setAntwoord(DakkapelKant.ZIJKANT.name());
        kieSession.insert(vraag3);
        vraag4.setAntwoord(Antwoord.JA.name());
        kieSession.insert(vraag4);

        kieSession.fireAllRules();

        printAllObjects(kieSession.getObjects());

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(RuleResultaat.class));
        Assert.assertThat(RuleResultaat.ResultaatVoor.VOORWAARDEN_VERGUNNINGVRIJ.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaatVoor()));
        Assert.assertThat(Antwoord.NEE.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }

    @After
    public void tearDown() {
        if (kieSession != null) {
            kieSession.dispose();
        }
    }

}
