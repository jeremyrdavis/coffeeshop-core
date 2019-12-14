package infrastructure;

import com.redhat.examples.quarkus.model.CoffeeShopOrder;
import io.quarkus.test.Mock;

import javax.enterprise.context.ApplicationScoped;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@Mock
@ApplicationScoped
public class MockDashboardService extends DashboardService {

    public void updateOrder(CoffeeShopOrder coffeeShopOrder) {

        assertNotNull(coffeeShopOrder);
        logger.debug("order being updated:" + coffeeShopOrder.toString());
    }

}
