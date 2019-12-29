package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.CoffeeShop;
import com.redhat.examples.quarkus.model.*;
import io.quarkus.test.junit.QuarkusTest;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.inject.Inject;

@QuarkusTest @Testcontainers
public class BaristaOrderIT extends BaseTestContainersIT{

    static final String incoming = "barista-orders-up";
    static final String outgoing = "barista-orders-in";

    @Inject
    Flyway flyway;

    @Inject
    CoffeeShop coffeeShop;

    public BaristaOrderIT() {
        super(incoming, outgoing);
    }

    @BeforeEach
    public void migrateDb() {

        flyway.migrate();
    }


    @Test
    public void testBaristaOrderIn() {

        Order order = new Order();
        BeverageOrder beverageOrder = new BeverageOrder(order, Beverage.BLACK_COFFEE);

        Order updatedOrder = coffeeShop.orderIn(order);
    }


}
