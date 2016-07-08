
package nl.rws.re.backend;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderAccept" type="{http://com.test/placeorder}OrderAccept" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="orderReject" type="{http://com.test/placeorder}OrderReject" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "orderAccept",
    "orderReject"
})
@XmlRootElement(name = "placeOrderRespone")
public class PlaceOrderRespone {

    protected List<OrderAccept> orderAccept;
    protected List<OrderReject> orderReject;

    /**
     * Gets the value of the orderAccept property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderAccept property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderAccept().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderAccept }
     * 
     * 
     */
    public List<OrderAccept> getOrderAccept() {
        if (orderAccept == null) {
            orderAccept = new ArrayList<OrderAccept>();
        }
        return this.orderAccept;
    }

    /**
     * Gets the value of the orderReject property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderReject property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderReject().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrderReject }
     * 
     * 
     */
    public List<OrderReject> getOrderReject() {
        if (orderReject == null) {
            orderReject = new ArrayList<OrderReject>();
        }
        return this.orderReject;
    }

}
