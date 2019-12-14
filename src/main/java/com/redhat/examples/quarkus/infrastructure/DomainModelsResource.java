package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.Beverage;
import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/models")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DomainModelsResource {

    @GET
    @Path("/{model}")
    public Response getModel(@PathParam("model") String model) {
        switch (model) {
            case "Order":
                Order order = new Order();
                order.name = "Jeremy";
                order.status = OrderStatus.ACCEPTED;
                order.addBeverage(Beverage.BLACK_COFFEE);
                return Response.ok().entity(order).build();
            default:
                return Response.noContent().build();

        }
    }
}
