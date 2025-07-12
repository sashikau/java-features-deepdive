package io.github.sashikau.reflection.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class DefaultPaymentService implements PaymentService{

    private static final Logger logger = LoggerFactory.getLogger(DefaultPaymentService.class);

    @Override
    public void process() {
        logger.info("Starting default payment service process");
    }
}
