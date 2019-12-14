package com.redhat.examples.quarkus.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.json.bind.annotation.JsonbProperty;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "coffee_order")
public class Order extends PanacheEntity implements CoffeeShopOrder{

    public final String orderNumber;

    public String name;

    @Enumerated(EnumType.STRING)
    public Beverage beverage;

    @OneToMany(cascade = CascadeType.ALL) @JsonbProperty("beverages")
    private List<BeverageOrder> beverageOrder;

    @OneToMany(cascade = CascadeType.ALL) @JsonbProperty("menuItems")
    private List<KitchenOrder> kitchenOrder;

    public OrderStatus status;

    public Order() {
        this.orderNumber = UUID.randomUUID().toString();
    }

    public Order(String name){
        this.orderNumber = UUID.randomUUID().toString();
        this.name = name;
    }

    public void addBeverage(Beverage beverage) {
        if (this.beverageOrder == null) {
            this.beverageOrder = new ArrayList<>();
        }
        this.beverageOrder.add(new BeverageOrder(this, beverage));
    }

    public void addMenuItem(MenuItem menuItem) {
        if (this.kitchenOrder == null) {
            this.kitchenOrder = new ArrayList<>();
        }
        this.kitchenOrder.add(new KitchenOrder(this, menuItem));
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
        return Optional.ofNullable(kitchenOrder);
    }

    public Optional<List<BeverageOrder>> getBeverageOrder() {
        return Optional.ofNullable(beverageOrder);
    }


    public String getOrderNumber() {
        return orderNumber;
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
