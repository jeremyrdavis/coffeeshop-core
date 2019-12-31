package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.KitchenOrder;
import io.quarkus.test.Mock;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@Mock
@ApplicationScoped
public class MockKitchenService extends KitchenService{

    @Override
    public void orderIn(List<KitchenOrder> kitchenOrderList) {

        System.out.println(kitchenOrderList.size());
        //kitchen.send(kitchenOrder.toString());
    }
}
