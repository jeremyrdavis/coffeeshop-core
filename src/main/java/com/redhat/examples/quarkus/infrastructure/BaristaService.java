package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.BeverageOrder;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

/**
 * BaristaResource handles communication with the Barista Microservice.
 * Kafka is used for communication.
 */
@ApplicationScoped
public class BaristaService {

    Logger logger = Logger.getLogger(BaristaService.class);

    public void orderIn(BeverageOrder beverageOrder) {

        logger.debug("beverage order received:" + beverageOrder.toString());
    }

}
