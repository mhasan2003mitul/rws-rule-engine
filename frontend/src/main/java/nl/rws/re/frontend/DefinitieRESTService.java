package nl.rws.re.frontend;

import com.google.gson.Gson;
import nl.rws.re.backend.*;
import nl.rws.re.backend.Grondslag;
import nl.rws.re.backend.Vraag;
import nl.rws.re.facts.dakkapelx.*;
import nl.rws.re.facts.dakkapelx.RuleResultaat;
import nl.rws.re.facts.dakkapelx.Error;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 07-Jul-16.
 */
@Path("definitieRule")
public class DefinitieRESTService {

    private static Logger LOGGER = Logger.getLogger(DefinitieRESTService.class);

    private Vraag vraag1;
    private Vraag vraag2;

    private Grondslag grondslag1;
    private Grondslag grondslag2;

    @POST
    @Path("grondslagsVragen")
    @Produces("application/json")
    @Consumes("application/json")
    public String getGrondslagsVragen(List<nl.rws.re.facts.dakkapelx.Grondslag> grondslags) {

        ObjectFactory objectFactory = new ObjectFactory();

        DefinitieRuleRequest definitieRuleRequest = objectFactory.createDefinitieRuleRequest();

        for (nl.rws.re.facts.dakkapelx.Grondslag grondslag : grondslags) {
            Grondslag tempGrondslag = objectFactory.createGrondslag();
            tempGrondslag.setGrondslagBeschrijving(objectFactory.createGrondslagGrondslagBeschrijving(grondslag.getGrondslagBeschrijving()));
            definitieRuleRequest.getGrondslag().add(tempGrondslag);
        }

        DefinitieService definitieService = new DefinitieService();
        DefinitieRuleResponse definitieRuleResponse = definitieService.getDefinitieServiceHttpSoap11Endpoint().definitieRuleService(definitieRuleRequest);

        List<nl.rws.re.facts.dakkapelx.Vraag> vraags = new ArrayList<>();
        for (Vraag vraag : definitieRuleResponse.getVraag()) {
            vraags.add(new VraagBuilder().metVraagId(vraag.getVraagId().getValue().toString()).build());
        }
        String vraagJSON = new Gson().toJson(vraags);

        return vraagJSON;
    }

    @POST
    @Path("resultaat")
    @Consumes("application/json")
    @Produces("application/json")
    public String getResultaat(List<nl.rws.re.facts.dakkapelx.Vraag> vragen) {

        ObjectFactory objectFactory = new ObjectFactory();

        DefinitieRuleRequest definitieRuleRequest = objectFactory.createDefinitieRuleRequest();

        for (nl.rws.re.facts.dakkapelx.Vraag vraag : vragen) {
            Vraag tempVraag = objectFactory.createVraag();
            tempVraag.setVraagId(objectFactory.createVraagVraagId(vraag.getVraagId()));
            tempVraag.setAntwoord(objectFactory.createVraagAntwoord(vraag.getAntwoord()));
            definitieRuleRequest.getVraag().add(tempVraag);
        }

        DefinitieService definitieService = new DefinitieService();
        DefinitieRuleResponse definitieRuleResponse = definitieService.getDefinitieServiceHttpSoap11Endpoint().definitieRuleService(definitieRuleRequest);

        String jsonOutput;

        Gson gson = new Gson();

        if (definitieRuleResponse.getRuleResultaat().size() > 0) {
            List<RuleResultaat> results = new ArrayList<>();
            for (nl.rws.re.backend.RuleResultaat result : definitieRuleResponse.getRuleResultaat()) {
                results.add(new RuleResultaat(result.getResultaatVoor().getValue(),result.getResultaat().getValue()));
            }
            jsonOutput = gson.toJson(results);
        } else {
            jsonOutput = gson.toJson(new Error("Wrong Output"));
        }

//        return gson.toJson(vragen);
        return jsonOutput;
    }
}
