package org.example.service;

import org.example.entity.Order;
import org.example.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;


    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private OrderService orderService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void placeOrderSuccessFul() {
        Order order = new Order(1, 100);
        when(paymentService.processPayment(100)).thenReturn(true);

        boolean result = orderService.placeOrder(order);
        assertTrue(result);
        assertTrue(order.isPaid());
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void placeOrderUnsuccessful200Times() {
        Order order = new Order(1, 200);
        //тест на непройденную оплату, если будет количество 200 times должен вернуть false
        when(paymentService.processPayment(anyInt())).thenReturn(false);
        //все остальные методы должны быть c false, проверяем по циклу 200 раз
        boolean result = true;
        for (int i = 0; i < 200; i++) {
            result = orderService.placeOrder(order);
            assertFalse(result); // Ожидаем, что результат будет false
        }
        // Проверка, что метод processPayment был вызван 200 раз
        verify(paymentService, times(200)).processPayment(anyInt());

        // Проверка, что статус оплаты заказа false
        assertFalse(order.isPaid());

        // Проверка, что метод save не был вызван ни разу
        verify(orderRepository, never()).save(order);
    }




}