package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.BeverageOrder;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.List;

/**
 * BaristaResource handles communication with the Barista Microservice.
 * Kafka is used for communication.
 */
@ApplicationScoped
public class BaristaService {

    Logger logger = Logger.getLogger(BaristaService.class);

    static final String TOPIC = CoffeeShopConstants.TOPIC_BARISTA_ORDERS_OUTGOING;

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

}
