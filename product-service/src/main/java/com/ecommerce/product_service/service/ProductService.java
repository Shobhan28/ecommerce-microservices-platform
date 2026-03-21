package com.ecommerce.product_service.service;

import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.exception.ResourceNotFoundException;
import com.ecommerce.product_service.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductService {
    private final Map<Long, Product> productStore = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public Product addProduct(ProductRequest request) {
        Long id = idGenerator.getAndIncrement();
        Product product = new Product(id, request.getName(), request.getPrice());
        productStore.put(id, product);
        return product;
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productStore.values());
    }

    public Product getProductById(Long id) {
        Product product = productStore.get(id);
        if (product == null) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        return product;
    }
}
