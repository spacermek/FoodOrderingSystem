package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.OrderItem;
import com.intro.introSpring.Exceptions.NotEnoughProductException;
import com.intro.introSpring.Exceptions.ResourceNotFoundException;
import com.intro.introSpring.Repository.OrderItemRepository;
import com.intro.introSpring.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemImpl implements OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final DishService dishService;

    @Autowired
    public OrderItemImpl(OrderItemRepository orderItemRepository, OrderRepository orderRepository, DishService dishService) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.dishService = dishService;
    }

    @Override
    public List<OrderItem> getAll() {
        return orderItemRepository.findAll();
    }

    @Override
    public OrderItem getById(Long id) {
        return orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find order item with id ", id));
    }

    @Override
    public OrderItem save(OrderItem orderItem) {
        if (orderItem.getQuantity() > dishService.getById(orderItem.getDishId().getId()).getQuantity()) throw new NotEnoughProductException(
                "We don't have product with this amount!"
        );

        orderItem.setPrice(dishService.getById(orderItem.getDishId().getId()).getPrice());
        orderRepository.findById(orderItem.getOrderId().getId())
                .map(newOrder -> {
                    newOrder.setTotal(orderItem.getQuantity() * orderItem.getPrice());
                    return orderRepository.save(newOrder);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find order item with id ", orderItem.getOrderId().getId()));

        return orderItemRepository.save(orderItem);
    }

    @Override
    public OrderItem updateById(OrderItem orderItem, Long id) {
        return orderItemRepository.findById(id)
                .map(newOrderItem -> {
                    newOrderItem.setPrice(orderItem.getPrice());
                    newOrderItem.setQuantity(orderItem.getQuantity());
                    newOrderItem.setDishId(orderItem.getDishId());
                    newOrderItem.setOrderId(orderItem.getOrderId());
                    return orderItemRepository.save(newOrderItem);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find order item with id ", id));
    }

    @Override
    public void deleteById(Long id) {
        orderItemRepository.deleteById(id);
    }
}
