package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;

import javax.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class CoffeeShop {

    public Order acceptOrder(Order order) {
        order.setOrderNumber(UUID.randomUUID().toString());
        return order;
    }

    public Order updateOrder(long id, OrderStatus status) {

        Order order = Order.findById(id);
        order.setStatus(status);
        order.persist();
        return order;
    }
}
