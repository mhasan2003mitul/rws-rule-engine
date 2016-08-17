package nl.rws.re.rules.test.dakkapel;

import nl.rws.re.facts.dakkapelx.*;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Md. Mainul Hasan Patwary on 11-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class DefinitieTest extends BaseTest {

    @Before
    public void setUp() {
    }

    @Test
    public void testDefinitieRuleVraagId() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        kieSession.fireAllRules();

        printAllObjects(kieSession.getObjects());

        List<Vraag> vragen = new ArrayList<>();

        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof Vraag) {
                vragen.add((Vraag) obj);
            }
        }
        Assert.assertThat(2, Matchers.equalTo(vragen.size()));
        Assert.assertThat("1", Matchers.isIn(new String[]{vragen.get(0).getVraagId(), vragen.get(1).getVraagId()}));
        Assert.assertThat("2", Matchers.isIn(new String[]{vragen.get(0).getVraagId(), vragen.get(1).getVraagId()}));
    }

    @Test
    public void testDefinitieRuleJAAntwoord() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        kieSession.insert(new Node(2,Antwoord.JA.name()));
        kieSession.insert(new Node(3,Antwoord.JA.name()));

        kieSession.fireAllRules();

        List<Node> nodes = new ArrayList<>();

        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof Node) {
                nodes.add((Node) obj);
            }
        }

        printAllObjects(kieSession.getObjects());

        Assert.assertThat(3, Matchers.equalTo(nodes.size()));
        Assert.assertThat(1, Matchers.isIn(new Integer[]{nodes.get(0).getId(), nodes.get(1).getId(),nodes.get(2).getId()}));
        for(Node n: nodes){
            if(n.getId() == 1){
                Assert.assertThat("JA", Matchers.equalTo(n.getAntwoord()));
            }
        }
    }

    @Test
    public void testDefinitieRuleNEEAntwoord() {

        kieSession = getKieSession("dakkapelExDefinitieSession");

        kieSession.insert(new Node(2,Antwoord.NEE.name()));
        kieSession.insert(new Node(3,Antwoord.JA.name()));

        kieSession.fireAllRules();

        printAllObjects(kieSession.getObjects());

        List<Node> nodes = new ArrayList<>();

        for (Object obj : kieSession.getObjects()) {
            if (obj instanceof Node) {
                nodes.add((Node) obj);
            }
        }

        Assert.assertThat(3, Matchers.equalTo(nodes.size()));
        Assert.assertThat(1, Matchers.isIn(new Integer[]{nodes.get(0).getId(), nodes.get(1).getId(),nodes.get(2).getId()}));
        for(Node n: nodes){
            if(n.getId() == 1){
                Assert.assertThat("NEE", Matchers.equalTo(n.getAntwoord()));
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
