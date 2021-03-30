package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAll();
    Order getById(Long id);
    Order save(Order order);
    Order updateById(Order order, Long id);
    void deleteById(Long id);
}
