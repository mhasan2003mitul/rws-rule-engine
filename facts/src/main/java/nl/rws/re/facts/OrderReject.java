/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.rws.re.facts;

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

}
