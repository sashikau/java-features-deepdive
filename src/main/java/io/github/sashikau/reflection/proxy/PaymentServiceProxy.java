package io.github.sashikau.reflection.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * {@code PaymentServiceProxy} is a dynamic proxy handler for the {@link PaymentService} interface.
 * <p>
 * It allows interception of method calls to a real {@code PaymentService} implementation,
 * enabling features such as logging, security checks, retry mechanisms, etc.,
 * without modifying the actual service implementation.
 * </p>
 *
 * <p>This class uses Java's built-in {@link java.lang.reflect.Proxy} API and {@link InvocationHandler} interface.</p>
 *
 * <h2>Usage Example:</h2>
 * <pre>{@code
 * PaymentService realService = new DefaultPaymentService();
 * PaymentService proxiedService = PaymentServiceProxy.newInstance(realService);
 * proxiedService.process();
 * }</pre>
 *
 * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html">Official Java Proxy Documentation</a>
 *
 * @author Sashika U
 */
class PaymentServiceProxy implements InvocationHandler {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceProxy.class);
    private final PaymentService target;

    public static PaymentService newInstance(PaymentService target) {
        return (PaymentService) java.lang.reflect.Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new PaymentServiceProxy(target));
    }

    private PaymentServiceProxy(PaymentService target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method m, Object[] args)
            throws Throwable
    {
        Object result;
        try {
            logger.info("Before method {}", m.getName());
            result = m.invoke(target, args);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " +
                    e.getMessage());
        } finally {
            logger.info("After method {}", m.getName());
        }
        return result;
    }
}
