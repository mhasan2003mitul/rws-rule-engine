package nl.rws.re.rules.test.dakkapel;

import nl.rws.re.facts.dakkapelx.*;
import nl.rws.re.facts.dakkapelx.Error;
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
 * Created by Md. Mainul Hasan Patwary on 11-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class DefinitieTestV2 extends BaseTest{
    private Vraag vraag1;
    private Vraag vraag2;

    private Grondslag grondslag1;
    private Grondslag grondslag2;

    @Before
    public void setUp() {

        vraag1 = new VraagBuilder().metVraagId("1").build();
        vraag2 = new VraagBuilder().metVraagId("2").build();

        grondslag1 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.DAKPANNEN_RONDOM.toString())
                .metGrondslagType(GrondslagType.WAT_WERKWOORD.name()).build();
        grondslag2 = new GrondslagBuilder().metGrondslagBeschrijving(GrondslagBeschrijving.OP_EEN_SCHUIN_DAK.toString())
                .metGrondslagType(GrondslagType.WAAR.name()).build();
    }

    @Test
    public void testDefinitieRuleVraagId() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        kieSession.insert(grondslag1);
        kieSession.insert(grondslag2);

        kieSession.fireAllRules();

        printAllObjects(kieSession.getObjects());

        List<Vraag> vragen = new ArrayList<>();

        for(Object obj: kieSession.getObjects()){
            if(obj instanceof Vraag){
                vragen.add((Vraag) obj);
            }
        }
        Assert.assertThat(2,Matchers.equalTo(vragen.size()));
        Assert.assertThat("1", Matchers.isIn(new String[]{vragen.get(0).getVraagId(),vragen.get(1).getVraagId()}));
        Assert.assertThat("2", Matchers.isIn(new String[]{vragen.get(0).getVraagId(),vragen.get(1).getVraagId()}));
    }

    @Test
    public void testDefinitieRuleJAAntwoord() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        vraag1.setAntwoord(Antwoord.JA.name());
        kieSession.insert(vraag1);
        vraag2.setAntwoord(Antwoord.JA.name());
        kieSession.insert(vraag2);

        kieSession.fireAllRules();

        printAllObjects(kieSession.getObjects());

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(RuleResultaat.class));
        Assert.assertThat(RuleResultaat.ResultaatVoor.DEFINITIE.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaatVoor()));
        Assert.assertThat(Antwoord.JA.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }

    @Test
    public void testDefinitieRuleNEEAntwoord() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        vraag1.setAntwoord(Antwoord.NEE.name());
        kieSession.insert(vraag1);
        vraag2.setAntwoord(Antwoord.JA.name());
        kieSession.insert(vraag2);

        kieSession.fireAllRules();

        printAllObjects(kieSession.getObjects());

        Assert.assertThat(kieSession.getObjects().toArray()[0],Matchers.instanceOf(RuleResultaat.class));
        Assert.assertThat(RuleResultaat.ResultaatVoor.DEFINITIE.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaatVoor()));
        Assert.assertThat(Antwoord.NEE.name(), Matchers.equalTo(((RuleResultaat) kieSession.getObjects().toArray()[0]).getResultaat()));
    }

    @After
    public void tearDown() {
        if (kieSession != null) {
            kieSession.dispose();
        }
    }

}
