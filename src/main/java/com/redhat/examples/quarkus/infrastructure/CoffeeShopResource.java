package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.CoffeeShop;
import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CoffeeShopResource {

    @Inject
    CoffeeShop coffeeShop;

    @POST
    @Path("/order")
    public Response createOrder(Order order) {
        Order result = coffeeShop.acceptOrder(order);
        return Response.accepted().entity(order).build();
    }

}
