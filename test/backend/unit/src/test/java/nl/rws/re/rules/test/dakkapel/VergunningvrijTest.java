package nl.rws.re.rules.test.dakkapel;

import nl.rws.re.facts.Antwoord;
import nl.rws.re.facts.Node;
import nl.rws.re.facts.Vraag;
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

        List<String> vragen = new ArrayList<>();

        for(Object obj: kieSession.getObjects()){
            if(obj instanceof Vraag){
                vragen.add(((Vraag) obj).getVraagId());
            }
        }
        Assert.assertThat(25,Matchers.equalTo(vragen.size()));
        Assert.assertThat("3", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("4", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("5", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("6", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("7", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("8", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("9", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("10", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("11", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("12", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("13", Matchers.isIn(vragen.toArray()));
        Assert.assertThat("14", Matchers.isIn(vragen.toArray()));
    }


    @Test
    public void testVergunningvrijRuleJAAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        kieSession.insert(new Node(12, Antwoord.JA.getValue()));
        kieSession.insert(new Node(13,Antwoord.JA.getValue()));
        kieSession.insert(new Node(14,Antwoord.NEE.getValue()));
        kieSession.insert(new Node(15,Antwoord.JA.getValue()));
        kieSession.insert(new Node(16,Antwoord.JA.getValue()));
        kieSession.insert(new Node(17,Antwoord.JA.getValue()));
        kieSession.insert(new Node(18,Antwoord.JA.getValue()));
        kieSession.insert(new Node(19,Antwoord.JA.getValue()));
        kieSession.insert(new Node(20,Antwoord.JA.getValue()));
        kieSession.insert(new Node(21,Antwoord.JA.getValue()));
        kieSession.insert(new Node(22,Antwoord.JA.getValue()));
        kieSession.insert(new Node(23,Antwoord.JA.getValue()));
        kieSession.insert(new Node(24,Antwoord.JA.getValue()));
        kieSession.insert(new Node(25,Antwoord.JA.getValue()));
        kieSession.insert(new Node(26,Antwoord.JA.getValue()));
        kieSession.insert(new Node(27,Antwoord.JA.getValue()));
        kieSession.insert(new Node(28,Antwoord.JA.getValue()));
        kieSession.insert(new Node(29,Antwoord.JA.getValue()));
        kieSession.insert(new Node(30,Antwoord.JA.getValue()));
        kieSession.insert(new Node(31,Antwoord.JA.getValue()));
        kieSession.insert(new Node(32,Antwoord.NEE.getValue()));
        kieSession.insert(new Node(33,Antwoord.NEE.getValue()));
        kieSession.insert(new Node(34,Antwoord.NEE.getValue()));
        kieSession.insert(new Node(35,Antwoord.NEE.getValue()));

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

        Assert.assertThat(35, Matchers.equalTo(nodes.size()));
        Assert.assertThat(1, Matchers.isIn(ids));
        for(Node n: nodes){
            if(n.getId() == 1){
                Assert.assertThat(Antwoord.JA.getValue(), Matchers.equalTo(n.getAntwoord()));
            }
        }
    }

    @Test
    public void testVergunningvrijRuleNeeAntwoord() {

        kieSession = getKieSession("dakkapelExVergunningvrijSession");

        kieSession.insert(new Node(12,Antwoord.JA.getValue()));
        kieSession.insert(new Node(13,Antwoord.JA.getValue()));
        kieSession.insert(new Node(14,Antwoord.NEE.getValue()));
        kieSession.insert(new Node(15,Antwoord.JA.getValue()));
        kieSession.insert(new Node(16,Antwoord.JA.getValue()));
        kieSession.insert(new Node(17,Antwoord.JA.getValue()));
        kieSession.insert(new Node(18,Antwoord.JA.getValue()));
        kieSession.insert(new Node(19,Antwoord.JA.getValue()));
        kieSession.insert(new Node(20,Antwoord.JA.getValue()));
        kieSession.insert(new Node(21,Antwoord.JA.getValue()));
        kieSession.insert(new Node(22,Antwoord.JA.getValue()));
        kieSession.insert(new Node(23,Antwoord.JA.getValue()));
        kieSession.insert(new Node(24,Antwoord.JA.getValue()));
        kieSession.insert(new Node(25,Antwoord.JA.getValue()));
        kieSession.insert(new Node(26,Antwoord.JA.getValue()));
        kieSession.insert(new Node(27,Antwoord.JA.getValue()));
        kieSession.insert(new Node(28,Antwoord.JA.getValue()));
        kieSession.insert(new Node(29,Antwoord.JA.getValue()));
        kieSession.insert(new Node(30,Antwoord.JA.getValue()));
        kieSession.insert(new Node(31,Antwoord.JA.getValue()));
        kieSession.insert(new Node(32,Antwoord.NEE.getValue()));
        kieSession.insert(new Node(33,Antwoord.NEE.getValue()));
        kieSession.insert(new Node(34,Antwoord.NEE.getValue()));
        kieSession.insert(new Node(35,Antwoord.JA.getValue()));

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

        Assert.assertThat(35, Matchers.equalTo(nodes.size()));
        Assert.assertThat(1, Matchers.isIn(ids));
        for(Node n: nodes){
            if(n.getId() == 1){
                Assert.assertThat(Antwoord.NEE.getValue(), Matchers.equalTo(n.getAntwoord()));
            }
        }    }

    @After
    public void tearDown() {
        if (kieSession != null) {
            kieSession.dispose();
        }
    }

}
