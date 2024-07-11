package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import org.example.entity.Order;
import org.example.repository.OrderRepository;
@AllArgsConstructor
@Getter
@Setter
public class OrderService {
    private OrderRepository orderRepository;
    private PaymentService paymentService;

    public boolean placeOrder(Order order) {
        boolean processPayment = paymentService.processPayment(order.getAmount());
        if(processPayment) {
            order.setPaid(true);
            orderRepository.save(order);
            return true;
        }
        return false;
    }


}
