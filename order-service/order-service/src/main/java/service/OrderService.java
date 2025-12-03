package service;

import client.ProductClient;
import client.UserClient;
import dto.ProductDTO;
import dto.UserDTO;
import entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import repository.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository repository;
    private final ProductClient productClient;
    private final UserClient userClient;

    // ---------------------
    // CREATE ORDER
    // ---------------------
    public Order placeOrder(Long userId, Long productId, Integer quantity) {

        // 1. Fetch User
        UserDTO user = userClient.getUserById(userId);

        // 2. Fetch Product
        ProductDTO product = productClient.getProductById(productId);

        // 3. Build Order
        Order order = new Order();
        order.setUserId(user.getId());
        order.setUserName(user.getName());
        order.setProductId(product.getId());
        order.setProductName(product.getName());
        order.setQuantity(quantity);
        order.setTotalPrice(product.getPrice() * quantity);

        return repository.save(order);
    }

    // ---------------------
    // READ ALL
    // ---------------------
    public List<Order> getAllOrders() {
        return repository.findAll();
    }

    // ---------------------
    // READ BY ID
    // ---------------------
    public Order getOrderById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    // ---------------------
    // UPDATE ORDER
    // ---------------------
    public Order updateOrder(Long id, Order order) {
        Order existingOrder = getOrderById(id);

        existingOrder.setUserId(order.getUserId());
        existingOrder.setUserName(order.getUserName());
        existingOrder.setProductId(order.getProductId());
        existingOrder.setProductName(order.getProductName());
        existingOrder.setQuantity(order.getQuantity());
        existingOrder.setTotalPrice(order.getTotalPrice());

        return repository.save(existingOrder);
    }

    // ---------------------
    // DELETE ORDER
    // ---------------------
    public void deleteOrder(Long id) {
        repository.deleteById(id);
    }
}
