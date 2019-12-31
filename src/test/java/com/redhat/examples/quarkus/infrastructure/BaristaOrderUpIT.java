package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.Beverage;
import com.redhat.examples.quarkus.model.BeverageOrder;
import com.redhat.examples.quarkus.model.Order;
import com.redhat.examples.quarkus.model.OrderStatus;
import io.quarkus.deployment.annotations.Produce;
import io.quarkus.test.junit.QuarkusTest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
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
public class BaristaOrderUpIT extends BaseTestContainersIT{

    @Inject
    BaristaService baristaService;

    public BaristaOrderUpIT() {
        // We use CONSUMER_TOPIC in this test case because we are testing message consumption from the Barista Service
        this.PRODUCER_TOPIC = CoffeeShopConstants.TOPIC_BARISTA_ORDERS_INCOMING;
        this.CONSUMER_TOPIC = CoffeeShopConstants.TOPIC_BARISTA_ORDERS_INCOMING;
    }

    @Test
    @Timeout(20)
    public void testBaristaOrderUp() throws ExecutionException, InterruptedException {

        Order order = new Order();
        BeverageOrder beverageOrder = new BeverageOrder(order, Beverage.BLACK_COFFEE);
        order.setBeverageOrder(Arrays.asList(beverageOrder));
        order.setStatus(OrderStatus.READY);

        // We use CONSUMER_TOPIC in this test case because we are testing message consumption from the Barista Service
        ProducerRecord<String, String> record = new ProducerRecord(CONSUMER_TOPIC, order.orderNumber, jsonb.toJson(order).toString());
        kafkaProducer.send(record);

    }

}
