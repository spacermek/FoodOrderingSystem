package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.OrderItem;
import java.util.List;

public interface OrderItemService {
    List<OrderItem> getAll();
    OrderItem getById(Long id);
    OrderItem save(OrderItem orderItem);
    OrderItem updateById(OrderItem orderItem, Long id);
    void deleteById(Long id);
}
