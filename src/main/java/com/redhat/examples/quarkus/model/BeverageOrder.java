package com.redhat.examples.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="beverage_order")
public class BeverageOrder extends PanacheEntity implements CoffeeShopOrder{

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonbTransient
    private Order order;

    public Beverage beverage;

    public BeverageOrder() {
    }

    public BeverageOrder(Order order, Beverage beverage){
        this.order = order;
        this.beverage = beverage;
    }

}
