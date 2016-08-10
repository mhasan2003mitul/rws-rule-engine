package nl.rws.re.frontend;

import org.apache.log4j.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


/**
 * Created by Administrator on 07-Jul-16.
 */
@Path("")
public class DakkapelService {

    private static Logger LOGGER = Logger.getLogger(DakkapelService.class);

    @GET
    public String result() {
        return "Hello ";
    }
}
