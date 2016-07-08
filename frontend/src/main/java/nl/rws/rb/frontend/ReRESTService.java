package nl.rws.rb.frontend;

import nl.rws.re.backend.*;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Holder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 07-Jul-16.
 */
@Path("")
public class ReRESTService {

    private static Logger LOGGER = Logger.getLogger(ReRESTService.class);

    private OrderApprovalService orderApprovalService;

    @PostConstruct
    private void init() {
        orderApprovalService = new OrderApprovalService();

        LOGGER.info(orderApprovalService.getOrderApprovalServiceHttpSoap11Endpoint());
    }

    @GET
    public String result() {
        PlaceOrder2 p1 = new PlaceOrder2();
        p1.setPrice(100.00);
        p1.setQuantity(100);
        List<PlaceOrder2> list = new ArrayList<PlaceOrder2>();
        list.add(p1);
        Holder<List<OrderAccept>> a = new Holder<List<OrderAccept>>();
        a.value = new ArrayList<OrderAccept>();
        Holder<List<OrderReject>> b = new Holder<List<OrderReject>>();
        b.value = new ArrayList<OrderReject>();
        orderApprovalService.getOrderApprovalServiceHttpSoap11Endpoint().placeOrder(list,a,b);

        return a.value.size()+"";
    }
}
