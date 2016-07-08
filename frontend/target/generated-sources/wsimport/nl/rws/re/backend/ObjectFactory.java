
package nl.rws.re.backend;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the nl.rws.re.backend package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _OrderAcceptMessage_QNAME = new QName("http://com.test/placeorder", "message");
    private final static QName _PlaceOrder2Symbol_QNAME = new QName("http://com.test/placeorder", "symbol");
    private final static QName _OrderRejectReason_QNAME = new QName("http://com.test/placeorder", "reason");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: nl.rws.re.backend
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PlaceOrder }
     * 
     */
    public PlaceOrder createPlaceOrder() {
        return new PlaceOrder();
    }

    /**
     * Create an instance of {@link PlaceOrder2 }
     * 
     */
    public PlaceOrder2 createPlaceOrder2() {
        return new PlaceOrder2();
    }

    /**
     * Create an instance of {@link PlaceOrderRespone }
     * 
     */
    public PlaceOrderRespone createPlaceOrderRespone() {
        return new PlaceOrderRespone();
    }

    /**
     * Create an instance of {@link OrderAccept }
     * 
     */
    public OrderAccept createOrderAccept() {
        return new OrderAccept();
    }

    /**
     * Create an instance of {@link OrderReject }
     * 
     */
    public OrderReject createOrderReject() {
        return new OrderReject();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.test/placeorder", name = "message", scope = OrderAccept.class)
    public JAXBElement<String> createOrderAcceptMessage(String value) {
        return new JAXBElement<String>(_OrderAcceptMessage_QNAME, String.class, OrderAccept.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.test/placeorder", name = "symbol", scope = PlaceOrder2 .class)
    public JAXBElement<String> createPlaceOrder2Symbol(String value) {
        return new JAXBElement<String>(_PlaceOrder2Symbol_QNAME, String.class, PlaceOrder2 .class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://com.test/placeorder", name = "reason", scope = OrderReject.class)
    public JAXBElement<String> createOrderRejectReason(String value) {
        return new JAXBElement<String>(_OrderRejectReason_QNAME, String.class, OrderReject.class, value);
    }

}
