package com.redhat.examples.quarkus.model;

public class KitchenOrder {

    String orderId;

    String name;

    MenuItem menuItem;


    public KitchenOrder() {
    }

    public KitchenOrder(String orderId, String name, MenuItem menuItem) {
        this.orderId = orderId;
        this.name = name;
        this.menuItem = menuItem;
    }

    @Override
    public String toString() {
        return new StringBuilder().append("KitchenOrder[orderId=")
                .append(orderId)
                .append(",name=")
                .append(name)
                .append(",menuItem=")
                .append(menuItem)
                .append("]").toString();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}
