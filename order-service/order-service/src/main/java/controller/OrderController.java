package controller;

import entity.Order;
import service.OrderService;
import org.springframework.web.bind.annotation.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor // Injects Service automatically
public class OrderController {

    private final OrderService service;

    // Static DTO for Request Body
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
}

