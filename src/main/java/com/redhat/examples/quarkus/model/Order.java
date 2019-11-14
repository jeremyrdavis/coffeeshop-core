package com.redhat.examples.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="coffee_order")
public class Order extends PanacheEntity {

    String orderNumber;

    String name;

    OrderStatus status;

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
