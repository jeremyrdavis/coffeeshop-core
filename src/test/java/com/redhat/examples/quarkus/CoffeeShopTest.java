package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CoffeeShopTest {

    @Inject
    CoffeeShop coffeeShop;

    @Test
    public void testAcceptOrder() {
        Order order = new Order();
        Order result = coffeeShop.acceptOrder(order);
        assertNotNull(result);
        assertNotNull(result.getOrderNumber());
    }

    @Test
    public void updateOrder() {
        Order result = coffeeShop.updateOrder(1, OrderStatus.IN_PROGRESS);
        assertEquals(OrderStatus.IN_PROGRESS, result.getStatus());
    }


}
