package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.infrastructure.BaristaResource;
import com.redhat.examples.quarkus.infrastructure.KitchenService;
import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.UUID;

@ApplicationScoped
public class CoffeeShop {

    @Inject
    BaristaResource baristaResource;

    @Inject
    KitchenService kitchenService;

    @Transactional
    public Order orderIn(Order order) {

        order.setOrderNumber(UUID.randomUUID().toString());
        order.setStatus(OrderStatus.ACCEPTED);
        order.persist();

        order.getKitchenOrder().ifPresent(ko -> kitchenService.orderIn(ko));

        return order;
    }

    @Transactional
    public Order updateOrder(long id, OrderStatus status) {
        Order order = Order.findById(id);
        order.setStatus(status);
        order.persist();
        return order;
    }
}
