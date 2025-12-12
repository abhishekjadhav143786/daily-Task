package com.example.order.controller;

import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import org.springframework.web.bind.annotation.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor // Injects Service automatically
public class OrderController {

    private final OrderService service;

    @Data
    static class OrderRequest {
        public Long userId;
        public Long productId;
        public Integer quantity;
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request) {
        return service.placeOrder(request.userId, request.productId, request.quantity);
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return service.getOrderById(id);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        return service.updateOrder(id, order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        service.deleteOrder(id);
    }
}


