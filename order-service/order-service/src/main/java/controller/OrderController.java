package controller;

import entity.Order;
import service.OrderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // Input DTO just for the API request
    static class OrderRequest {
        public Long userId;
        public Long productId;
        public Integer quantity;
    }

    @PostMapping
    public Order createOrder(@RequestBody OrderRequest request) {
        return service.placeOrder(request.userId, request.productId, request.quantity);
    }
}