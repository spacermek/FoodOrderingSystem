package com.intro.introSpring.Controller;

import com.intro.introSpring.Entity.Order;
import com.intro.introSpring.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAll();
    }

    @GetMapping("/{orderId}")
    public Order getOrderById(@PathVariable Long orderId) {
        return orderService.getById(orderId);
    }

    @PutMapping("/{orderId}")
    public Order updateOrderById(@RequestBody Order order, @PathVariable Long orderId) {
        return orderService.updateById(order, orderId);
    }

    @PostMapping
    public Order saveOrder(@RequestBody Order order) {
        return orderService.save(order);
    }

    @DeleteMapping("/{orderId}")
    public void deleteOrderById(@PathVariable Long orderId) {
        orderService.deleteById(orderId);
    }
}
