package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.CoffeeShop;
import com.redhat.examples.quarkus.model.KitchenOrder;
import com.redhat.examples.quarkus.model.MenuItem;
import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;
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
public class SendKitchenOrderIT extends BaseTestContainersIT{

    @Inject
    Flyway flyway;

    @Inject
    KitchenService kitchenService;

    /*
        Set both to incoming because this test is verifying that orders are sent to the Barista Service
     */
    public SendKitchenOrderIT() {
        this.PRODUCER_TOPIC = CoffeeShopConstants.TOPIC_KITCHEN_ORDERS_OUTGOING;
        this.CONSUMER_TOPIC = CoffeeShopConstants.TOPIC_KITCHEN_ORDERS_OUTGOING;
    }

    @BeforeEach
    public void migrateDb() {
        flyway.migrate();
    }

    @Test @Timeout(60)
    public void testSendKitchenOrder() throws ExecutionException, InterruptedException {

        Order order = new Order();
        order.id = 6L;
        order.name = "Jeremy";

        KitchenOrder kitchenOrder = new KitchenOrder(order, MenuItem.COOKIE);
        kitchenOrder.id = 7L;
        System.out.println("kitchenOrder to be sent: " + kitchenOrder);
        System.out.println("kitchenService: " + kitchenService.toString());

        kitchenService.orderIn(Arrays.asList(kitchenOrder));

        assertNotNull(kafkaConsumer);
        ConsumerRecords<String, String> newRecords = kafkaConsumer.poll(Duration.ofMillis(10000));
        assertNotNull(newRecords);
        assertEquals(1, newRecords.count());
        for (ConsumerRecord<String, String> record : newRecords) {
            System.out.printf("offset = %d, key = %s, value = %s\n",
                    record.offset(),
                    record.key(),
                    record.value());
        }
    }

}
