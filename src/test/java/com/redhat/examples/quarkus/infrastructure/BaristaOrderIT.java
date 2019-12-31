package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.CoffeeShop;
import com.redhat.examples.quarkus.model.*;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.testcontainers.junit.jupiter.Testcontainers;

import javax.inject.Inject;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest @Testcontainers
public class BaristaOrderIT extends BaseTestContainersIT{

    @Inject
    Flyway flyway;

    @Inject
    BaristaService baristaService;

    /*
        Set both to outgoing because this test is verifying that orders are sent to the Kitchen Service
     */
    public BaristaOrderIT() {
        this.PRODUCER_TOPIC = CoffeeShopConstants.TOPIC_BARISTA_ORDERS_OUTGOING;
        this.CONSUMER_TOPIC = CoffeeShopConstants.TOPIC_BARISTA_ORDERS_OUTGOING;
    }

    @BeforeEach
    public void migrateDb() {
        flyway.migrate();
    }


    @Test @Timeout(60)
    public void testBaristaOrderIn() throws ExecutionException, InterruptedException {

        Order order = new Order();
        BeverageOrder beverageOrder = new BeverageOrder(order, Beverage.BLACK_COFFEE);
        baristaService.orderIn(Arrays.asList(beverageOrder));

        ConsumerRecords<String, String> newRecords = kafkaConsumer.poll(Duration.ofMillis(10000));
        assertEquals(0, newRecords.count());
        for (ConsumerRecord<String, String> record : newRecords) {
            System.out.printf("offset = %d, key = %s, value = %s\n",
                    record.offset(),
                    record.key(),
                    record.value());
        }
    }
}
