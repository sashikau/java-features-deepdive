package io.github.sashikau.reflection.proxy;

import nl.altindag.log.LogCaptor;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for {@link PaymentServiceProxy}.
 * <p>
 * Verifies that dynamic proxy behavior (e.g., logging, delegation) works as expected
 * </p>
 *
 * @author Sashika U
 */
public class PaymentServiceProxyTest {

    private LogCaptor logCaptor;

    @BeforeEach
    void setUp() {
        logCaptor = LogCaptor.forClass(PaymentServiceProxy.class);
    }

    @AfterEach
    void tearDown() {
        logCaptor.close();
    }
    @Test
    void shouldLogBeforeAndAfter() {
        PaymentService service = PaymentServiceFactory.getSimplePaymentService();
        service.process();

        assertThat(logCaptor.getInfoLogs())
                .containsExactly("Before method process", "After method process");
    }
}
