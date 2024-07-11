package org.example.repository;


import org.example.entity.Order;

public interface OrderRepository {
    void save(Order order);

    Order findById(int id);

}