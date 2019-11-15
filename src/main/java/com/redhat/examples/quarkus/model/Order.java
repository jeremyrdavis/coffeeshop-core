package com.redhat.examples.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="coffee_order")
public class Order extends PanacheEntity {

    public String orderNumber;

    public String name;

    public String beverage;

    public OrderStatus status;

    public Order() {
    }

    public Order(String orderNumber, String name, String beverage, OrderStatus status) {
        this.orderNumber = orderNumber;
        this.name = name;
        this.beverage = beverage;
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder.append("Order[")
                .append("id=")
                .append(id)
                .append(",orderNumber=")
                .append(orderNumber)
                .append(",beverage=")
                .append(beverage)
                .append(",name=")
                .append(name)
                .append(",status=")
                .append(status)
                .append("]").toString();
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String idToSet) {
        orderNumber = idToSet;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
