package ru.practicum.migration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTestJupiter {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceTestJupiter.class);

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private OrderService orderService;

    @BeforeAll
    public static void setUpBeforeClass() {
        logger.info("Запускаем все тесты для проверки сервиса заказов");
    }

    @AfterAll
    public static void tearDownAfterClass() {
        logger.info("Все тесты для проверки сервиса заказов были запущены");
    }

    @Test
    public void testOrderCreation() {
        // Настраиваем mock для успешной обработки платежа
        when(paymentService.processPayment(anyDouble())).thenReturn(true);

        // Проверяем создание заказа
        boolean result = orderService.createOrder(100.0);
        Assertions.assertTrue(result);

        // Проверяем, что метод processPayment вызывался один раз
        verify(paymentService, times(1)).processPayment(100.0);
    }

    @Test
    @Disabled("Тест пропущен, требуется исправление логики отмены заказа")
    public void testOrderCancellation() {
        // Тест игнорируется, так как логика отмены требует исправления
        boolean result = orderService.cancelOrder(123);
        Assertions.assertTrue(result);
    }

    @Test
    @Tag("ProcessingTest")
    public void testOrderProcessing() {
        // Проверяем, что заказ может быть обработан
        boolean result = orderService.processOrder(123);
        Assertions.assertTrue(result);
    }

}
