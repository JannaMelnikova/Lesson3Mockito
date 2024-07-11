package org.example.repository;

import org.example.entity.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepositoryImpl implements OrderRepository{
    Map<Integer,Order> dataBase = new HashMap<>();

    @Override
    public void save(Order order) {
        dataBase.put(order.getId(),order);
    }

    @Override
    public Order findById(int id) {
        return dataBase.get(id);
    }
}
