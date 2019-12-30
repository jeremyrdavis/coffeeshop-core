package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.model.*;
import io.quarkus.test.junit.QuarkusTest;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

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
    public void testAcceptOrder() throws ExecutionException, InterruptedException {
        Order order = new Order();
        Order result = coffeeShop.orderIn(order).get();
        assertNotNull(result);
        assertNotNull(result.getOrderNumber());
    }

    @Test
    public void updateOrder() throws ExecutionException, InterruptedException {
        Order order = new Order();
        BeverageOrder beverageOrder = new BeverageOrder(order, Beverage.BLACK_COFFEE);
        KitchenOrder kitchenOrder = new KitchenOrder(order, MenuItem.COOKIE);

        Order updatedOrder = coffeeShop.orderIn(order).get();

        Order result = coffeeShop.updateOrder(updatedOrder.id, OrderStatus.IN_PROGRESS);
        assertEquals(OrderStatus.IN_PROGRESS, result.getStatus());
    }

    @Test
    public void testAcceptBeverageOrder() {
        Order order = new Order();
        order.beverage = Beverage.BLACK_COFFEE;
    }

    @Test
    public void testKitchenOrder() throws ExecutionException, InterruptedException {

        Order order = new Order();
        BeverageOrder beverageOrder = new BeverageOrder(order, Beverage.BLACK_COFFEE);
        KitchenOrder kitchenOrder = new KitchenOrder(order, MenuItem.COOKIE);

        Order updatedOrder = coffeeShop.orderIn(order).get();

        assertNotNull("The order should have a number", updatedOrder.orderNumber);
        assertEquals(OrderStatus.ACCEPTED, updatedOrder.status);

        Order anotherOrder = new Order();
        BeverageOrder anotherBeverageOrder = new BeverageOrder(order, Beverage.COFFEE_WITH_ROOM);
        KitchenOrder anotherKitchenOrder = new KitchenOrder(order, MenuItem.MUFFIN);

        Order updatedAnotherOrder = coffeeShop.orderIn(anotherOrder).get();

        assertNotNull("The order should have a number", updatedAnotherOrder.orderNumber);
        assertEquals(OrderStatus.ACCEPTED, updatedAnotherOrder.status);

        assertFalse(updatedOrder.orderNumber.equals(updatedAnotherOrder.orderNumber));
    }

    @Test
    public void testBeverageOnlyOrder() throws ExecutionException, InterruptedException {

        Order order = new Order("Jeremy");
        order.addBeverage(Beverage.BLACK_COFFEE);

        Order updatedOrder = coffeeShop.orderIn(order).get();

        assertNotNull(updatedOrder.orderNumber);
        assertNotNull(updatedOrder.id);
        assertEquals(OrderStatus.ACCEPTED, updatedOrder.status);
    }

    @Test
    public void testDashboardServiceReceivesOrderInNotification() throws ExecutionException, InterruptedException {

        Order order = new Order("Jeremy");
        order.addBeverage(Beverage.BLACK_COFFEE);

        Order updatedOrder = coffeeShop.orderIn(order).get();

        assertNotNull(updatedOrder.orderNumber);
        assertNotNull(updatedOrder.id);
        assertEquals(OrderStatus.ACCEPTED, updatedOrder.status);

    }

}
