package com.redhat.examples.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="beverage_order")
public class BeverageOrder extends PanacheEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    Beverage beverage;


    public BeverageOrder() {
    }

    public BeverageOrder(Order order, Beverage beverage) {

        this.order = order;
        this.beverage = beverage;
    }
}
