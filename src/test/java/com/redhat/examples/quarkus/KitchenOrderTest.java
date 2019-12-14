package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.infrastructure.KitchenService;
import com.redhat.examples.quarkus.model.KitchenOrder;
import com.redhat.examples.quarkus.model.MenuItem;
import com.redhat.examples.quarkus.model.Order;
import io.quarkus.test.junit.QuarkusTest;
import net.mguenther.kafka.junit.EmbeddedKafkaCluster;
import net.mguenther.kafka.junit.KeyValue;
import net.mguenther.kafka.junit.ReadKeyValues;
import net.mguenther.kafka.junit.TopicConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.awaitility.Duration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static net.mguenther.kafka.junit.EmbeddedKafkaCluster.provisionWith;
import static net.mguenther.kafka.junit.EmbeddedKafkaClusterConfig.useDefaults;
import static org.awaitility.Awaitility.await;


@QuarkusTest
public class KitchenOrderTest {

    @Inject
    CoffeeShop coffeeShop;

    public void testSendOrderToKitchen() throws InterruptedException {

        await().atLeast(Duration.FIVE_SECONDS);

        Order order = new Order();
        KitchenOrder kitchenOrder = new KitchenOrder(order, MenuItem.COOKIE);
//        order.addKitchenOrder(kitchenOrder);
//        Order acceptedOrder = coffeeShop.orderIn(kitchenOrder);

        await().atLeast(Duration.TEN_SECONDS);

    }

}
