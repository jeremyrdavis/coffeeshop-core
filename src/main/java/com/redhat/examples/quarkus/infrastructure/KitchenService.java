package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.KitchenOrder;
//import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.List;

@ApplicationScoped
public class KitchenService {

    Logger logger = Logger.getLogger(KitchenService.class);

    static final String TOPIC = CoffeeShopConstants.TOPIC_KITCHEN_ORDERS_OUTGOING;
    static final String TOPIC_INCOMING = CoffeeShopConstants.TOPIC_KITCHEN_ORDERS_INCOMING;

    @Inject @Channel(CoffeeShopConstants.TOPIC_KITCHEN_ORDERS_OUTGOING)
    Emitter<KafkaMessage> emitter;

    Jsonb jsonb = JsonbBuilder.create();

    public void orderIn(List<KitchenOrder> kitchenOrderList) {

        logger.debug("beverage order received:" + kitchenOrderList.size());
        kitchenOrderList.stream().forEach(ko -> {
            KafkaMessage<String, String> message = KafkaMessage.of(
                    ko.id.toString(),
                    jsonb.toJson(ko).toString());
            emitter.send(message);
            logger.debug("beverage order sent:" + ko.toString());
        });
    }


}
