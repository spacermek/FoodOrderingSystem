package com.intro.introSpring.Service;

import com.intro.introSpring.Entity.Order;
import com.intro.introSpring.Enum.Role;
import com.intro.introSpring.Exceptions.NotDefinedAddress;
import com.intro.introSpring.Exceptions.ResourceNotFoundException;
import com.intro.introSpring.Exceptions.RoleException;
import com.intro.introSpring.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserService userService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
    }

    @Override
    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order getById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find order with id ", id));
    }

    @Override
    public Order save(Order order) {

        if (order.getDelivery() == Boolean.TRUE && order.getDeliveryAddress() == null) throw new NotDefinedAddress(
                "The address is not defined!"
        );

        if (userService.getById(order.getUserId().getId()).getUserRole().getName().equals(Role.Client)) {
            order.setTotal(0.0);
            return orderRepository.save(order);
        }

        else throw new RoleException("Incorrect role!");
    }

    @Override
    public Order updateById(Order order, Long id) {
        return orderRepository.findById(id)
                .map(newOrder -> {
                    newOrder.setOrderDate(order.getOrderDate());
                    newOrder.setTotal(order.getTotal());
                    newOrder.setDelivery(order.getDelivery());
                    newOrder.setDeliveryAddress(order.getDeliveryAddress());
                    newOrder.setUserId(order.getUserId());
                    newOrder.setCafeId(order.getCafeId());
                    return orderRepository.save(newOrder);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Couldn't find order with id ", id));
    }

    @Override
    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}
