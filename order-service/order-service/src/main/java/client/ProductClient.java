package client;

import dto.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// "url" tells it exactly where the Product Service lives
@FeignClient(name = "product-service", url = "http://localhost:8081")
public interface ProductClient {
    // This must match the Controller in Product Service exactly!
    @GetMapping("/products/{id}")
    ProductDTO getProductById(@PathVariable("id") Long id);
}
