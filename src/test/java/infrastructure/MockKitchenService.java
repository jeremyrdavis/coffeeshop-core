package infrastructure;

import com.redhat.examples.quarkus.model.KitchenOrder;
import io.quarkus.test.Mock;

import javax.enterprise.context.ApplicationScoped;

@Mock
@ApplicationScoped
public class MockKitchenService {

    public void orderIn(KitchenOrder kitchenOrder) {

        System.out.println(kitchenOrder.toString());
    }
}
