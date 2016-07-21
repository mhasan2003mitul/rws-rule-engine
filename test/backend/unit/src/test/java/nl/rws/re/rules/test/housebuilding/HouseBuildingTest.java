package nl.rws.re.rules.test.housebuilding;

import nl.rws.re.facts.details.Address;
import nl.rws.re.facts.details.ApplicantDetail;
import nl.rws.re.facts.details.Information;
import nl.rws.re.facts.housebuilding.HouseBuildingService;
import nl.rws.re.facts.housebuilding.ServiceReply;
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
    private final static String KIE_SESSION_NAME = "huisbouwenSession";
    private KieSession kieSession;


    @Before
    public void setUp() {

        KieServices kieServices = KieServices.Factory.get();

        KieContainer kieContainer = kieServices.getKieClasspathContainer();

        kieSession = kieContainer.newKieSession(KIE_SESSION_NAME);

//        kieSession.addEventListener(new DebugAgendaEventListener());
    }

    @After
    public void teadDown(){
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
    public void testApplicantDetailRequiredForHouseBuildingRequest() {
        kieSession.insert(new HouseBuildingService());
        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(2, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("Applicant details should be provided in house building service request."));
    }

    @Test
    public void testInformationRequiredForApplicantDetail() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();
        houseBuildingService.setApplicantDetail(new ApplicantDetail());
        kieSession.insert(houseBuildingService);
        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(2, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("Applicant information should be provided in applicant detail."));
    }

    @Test
    public void testAddressRequiredForInformation() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();
        ApplicantDetail applicantDetail = new ApplicantDetail();
        applicantDetail.setInformation(new Information());
        houseBuildingService.setApplicantDetail(applicantDetail);
        kieSession.insert(houseBuildingService);
        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(2, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("Applicant address should be provided in application information."));
    }

    @Test
    public void testStree_HouseNumber_Postcode_CityRequiredForAddress() {
        HouseBuildingService houseBuildingService = new HouseBuildingService();
        ApplicantDetail applicantDetail = new ApplicantDetail();
        Information information = new Information();
        information.setAddress(new Address());
        applicantDetail.setInformation(information);
        houseBuildingService.setApplicantDetail(applicantDetail);
        kieSession.insert(houseBuildingService);
        int totalRulesExecuted = kieSession.fireAllRules();
        Assert.assertThat(2, Matchers.equalTo(totalRulesExecuted));
        Assert.assertTrue(kieSession.getObjects().toArray()[0] instanceof ServiceReply);
        Assert.assertTrue(((ServiceReply)kieSession.getObjects().toArray()[0]).getMessage().equals("Stree Name, House Number, Post Code and City should be provided in address."));
    }
}
