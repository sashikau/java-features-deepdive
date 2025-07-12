package io.github.sashikau.reflection.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class SimplePaymentService implements PaymentService{

    private static final Logger logger = LoggerFactory.getLogger(SimplePaymentService.class);

    @Override
    public void process() {
        logger.info("Starting simple payment service process");
    }
}
