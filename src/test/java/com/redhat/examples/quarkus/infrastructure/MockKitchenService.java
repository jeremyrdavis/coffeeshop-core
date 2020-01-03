package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.KitchenOrder;
import com.redhat.examples.quarkus.model.MenuItem;
import io.quarkus.test.Mock;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Mock
@ApplicationScoped
public class MockKitchenService extends KitchenService{

    @Override
    public void orderIn(List<KitchenOrder> kitchenOrderList) {

        System.out.println("MockKitchenService" + kitchenOrderList.size());
        assertNotNull(kitchenOrderList);
        assertEquals(1, kitchenOrderList.size());
        assertEquals(MenuItem.COOKIE, kitchenOrderList.get(0).getMenuItem());
    }
}
