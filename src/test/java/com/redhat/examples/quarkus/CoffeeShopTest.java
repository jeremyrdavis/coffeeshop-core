package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.model.Beverage;
import com.redhat.examples.quarkus.model.MenuItem;
import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;
import io.quarkus.test.junit.QuarkusTest;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CoffeeShopTest {

    @Inject
    CoffeeShop coffeeShop;

    @Inject
    Flyway flyway;

    @BeforeEach
    public void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void testAcceptOrder() {
        Order order = new Order();
        Order result = coffeeShop.orderIn(order);
        assertNotNull(result);
        assertNotNull(result.getOrderNumber());
    }

    @Test
    public void updateOrder() {
        Order result = coffeeShop.updateOrder(1, OrderStatus.IN_PROGRESS);
        assertEquals(OrderStatus.IN_PROGRESS, result.getStatus());
    }

    @Test
    public void testAcceptBeverageOrder() {
        Order order = new Order();
        order.beverage = Beverage.BLACK_COFFEE;
    }

    @Test
    public void testKitchenOrder() {
        Order order = new Order();
        order.beverage = Beverage.BLACK_COFFEE;
        order.menuItem = MenuItem.COOKIE;
        Order updatedOrder = coffeeShop.orderIn(order);
        assertNotNull("The order should have a number", updatedOrder.orderNumber);
        assertEquals(OrderStatus.ACCEPTED, updatedOrder.status);
    }


}
