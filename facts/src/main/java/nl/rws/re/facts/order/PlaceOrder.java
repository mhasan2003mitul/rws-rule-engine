/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.rws.re.facts.order;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * @author Mainul
 */
public class PlaceOrder {

    public String symbol;
    public int quantity;
    public double price;

    public PlaceOrder() {

    }

    public PlaceOrder(String symbol, int quantity, double price) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
    }

    public String getSymbol() {

        return symbol;
    }

    public void setSymbol(String symbol) {

        this.symbol = symbol;
    }

    public int getQuantity() {

        return quantity;
    }

    public void setQuantity(int quantity) {

        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {

        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        return new EqualsBuilder().append(this,obj).isEquals();
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}