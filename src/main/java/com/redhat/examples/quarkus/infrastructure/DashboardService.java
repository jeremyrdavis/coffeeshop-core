package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.CoffeeShopOrder;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DashboardService {

    Logger logger = Logger.getLogger(DashboardService.class);

    public void updateOrder(CoffeeShopOrder coffeeShopOrder) {

        logger.debug("order being updated:" + coffeeShopOrder.toString());
    }
}
