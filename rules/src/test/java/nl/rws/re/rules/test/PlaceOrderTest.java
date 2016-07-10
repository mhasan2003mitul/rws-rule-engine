package nl.rws.re.rules.test;

import nl.rws.re.facts.OrderAccept;
import nl.rws.re.facts.OrderReject;
import nl.rws.re.facts.PlaceOrder;
import org.apache.log4j.Logger;
import org.drools.core.event.DebugProcessEventListener;
import org.drools.core.event.DebugRuleRuntimeEventListener;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.io.ResourceType;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.io.ResourceFactory;
import org.kie.internal.runtime.StatefulKnowledgeSession;

/**
 * Created by Administrator on 08-Jul-16.
 */
public class PlaceOrderTest {
    private static Logger LOGGER = Logger.getLogger(PlaceOrderTest.class);

    private KnowledgeBase knowledgeBase;
    private StatefulKnowledgeSession statefulKnowledgeSession;

    @Before
    public void setUp() {
        try {
            knowledgeBase = populateKnowledgeBase();

            statefulKnowledgeSession = knowledgeBase.newStatefulKnowledgeSession();

//            statefulKnowledgeSession.addEventListener(new DebugAgendaEventListener());
//            statefulKnowledgeSession.addEventListener(new DebugProcessEventListener());
//            statefulKnowledgeSession.addEventListener(new DebugRuleRuntimeEventListener());

        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    private KnowledgeBase populateKnowledgeBase() {
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
        builder.add(ResourceFactory.newClassPathResource("orderApprovalRules.drl"), ResourceType.DRL);

        KnowledgeBase knowledgeBase = KnowledgeBaseFactory.newKnowledgeBase();

        knowledgeBase.addKnowledgePackages(builder.getKnowledgePackages());

        return knowledgeBase;
    }

    @After
    public void tearDown() {
        if (statefulKnowledgeSession != null) {
            statefulKnowledgeSession.dispose();
        }
    }

    @Test
    public void testOrderAcceptCompanyA() {
        statefulKnowledgeSession.insert(new PlaceOrder("Company A", 100, 100));
        Assert.assertTrue(statefulKnowledgeSession.fireAllRules() == 1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().size() == 1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().toArray()[0] instanceof OrderAccept);
        Assert.assertTrue(((OrderAccept)statefulKnowledgeSession.getObjects().toArray()[0]).getMessage().equals("Accepted order for: 100 stocks of Company A at $100.0"));
    }

    @Test
    public void testOrderAcceptCompanyB() {
        statefulKnowledgeSession.insert(new PlaceOrder("Company B", 100, 200));
        Assert.assertTrue(statefulKnowledgeSession.fireAllRules() == 1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().size() == 1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().toArray()[0] instanceof OrderAccept);
        Assert.assertTrue(((OrderAccept)statefulKnowledgeSession.getObjects().toArray()[0]).getMessage().equals("Accepted order for: 100 stocks of Company B at $200.0"));
    }

    @Test
    public void testOrderAcceptCompanyC() {
        statefulKnowledgeSession.insert(new PlaceOrder("Company C", 100, 200));
        Assert.assertTrue(statefulKnowledgeSession.fireAllRules() == 1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().size() == 1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().toArray()[0] instanceof OrderAccept);
        Assert.assertTrue(((OrderAccept)statefulKnowledgeSession.getObjects().toArray()[0]).getMessage().equals("Accepted order for: 100 stocks of Company C at $200.0"));
    }

    @Test
    public void testOrderRejectCompanyA() {
        statefulKnowledgeSession.insert(new PlaceOrder("Company A", 5, 100));
        Assert.assertTrue(statefulKnowledgeSession.fireAllRules()==1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().size() == 1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().toArray()[0] instanceof OrderReject);
        Assert.assertTrue(((OrderReject)statefulKnowledgeSession.getObjects().toArray()[0]).getReason().equals("An Order for stocks of Company A is accepted only if the number of stocks is higher than 10."));
    }

    @Test
    public void testOrderRejectCompanyB() {
        statefulKnowledgeSession.insert(new PlaceOrder("Company B", 5, 10));
        Assert.assertTrue(statefulKnowledgeSession.fireAllRules() == 1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().size() == 1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().toArray()[0] instanceof OrderReject);
        Assert.assertTrue(((OrderReject)statefulKnowledgeSession.getObjects().toArray()[0]).getReason().equals("An Order for stocks of Company B is accepted only if the stock price is higher than 100 $."));
    }

    @Test
    public void testOrderRejectCompanyC() {
        statefulKnowledgeSession.insert(new PlaceOrder("Company C", 100, 10));
        Assert.assertTrue(statefulKnowledgeSession.fireAllRules()==1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().size() == 1);
        Assert.assertTrue(statefulKnowledgeSession.getObjects().toArray()[0] instanceof OrderReject);
        Assert.assertTrue(((OrderReject)statefulKnowledgeSession.getObjects().toArray()[0]).getReason().equals("An Order for stocks of Company C is accepted only if the stock price is higher than 50 $ and the number of stocks is lower than 200."));
    }
}
