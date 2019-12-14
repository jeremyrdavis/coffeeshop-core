package com.redhat.examples.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "coffee_order")
public class Order extends PanacheEntity {

    public String orderNumber;

    public String name;

    @Enumerated(EnumType.STRING)
    public Beverage beverage;

    @OneToMany
    private List<BeverageOrder> beverageOrder;

    @OneToMany
    private List<KitchenOrder> kitchenOrder;

    public OrderStatus status;

    public void addBeverage(Beverage beverage) {
        this.beverageOrder.add(new BeverageOrder(this, beverage));
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

    public Optional<List<KitchenOrder>> getKitchenOrder() {
        return Optional.of(kitchenOrder);
    }

    public Optional<List<BeverageOrder>> getBeverageOrder() {
        return Optional.of(beverageOrder);
    }


    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    public void setBeverageOrder(List<BeverageOrder> beverageOrder) {
        this.beverageOrder = beverageOrder;
    }

    public void setKitchenOrder(List<KitchenOrder> kitchenOrder) {
        this.kitchenOrder = kitchenOrder;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
