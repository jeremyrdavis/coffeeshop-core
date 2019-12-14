package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.infrastructure.BaristaService;
import com.redhat.examples.quarkus.infrastructure.KitchenService;
import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;


@ApplicationScoped
public class CoffeeShop {

    Logger logger = Logger.getLogger(CoffeeShop.class);

    @Inject
    BaristaService baristaResource;

    @Inject
    KitchenService kitchenService;

    @Transactional
    public Order orderIn(final Order order) {

        logger.debug("received order:" + order.toString());

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
