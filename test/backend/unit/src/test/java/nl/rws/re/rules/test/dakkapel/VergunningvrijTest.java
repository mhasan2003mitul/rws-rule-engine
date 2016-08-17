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
 * Created by Md. Mainul Hasan Patwary on 09-Aug-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */
public class VergunningvrijTest extends BaseTest{
    @Before
    public void setUp() {
    }

    @Test
    public void testVergunningvrijRuleVraagId() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

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

        kieSession.insert(new Node(5,Antwoord.JA.name()));
        kieSession.insert(new Node(7,Antwoord.JA.name()));
        kieSession.insert(new Node(8,Antwoord.NEE.name()));

        kieSession.fireAllRules();

        printAllObjects(kieSession.getObjects());

        List<Node> nodes = new ArrayList<>();
        Integer []ids = new Integer[kieSession.getObjects().size()];

        for (int i = 0; i< kieSession.getObjects().size(); i++) {
            Object obj = kieSession.getObjects().toArray()[i];
            if (obj instanceof Node) {
                Node node = (Node) obj;
                nodes.add(node);
                ids[i]=node.getId();
            }
        }

        Assert.assertThat(8, Matchers.equalTo(nodes.size()));
        Assert.assertThat(1, Matchers.isIn(ids));
        for(Node n: nodes){
            if(n.getId() == 1){
                Assert.assertThat(Antwoord.JA.name(), Matchers.equalTo(n.getAntwoord()));
            }
        }
    }

    @Test
    public void testVergunningvrijRuleNeeAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        kieSession.insert(new Node(5,Antwoord.NEE.name()));
        kieSession.insert(new Node(7,Antwoord.JA.name()));
        kieSession.insert(new Node(8,Antwoord.JA.name()));

        kieSession.fireAllRules();

        printAllObjects(kieSession.getObjects());

        List<Node> nodes = new ArrayList<>();
        Integer []ids = new Integer[kieSession.getObjects().size()];

        for (int i = 0; i< kieSession.getObjects().size(); i++) {
            Object obj = kieSession.getObjects().toArray()[i];
            if (obj instanceof Node) {
                Node node = (Node) obj;
                nodes.add(node);
                ids[i]=node.getId();
            }
        }

        Assert.assertThat(8, Matchers.equalTo(nodes.size()));
        Assert.assertThat(1, Matchers.isIn(ids));
        for(Node n: nodes){
            if(n.getId() == 1){
                Assert.assertThat(Antwoord.NEE.name(), Matchers.equalTo(n.getAntwoord()));
            }
        }    }

    @After
    public void tearDown() {
        if (kieSession != null) {
            kieSession.dispose();
        }
    }

}
