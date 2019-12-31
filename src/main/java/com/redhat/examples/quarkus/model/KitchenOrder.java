package com.redhat.examples.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbProperty;
import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbVisibility;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "kitchen_order")
public class KitchenOrder extends PanacheEntity implements CoffeeShopOrder{

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonbTransient
    private Order order;

    MenuItem menuItem;

    public KitchenOrder() {
    }

    public KitchenOrder(Order order, MenuItem menuItem) {
        this.order = order;
        this.menuItem = menuItem;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("KitchenOrder[order=")
                .append(order.id)
                .append(",name=")
                .append(order.name)
                .append(",menuItem=")
                .append(menuItem)
                .append("]").toString();
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}