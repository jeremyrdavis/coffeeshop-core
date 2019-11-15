package com.redhat.examples.quarkus;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.flywaydb.core.Flyway;


import javax.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class CoffeeShopResourceTest {

    @Inject
    Flyway flyway;

    @BeforeEach
    public void setUp() {
        flyway.clean();
        flyway.migrate();
    }

    @Test
    public void testPlaceOrderEndpoint() {

        String payload = "{\"name\":\"Jeremy\",\"beverage\":\"Latte\"}";

        given()
          .when()
                .contentType(ContentType.JSON)
                .body(payload)
                .post("/order")
          .then()
             .statusCode(201)
             .body(containsString("ACCEPTED"));
    }

}