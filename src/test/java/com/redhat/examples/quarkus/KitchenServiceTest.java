package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.infrastructure.KitchenService;
import com.redhat.examples.quarkus.model.KitchenOrder;
import com.redhat.examples.quarkus.model.MenuItem;
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
public class KitchenServiceTest {

    @Inject
    KitchenService kitchenService;

    @Test
    public void testConnection() {
    }

    public void testSendOrderToKitchen() throws InterruptedException {

        await().atLeast(Duration.FIVE_SECONDS);

        KitchenOrder kitchenOrder = new KitchenOrder("12345", "Jeremy", MenuItem.COOKIE);
        kitchenService.orderIn(kitchenOrder);

        await().atLeast(Duration.TEN_SECONDS);

    }

}
