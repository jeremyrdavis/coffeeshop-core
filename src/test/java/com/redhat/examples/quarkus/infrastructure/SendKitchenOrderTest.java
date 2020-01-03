package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.CoffeeShop;
import com.redhat.examples.quarkus.model.*;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.jboss.logging.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import javax.inject.Inject;
import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class SendKitchenOrderTest {

    Logger logger = Logger.getLogger(SendKitchenOrderTest.class);

    @Inject
    CoffeeShop coffeeShop;

    @Test
    @Timeout(5)
    public void testBaristaOrderIn() throws ExecutionException, InterruptedException {

        Order order = new Order();
        KitchenOrder kitchenOrder = new KitchenOrder(order, MenuItem.COOKIE);
        BeverageOrder beverageOrder = new BeverageOrder(order, Beverage.BLACK_COFFEE);
        order.setBeverageOrder(Arrays.asList(beverageOrder));

        CompletableFuture<Order> futureOrder = coffeeShop.orderIn(order);
        Order updatedOrder = futureOrder.get();
        assertNotNull(updatedOrder);
        assertEquals(OrderStatus.ACCEPTED, updatedOrder.status);

    }

}
