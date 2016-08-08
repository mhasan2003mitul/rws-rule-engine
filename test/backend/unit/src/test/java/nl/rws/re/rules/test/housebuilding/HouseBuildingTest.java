package nl.rws.re.rules.test.housebuilding;

import nl.rws.re.facts.details.Address;
import nl.rws.re.facts.details.ApplicantDetail;
import nl.rws.re.facts.details.Information;
import nl.rws.re.facts.housebuilding.HouseBuildingService;
import nl.rws.re.facts.housebuilding.ServiceReply;
import nl.rws.re.facts.housebuilding.TypeOfLand;
import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.io.Resource;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * Created by Md. Mainul Hasan Patwary on 20-Jul-16.
 * email: mhasan_mitul@yahoo.com
 * skype: mhasan_mitul
 */

public class HouseBuildingTest {
    private final static String KIE_SESSION_NAME = "houseBuildingSession";
    private KieSession kieSession;


    @Before
    public void setUp() {

        KieServices kieServices = KieServices.Factory.get();

        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        kieSession = kieContainer.newKieSession(KIE_SESSION_NAME);

        kieSession.addEventListener(new DebugAgendaEventListener());
    }

    @After
    public void tearDown(){
        kieSession.dispose();
    }

    @Test
    public void testHouseBuildingRequestRequiredForHouseBuildingService() {
        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(1, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("House building service request should be provided."));
    }

    @Test
    public void testApplicantAddressRequiredForHouseBuildingRequest() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();
        Address address = new Address("Abc","60","3456HT","ABC");
        houseBuildingService.setNewHouseBuildingLocationAddress(address);

        kieSession.insert(houseBuildingService);

        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(2, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[1] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[1]).getMessage().equals("Applicant address[street name, house number, post code, city] should be provided."));
    }

    @Test
    public void testApplicantNewHouseBuildingLocationAddressRequiredForHouseBuildingRequest() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();
        Address address = new Address("Abc","60","3456HT","ABC");
        houseBuildingService.setApplicantCurrentAddress(address);

        kieSession.insert(houseBuildingService);

        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(2, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[1] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[1]).getMessage().equals("Applicant new house building location address[street name, house number, post code, city] should be provided."));
    }

    @Test
    public void testApplicantNewHouseBuildingLocationAddressFiledPartiallyNull() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address(null,"60","3456HT",null));
        houseBuildingService.setApplicantCurrentAddress(new Address("Abc","60","3456HT","ABC"));

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(2, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[1] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[1]).getMessage().equals("Stree Name, House Number, Post Code and City should be provided in address."));
    }

    @Test
    public void testApplicantAddressFiledPartiallyNull() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();

        houseBuildingService.setApplicantCurrentAddress(new Address(null,"60","3456HT",null));
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address("Abc","60","3456HT","ABC"));

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(2, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[1] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[1]).getMessage().equals("Stree Name, House Number, Post Code and City should be provided in address."));
    }

    @Test
    public void testTypeOfLand() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();

        houseBuildingService.setApplicantCurrentAddress(new Address("Abc","60","3456HT","Abc"));
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address("Abc","60","3456HT","ABC"));

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(2, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("What is the type of the land?"));
    }

    @Test
    public void testDeepOfLowLand() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();

        houseBuildingService.setApplicantCurrentAddress(new Address("Abc","60","3456HT","Abc"));
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address("Abc","60","3456HT","ABC"));
        houseBuildingService.setTypeOfTheLand("LOW");

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(3, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("How deep is the land?"));
    }

    @Test
    public void testAltitudeOfHighLand() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();

        houseBuildingService.setApplicantCurrentAddress(new Address("Abc","60","3456HT","Abc"));
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address("Abc","60","3456HT","ABC"));
        houseBuildingService.setTypeOfTheLand("HIGH");

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(3, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("What is the altitude of the high land?"));
    }

    @Test
    public void testSizeOfLowLand() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();

        houseBuildingService.setApplicantCurrentAddress(new Address("Abc","60","3456HT","Abc"));
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address("Abc","60","3456HT","ABC"));
        houseBuildingService.setTypeOfTheLand("LOW");
        houseBuildingService.setDeep(50);

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(3, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("How big is the land?"));
    }

    @Test
    public void testSizeOfHighLand() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();

        houseBuildingService.setApplicantCurrentAddress(new Address("Abc","60","3456HT","Abc"));
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address("Abc","60","3456HT","ABC"));
        houseBuildingService.setTypeOfTheLand("HIGH");
        houseBuildingService.setAltitude(50);

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(3, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("What is the size of the high land?"));
    }

    @Test
    public void testAcceptRuleToBuildHouseInLowLand() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();

        houseBuildingService.setApplicantCurrentAddress(new Address("Abc","60","3456HT","Abc"));
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address("Abc","60","3456HT","ABC"));
        houseBuildingService.setTypeOfTheLand("LOW");
        houseBuildingService.setDeep(40);
        houseBuildingService.setSize(40);

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(3, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("Allowed to build a house."));
    }

    @Test
    public void testAcceptRuleToBuildHouseInHighLand() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();

        houseBuildingService.setApplicantCurrentAddress(new Address("Abc","60","3456HT","Abc"));
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address("Abc","60","3456HT","ABC"));
        houseBuildingService.setTypeOfTheLand("HIGH");
        houseBuildingService.setAltitude(40);
        houseBuildingService.setSize(40);

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(3, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("Allowed to build a house."));
    }

    @Test
    public void testRejectRuleToBuildHouseInLowLand() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();

        houseBuildingService.setApplicantCurrentAddress(new Address("Abc","60","3456HT","Abc"));
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address("Abc","60","3456HT","ABC"));
        houseBuildingService.setTypeOfTheLand(TypeOfLand.LOW.name());
        houseBuildingService.setDeep(40);
        houseBuildingService.setSize(100);

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(3, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("Not allowed to build a house."));
    }

    @Test
    public void testRejectRuleToBuildHouseInHighLand() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();

        houseBuildingService.setApplicantCurrentAddress(new Address("Abc","60","3456HT","Abc"));
        houseBuildingService.setNewHouseBuildingLocationAddress(new Address("Abc","60","3456HT","ABC"));
        houseBuildingService.setTypeOfTheLand(TypeOfLand.HIGH.name());
        houseBuildingService.setAltitude(40);
        houseBuildingService.setSize(100);

        kieSession.insert(houseBuildingService);


        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(3, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("Not allowed to build a house."));
    }
}
