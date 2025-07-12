package io.github.sashikau.reflection.proxy;

/**
 * {@code PaymentServiceFactory} provides static factory methods to create ready-to-use
 * instances of {@link PaymentService} implementations.
 * <p>
 * The returned service instances are dynamically proxied using {@link PaymentServiceProxy},
 * but this complexity is abstracted away from the consumer. The client code simply receives a
 * {@code PaymentService} instance and can invoke operations without knowing or caring about
 * the underlying proxy logic.
 * </p>
 *
 * <p>This design promotes cleaner code and separation of concerns — allowing additional
 * behaviors (e.g., logging, validation, metrics) to be injected transparently via proxy
 * without burdening the business logic.</p>
 *
 * <p><strong>Usage Example:</strong></p>
 * <pre>{@code
 * PaymentService service = PaymentServiceFactory.getDefaultPaymentService();
 * service.process();
 * }</pre>
 *
 * @author Sashika U
 */
public class PaymentServiceFactory {

    /**
     * Returns a proxied instance of {@link SimplePaymentService}.
     * <p>The consumer does not need to know that the service is proxied.</p>
     *
     * @return a transparent {@link PaymentService} instance
     */
    public static PaymentService getSimplePaymentService() {
        PaymentService paymentService = new SimplePaymentService();
        return PaymentServiceProxy.newInstance(paymentService);
    }

    /**
     * Returns a proxied instance of {@link DefaultPaymentService}.
     * <p>The consumer does not need to know that the service is proxied.</p>
     *
     * @return a transparent {@link PaymentService} instance
     */
    public static PaymentService getDefaultPaymentService() {
        PaymentService paymentService = new DefaultPaymentService();
        return PaymentServiceProxy.newInstance(paymentService);
    }
}

