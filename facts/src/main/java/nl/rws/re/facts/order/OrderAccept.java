/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.rws.re.facts.order;


import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Mainul
 */
public class OrderAccept extends OrderStatus {

    private String message;

    public OrderAccept() {

    }

    public OrderAccept(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OrderAccept)) return false;

        OrderAccept that = (OrderAccept) o;

        return new EqualsBuilder()
                .append(getMessage(), that.getMessage())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getMessage())
                .toHashCode();
    }
}
