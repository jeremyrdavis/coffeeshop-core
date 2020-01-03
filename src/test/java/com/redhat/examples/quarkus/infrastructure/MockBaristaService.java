package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.Beverage;
import com.redhat.examples.quarkus.model.BeverageOrder;
import io.quarkus.test.Mock;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Mock
@ApplicationScoped
public class MockBaristaService extends BaristaService {

    Logger logger = Logger.getLogger(MockBaristaService.class);

    @Override
    public void orderIn(List<BeverageOrder> beverageOrderList) {
        System.out.println("MockBaristaService");
        logger.debug("beverage order received:" + beverageOrderList.size());
        beverageOrderList.forEach(o -> {
            System.out.println(o);
            assertEquals(Beverage.BLACK_COFFEE, o.beverage);
        });
    }
}
