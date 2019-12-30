package com.redhat.examples.quarkus.infrastructure;

import com.redhat.examples.quarkus.model.KitchenOrder;
//import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class KitchenService {

/*
    @Inject
    @Channel("kitchen")
    Emitter<String> kitchen;
*/

    public void orderIn(List<KitchenOrder> kitchenOrderList) {

        System.out.println(kitchenOrderList.size());
        //kitchen.send(kitchenOrder.toString());
    }


}
