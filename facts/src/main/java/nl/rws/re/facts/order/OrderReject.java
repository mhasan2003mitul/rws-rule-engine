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
public class OrderReject extends OrderStatus {

    private String reason;

    public OrderReject() {

    }

    public OrderReject(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof OrderReject)) return false;

        OrderReject that = (OrderReject) o;

        return new EqualsBuilder()
                .append(getReason(), that.getReason())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getReason())
                .toHashCode();
    }
}
