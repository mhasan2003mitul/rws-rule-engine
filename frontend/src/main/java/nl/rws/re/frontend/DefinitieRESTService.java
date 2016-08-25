package nl.rws.re.frontend;

import com.google.gson.Gson;
import nl.rws.re.backend.*;
import nl.rws.re.backend.Node;
import nl.rws.re.backend.Vraag;
import nl.rws.re.facts.Error;
import nl.rws.re.facts.VraagBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 07-Jul-16.
 */
@Path("definitieRule")
public class DefinitieRESTService {

    private static Logger LOGGER = LoggerFactory.getLogger(DefinitieRESTService.class);

    @POST
    @Path("grondslagsVragen")
    @Produces("application/json")
    public String getGrondslagsVragen() {

        ObjectFactory objectFactory = new ObjectFactory();

        DefinitieRuleRequest definitieRuleRequest = objectFactory.createDefinitieRuleRequest();

        DefinitieService definitieService = new DefinitieService();
        DefinitieRuleResponse definitieRuleResponse = definitieService.getDefinitieServiceHttpSoap11Endpoint().definitieRuleService(definitieRuleRequest);

        List<nl.rws.re.facts.Vraag> vraags = new ArrayList<>();
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
    public String getResultaat(List<nl.rws.re.facts.Node> nodes) {

        ObjectFactory objectFactory = new ObjectFactory();

        DefinitieRuleRequest definitieRuleRequest = objectFactory.createDefinitieRuleRequest();

        for (nl.rws.re.facts.Node node : nodes) {
            Node tempNode = objectFactory.createNode();
            tempNode.setId(node.getId());
            tempNode.setAntwoord(objectFactory.createVraagAntwoord(node.getAntwoord()));
            definitieRuleRequest.getNode().add(tempNode);
        }

        DefinitieService definitieService = new DefinitieService();
        DefinitieRuleResponse definitieRuleResponse = definitieService.getDefinitieServiceHttpSoap11Endpoint().definitieRuleService(definitieRuleRequest);

        String jsonOutput;

        Gson gson = new Gson();

        if (definitieRuleResponse.getNode().size() > 0) {
            List<nl.rws.re.facts.Node> results = new ArrayList<>();
            for (Node result : definitieRuleResponse.getNode()) {
                results.add(new nl.rws.re.facts.Node(result.getId(), result.getAntwoord().getValue()));
            }
            jsonOutput = gson.toJson(results);
        } else {
            jsonOutput = gson.toJson(new Error("Wrong Output"));
        }


        return jsonOutput;
    }
}
