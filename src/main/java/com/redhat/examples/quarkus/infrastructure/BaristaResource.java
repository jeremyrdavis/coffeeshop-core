package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.CoffeeShop;
import com.redhat.examples.quarkus.model.Order;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.concurrent.CompletableFuture;

/**
 * BaristaResource handles communication with the Barista Microservice.
 * Kafka is used for communication.
 */
@ApplicationScoped
public class BaristaResource {

    @Inject
    CoffeeShop coffeeShop;

    private Jsonb jsonb = JsonbBuilder.create();

    @Incoming("orders")
    public void acceptOrder(String message) {
        Order order = jsonb.fromJson(message, Order.class);
        System.out.println("Received order from Kafka : " + message);
        CompletableFuture.runAsync(() -> {
            Order result = coffeeShop.orderIn(order);
            System.out.println(result.toString());
        });
    }


}
