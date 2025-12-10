package com.example.order.service;

import com.example.order.client.ProductClient;
import com.example.order.client.UserClient;
import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository repository;
    private final ProductClient productClient;
    private final UserClient userClient;

    // Helper method to keep code DRY (Don't Repeat Yourself) and prevent price bugs
    private void updateOrderDetails(Order order, Long userId, Long productId, Integer quantity) {
        var user = userClient.getUserById(userId);
        var product = productClient.getProductById(productId);
        order.setUserId(user.getId());
        order.setUserName(user.getName());
        order.setProductId(product.getId());
        order.setProductName(product.getName());
        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity); // Auto-calculate price
    }

    public Order placeOrder(Long userId, Long productId, Integer quantity) {
        Order order = new Order();
        updateOrderDetails(order, userId, productId, quantity);
        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    public Order getOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new jakarta.persistence.EntityNotFoundException("Order not found: " + id));
    }

    public Order updateOrder(Long id, Order request) {
        Order existingOrder = getOrderById(id);
        updateOrderDetails(existingOrder, request.getUserId(), request.getProductId(), request.getQuantity());
        return repository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        if (!repository.existsById(id)) {
            throw new jakarta.persistence.EntityNotFoundException("Cannot delete. Order not found: " + id);
        }
        repository.deleteById(id);
    }
}