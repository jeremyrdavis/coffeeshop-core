package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.BeverageOrder;
import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * BaristaResource handles communication with the Barista Microservice.
 * Kafka is used for communication.
 */
@ApplicationScoped
public class BaristaService {

    Logger logger = Logger.getLogger(BaristaService.class);

    @Inject
    private Vertx vertx;

    static final String TOPIC = CoffeeShopConstants.TOPIC_BARISTA_ORDERS_OUTGOING;
    static final String TOPIC_INCOMING = CoffeeShopConstants.TOPIC_BARISTA_ORDERS_INCOMING;

    private KafkaProducer<String, String> producer;

    Jsonb jsonb = JsonbBuilder.create();

    public void orderIn(List<BeverageOrder> beverageOrderList) {

        System.out.println("beverage order received:" + beverageOrderList.size());
        logger.debug("beverage order received:" + beverageOrderList.size());
        beverageOrderList.stream().forEach(beverageOrder -> {
            KafkaProducerRecord<String, String> record = KafkaProducerRecord.create(
                    TOPIC,
                    beverageOrder.id.toString(),
                    jsonb.toJson(beverageOrder).toString());
            producer.write(record);
            logger.debug("beverage order sent:" + beverageOrder.toString());
        });
    }

/*
    @Incoming(TOPIC_INCOMING)
    public CompletionStage<Void> orderUp(Message<String> orderUp) {

        logger.debug("beverage order received:" + orderUp.toString());
        return CompletableFuture.completedFuture(null);
    }
*/

    @PostConstruct
    public void postConstruct() {
        // Config values can be moved to application.properties
        Map<String, String> config = new HashMap<>();
        config.put("bootstrap.servers", "localhost:9092");
        config.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        config.put("acks", "1");
        producer = KafkaProducer.create(vertx, config);

    }
}
