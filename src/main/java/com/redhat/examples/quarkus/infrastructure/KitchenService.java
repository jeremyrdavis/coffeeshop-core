package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.BeverageOrder;
import com.redhat.examples.quarkus.model.KitchenOrder;
//import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.vertx.core.Vertx;
import io.vertx.kafka.client.producer.KafkaProducer;
import io.vertx.kafka.client.producer.KafkaProducerRecord;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ApplicationScoped
public class KitchenService {

    Logger logger = Logger.getLogger(KitchenService.class);

    @Inject
    private Vertx vertx;

    static final String TOPIC = CoffeeShopConstants.TOPIC_KITCHEN_ORDERS_OUTGOING;
    static final String TOPIC_INCOMING = CoffeeShopConstants.TOPIC_KITCHEN_ORDERS_INCOMING;

    Jsonb jsonb = JsonbBuilder.create();

    private KafkaProducer<String, String> producer;

    public KitchenService() {}

    public void orderIn(List<KitchenOrder> kitchenOrderList) {

        System.out.println(kitchenOrderList.size());
        System.out.println("kitchen order received:" + kitchenOrderList.size());
        logger.debug("kitchen order received:" + kitchenOrderList.size());
        kitchenOrderList.stream().forEach(kitchenOrder -> {
            KafkaProducerRecord<String, String> record = KafkaProducerRecord.create(
                    TOPIC,
                    kitchenOrder.id.toString(),
                    jsonb.toJson(kitchenOrder).toString());
            producer.write(record);
            logger.debug("kitchen order sent:" + kitchenOrder.toString());
        });
    }

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
