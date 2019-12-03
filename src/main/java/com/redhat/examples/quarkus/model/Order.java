package com.redhat.examples.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Optional;

@Entity
@Table(name = "coffee_order")
public class Order extends PanacheEntity {

    public String orderNumber;

    public String name;

    @Enumerated(EnumType.STRING)
    public Beverages beverage;

    public OrderStatus status;

    @Enumerated(EnumType.STRING)
    public MenuItem menuItem;

    public Order() {
    }

    public Order(String orderNumber, String name, Beverages beverage, OrderStatus status) {
        this.orderNumber = orderNumber;
        this.name = name;
        this.beverage = beverage;
        this.status = status;
    }

    public Optional<Beverages> getBeverage() {
        return Optional.of(this.beverage);
    }

    public Optional<KitchenOrder> getKitchenOrder() {
        if (this.menuItem != null) {
            return Optional.of(new KitchenOrder(this.orderNumber, this.name, this.menuItem));
        }else{
            return Optional.empty();
        }
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
