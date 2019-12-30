package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.infrastructure.BaristaService;
import com.redhat.examples.quarkus.infrastructure.KitchenService;
import com.redhat.examples.quarkus.model.BeverageOrder;
import com.redhat.examples.quarkus.model.KitchenOrder;
import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;


@ApplicationScoped
public class CoffeeShop {

    Logger logger = Logger.getLogger(CoffeeShop.class);

    @Inject
    BaristaService baristaService;

    @Inject
    KitchenService kitchenService;

    @Transactional
    public CompletableFuture<Order> orderIn(final Order order) {

        logger.debug("received order:" + order.toString());

        order.setStatus(OrderStatus.ACCEPTED);
        order.persist();

        CompletableFuture<Order> retVal = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            order.getBeverageOrder().ifPresent(this::baristaOrderIn);
            order.getKitchenOrder().ifPresent(this::kitchenOrderIn);
            retVal.complete(order);
        });

        return retVal;
    }

    void kitchenOrderIn(List<KitchenOrder> kitchenOrderList) {
        kitchenService.orderIn(kitchenOrderList);
    }

    void baristaOrderIn(List<BeverageOrder> orderList) {
        baristaService.orderIn(orderList);
    }

    @Transactional
    public Order updateOrder(long id, OrderStatus status) {
        Order order = Order.findById(id);
        order.setStatus(status);
        order.persist();
        return order;
    }
}
