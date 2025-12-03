package com.example.order.service;

import com.example.order.client.ProductClient;
import com.example.order.client.UserClient;
import com.example.order.dto.ProductDTO;
import com.example.order.dto.UserDTO;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ProductClient productClient;
    private final UserClient userClient;

    // CREATE ORDER
    public Order placeOrder(Long userId, Long productId, Integer quantity) {

        UserDTO user = userClient.getUserById(userId);
        ProductDTO product = productClient.getProductById(productId);

        Order order = Order.builder()
                .userId(user.getId())
                .userName(user.getName())
                .productId(product.getId())
                .productName(product.getName())
                .quantity(quantity)
                .totalPrice(product.getPrice() * quantity)
                .build();

        return repository.save(order);
    }

    // READ ALL
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    // READ BY ID
    public Order getOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
    }

    // UPDATE
    public Order updateOrder(Long id, Order updated) {
        Order existing = getOrderById(id);
        existing.setUserId(updated.getUserId());
        existing.setUserName(updated.getUserName());
        existing.setProductId(updated.getProductId());
        existing.setProductName(updated.getProductName());
        existing.setQuantity(updated.getQuantity());
        existing.setTotalPrice(updated.getTotalPrice());
        return repository.save(existing);
    }

    // DELETE
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}
