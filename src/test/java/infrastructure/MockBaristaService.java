package infrastructure;

import com.redhat.examples.quarkus.infrastructure.BaristaService;
import com.redhat.examples.quarkus.model.Beverage;
import com.redhat.examples.quarkus.model.BeverageOrder;
import io.quarkus.test.Mock;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Mock
@ApplicationScoped
public class MockBaristaService extends BaristaService {

    Logger logger = Logger.getLogger(MockBaristaService.class);

    public void orderIn(BeverageOrder beverageOrder) {

        logger.debug("beverage order received:" + beverageOrder.toString());
        assertEquals(Beverage.BLACK_COFFEE, beverageOrder.beverage);

    }


}
