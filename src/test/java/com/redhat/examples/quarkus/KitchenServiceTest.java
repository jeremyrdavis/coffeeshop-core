package com.redhat.examples.quarkus;

import com.redhat.examples.quarkus.infrastructure.KitchenService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.fail;

@QuarkusTest
public class KitchenServiceTest {

    @Inject
    KitchenService kitchenService;

    @Test
    public void testSendOrderToKitchen() {
        fail("not implemented yet");
    }

}
