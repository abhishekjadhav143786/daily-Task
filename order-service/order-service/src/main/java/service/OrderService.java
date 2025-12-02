package service;

import client.ProductClient;
import client.UserClient;
import dto.ProductDTO;
import dto.UserDTO;
import entity.Order;
import repository.OrderRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor // Injects Repository and Clients automatically
public class OrderService {

    private final OrderRepository repository;
    private final ProductClient productClient;
    private final UserClient userClient;

    public Order placeOrder(Long userId, Long productId, Integer quantity) {
        // 1. Fetch User
        UserDTO user = userClient.getUserById(userId);

        // 2. Fetch Product
        ProductDTO product = productClient.getProductById(productId);

        // 3. Create & Save Order
        Order order = new Order();
        order.setUserId(user.getId());
        order.setUserName(user.getName());
        order.setProductId(product.getId());
        order.setProductName(product.getName());
        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity);

        return repository.save(order);
    }
}

