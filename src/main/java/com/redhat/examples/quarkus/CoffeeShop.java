package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.infrastructure.BaristaResource;
import com.redhat.examples.quarkus.infrastructure.KitchenService;
import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.UUID;
import java.util.stream.Stream;

@ApplicationScoped
public class CoffeeShop {

    @Inject
    BaristaResource baristaResource;

    @Inject
    KitchenService kitchenService;

    @Transactional
    public Order orderIn(Order order) {

        order.setStatus(OrderStatus.ACCEPTED);
        order.persist();

        order.getBeverageOrder().ifPresent(beverages -> beverages.forEach(b -> baristaResource.orderIn(b)));
        order.getKitchenOrder().ifPresent(ko -> ko.stream().forEach(o -> kitchenService.orderIn(o)));

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
