package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.Beverages;
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
                return Response.ok(new Order("b1f5c729-83ae-4e9d-87ca-abae7a1b87b3", "Jeremy", Beverages.BLACK_COFFEE, OrderStatus.ACCEPTED)).build();
            default:
                return Response.noContent().build();

        }
    }
}
