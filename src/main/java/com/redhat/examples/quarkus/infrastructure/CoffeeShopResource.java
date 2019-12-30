package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.CoffeeShop;
import com.redhat.examples.quarkus.model.Order;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CoffeeShopResource {

    @Inject
    CoffeeShop coffeeShop;

    @POST
    @Transactional
    public CompletionStage<Response> createOrder(Order order) {

        Order orderIn = new Order();
        order.getKitchenOrder().ifPresent(kitchenOrders -> {
            kitchenOrders.forEach( ko -> {orderIn.addMenuItem(ko.getMenuItem());});
        });
        order.getBeverageOrder().ifPresent(beverageOrders -> {
            beverageOrders.forEach( b -> {orderIn.addBeverage(b.beverage);});
        });
        CompletableFuture<Order> result = coffeeShop.orderIn(orderIn);
        return result.thenApply(r -> {
            return Response.created(URI.create("/order/" + r.id)).entity(r).build();
        });
    }

    @GET
    @Path("/{id}")
    public Response getOrder(@PathParam("id") Long id){
        Order order = Order.findById(id);
        if (order == null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(order).build();
    }

}
