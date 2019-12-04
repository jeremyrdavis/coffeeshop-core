package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.CoffeeShop;
import com.redhat.examples.quarkus.model.Order;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Path("/order")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CoffeeShopResource {

    @Inject
    CoffeeShop coffeeShop;

    @POST
    @Transactional
    public Response createOrder(Order order) {
        Order result = coffeeShop.orderIn(order);
        return Response.created(URI.create("/order/" + result.id)).entity(result).build();
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
