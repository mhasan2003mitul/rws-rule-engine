package nl.rws.re.rules.test;

import nl.rws.re.facts.order.OrderAccept;
import nl.rws.re.facts.order.OrderReject;
import nl.rws.re.facts.order.PlaceOrder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 08-Jul-16.
 */
public class PlaceOrderTest {
    private static Logger LOGGER = LoggerFactory.getLogger(PlaceOrderTest.class);

    private KieSession kieSession;

    @Before
    public void setUp() {
        try {
            KieServices kieServices = KieServices.Factory.get();
            KieContainer kieContainer = kieServices.getKieClasspathContainer();

            kieSession = kieContainer.newKieSession("session");

//            kieSession.addEventListener(new DebugAgendaEventListener());
//            kieSession.addEventListener(new DebugProcessEventListener());
//            kieSession.addEventListener(new DebugRuleRuntimeEventListener());


        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
        }
    }

    @After
    public void tearDown() {
        if (kieSession != null) {
            kieSession.dispose();
        }
    }

    @Test
    public void testOrderVerify() {
        kieSession.insert(new PlaceOrder());
        Assert.assertTrue(kieSession.fireAllRules() == 1);
        Assert.assertTrue(kieSession.getObjects().size() == 1);
    }

    @Test
    public void testOrderAcceptCompanyA() {
        kieSession.insert(new PlaceOrder("Company A", 100, 100));
        Assert.assertTrue(kieSession.fireAllRules() == 2);
        Assert.assertTrue(kieSession.getObjects().size() == 1);
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof OrderAccept);
        Assert.assertTrue(((OrderAccept)kieSession.getObjects().toArray()[0]).getMessage().equals("Accepted order for: 100 stocks of Company A at $100.0"));
    }

    @Test
    public void testOrderAcceptCompanyB() {
        kieSession.insert(new PlaceOrder("Company B", 100, 200));
        Assert.assertTrue(kieSession.fireAllRules() == 2);
        Assert.assertTrue(kieSession.getObjects().size() == 1);
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof OrderAccept);
        Assert.assertTrue(((OrderAccept)kieSession.getObjects().toArray()[0]).getMessage().equals("Accepted order for: 100 stocks of Company B at $200.0"));
    }

    @Test
    public void testOrderAcceptCompanyC() {
        kieSession.insert(new PlaceOrder("Company C", 100, 200));
        Assert.assertTrue(kieSession.fireAllRules() == 2);
        Assert.assertTrue(kieSession.getObjects().size() == 1);
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof OrderAccept);
        Assert.assertTrue(((OrderAccept)kieSession.getObjects().toArray()[0]).getMessage().equals("Accepted order for: 100 stocks of Company C at $200.0"));
    }

    @Test
    public void testOrderRejectCompanyA() {
        kieSession.insert(new PlaceOrder("Company A", 5, 100));
        Assert.assertTrue(kieSession.fireAllRules()==2);
        Assert.assertTrue(kieSession.getObjects().size() == 1);
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof OrderReject);
        Assert.assertTrue(((OrderReject)kieSession.getObjects().toArray()[0]).getReason().equals("An Order for stocks of Company A is accepted only if the number of stocks is higher than 10."));
    }

    @Test
    public void testOrderRejectCompanyB() {
        kieSession.insert(new PlaceOrder("Company B", 5, 10));
        Assert.assertTrue(kieSession.fireAllRules() == 2);
        Assert.assertTrue(kieSession.getObjects().size() == 1);
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof OrderReject);
        Assert.assertTrue(((OrderReject)kieSession.getObjects().toArray()[0]).getReason().equals("An Order for stocks of Company B is accepted only if the stock price is higher than 100 $."));
    }

    @Test
    public void testOrderRejectCompanyC() {
        kieSession.insert(new PlaceOrder("Company C", 100, 10));
        Assert.assertTrue(kieSession.fireAllRules()==2);
        Assert.assertTrue(kieSession.getObjects().size() == 1);
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof OrderReject);
        Assert.assertTrue(((OrderReject)kieSession.getObjects().toArray()[0]).getReason().equals("An Order for stocks of Company C is accepted only if the stock price is higher than 50 $ and the number of stocks is lower than 200."));
    }
}
