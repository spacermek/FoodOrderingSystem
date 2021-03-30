package com.intro.introSpring.Controller;

import com.intro.introSpring.Entity.OrderItem;
import com.intro.introSpring.Service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order_item")
public class OrderItemController {

    private final OrderItemService orderItemService;

    @Autowired
    public OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping
    public List<OrderItem> getAllOrderItem() {
        return orderItemService.getAll();
    }

    @GetMapping("/{orderItemId}")
    public OrderItem getOrderItemById(@PathVariable Long orderItemId) {
        return orderItemService.getById(orderItemId);
    }

    @PutMapping("/{orderItemId}")
    public OrderItem updateOrderItemById(@RequestBody OrderItem orderItem, @PathVariable Long orderItemId) {
        return orderItemService.updateById(orderItem, orderItemId);
    }

    @PostMapping
    public OrderItem saveOrderItem(@RequestBody OrderItem orderItem) {
        return orderItemService.save(orderItem);
    }

    @DeleteMapping("/{orderItemId}")
    public void deleteOrderItemById(@PathVariable Long orderItemId) {
        orderItemService.deleteById(orderItemId);
    }
}
