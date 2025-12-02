package service;

import client.ProductClient;
import client.UserClient;
import dto.ProductDTO;
import dto.UserDTO;
import entity.Order;
import repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository repository;
    private final ProductClient productClient; // Feign
    private final UserClient userClient;       // Feign

    public OrderService(OrderRepository repository, ProductClient productClient, UserClient userClient) {
        this.repository = repository;
        this.productClient = productClient;
        this.userClient = userClient;
    }

    public Order placeOrder(Long userId, Long productId, Integer quantity) {
        // 1. Call User Service using Feign
        UserDTO user = userClient.getUserById(userId);

        // 2. Call Product Service using Feign
        ProductDTO product = productClient.getProductById(productId);

        // 3. Create Order
        Order order = new Order();
        order.setUserId(user.getId());
        order.setUserName(user.getName()); // Proves we talked to User Service

        order.setProductId(product.getId());
        order.setProductName(product.getName()); // Proves we talked to Product Service

        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity);

        return repository.save(order);
    }
}