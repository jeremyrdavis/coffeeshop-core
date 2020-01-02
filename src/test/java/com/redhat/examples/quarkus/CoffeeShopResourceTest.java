package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.model.Beverage;
import com.redhat.examples.quarkus.model.MenuItem;
import com.redhat.examples.quarkus.model.Order;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Timeout;


import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import javax.json.bind.JsonbConfig;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class CoffeeShopResourceTest {

    @Inject
    Flyway flyway;

    @BeforeEach
    public void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test @Timeout(10)
    public void testPlaceOrderEndpoint() {

        Order order = new Order("Jeremy");
        order.addBeverage(Beverage.BLACK_COFFEE);
        order.addMenuItem(MenuItem.COOKIE);

        Jsonb jsonb = JsonbBuilder.create();
        String payload = jsonb.toJson(order);

        System.out.println("payload\n");
        System.out.println(payload);
        System.out.println("\n");

        Response response = given()
          .when()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/order");
/*
          .then()
             .statusCode(201)
             .body(containsString("ACCEPTED"));
*/
        assertEquals(201, response.statusCode());
    }

}