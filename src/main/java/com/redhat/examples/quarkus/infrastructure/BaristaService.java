package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.BeverageOrder;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

/**
 * BaristaResource handles communication with the Barista Microservice.
 * Kafka is used for communication.
 */
@ApplicationScoped
public class BaristaService {

    Logger logger = Logger.getLogger(BaristaService.class);

    static final String TOPIC = CoffeeShopConstants.TOPIC_BARISTA_ORDERS_OUTGOING;
    static final String TOPIC_INCOMING = CoffeeShopConstants.TOPIC_BARISTA_ORDERS_INCOMING;

    @Inject @Channel(CoffeeShopConstants.TOPIC_BARISTA_ORDERS_OUTGOING)
    Emitter<KafkaMessage> emitter;

    Jsonb jsonb = JsonbBuilder.create();

    public void orderIn(List<BeverageOrder> beverageOrderList) {

        logger.debug("beverage order received:" + beverageOrderList.size());
        beverageOrderList.stream().forEach(beverageOrder -> {
            KafkaMessage<String, String> message = KafkaMessage.of(
                    beverageOrder.id.toString(),
                    jsonb.toJson(beverageOrder).toString());
            emitter.send(message);
            logger.debug("beverage order sent:" + beverageOrder.toString());
        });
    }

    @Incoming(TOPIC_INCOMING)
    public CompletionStage<Void> orderUp(Message<String> orderUp) {

        logger.debug("beverage order received:" + orderUp.toString());
        return CompletableFuture.completedFuture(null);
    }

}
