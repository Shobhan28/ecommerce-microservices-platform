package com.ecommerce.product_service.controller;

import com.ecommerce.product_service.dto.ApiResponse;
import com.ecommerce.product_service.dto.ProductRequest;
import com.ecommerce.product_service.model.Product;
import com.ecommerce.product_service.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> addProduct(@Valid @RequestBody ProductRequest request){
        Product saved = productService.addProduct(request);
        return ResponseEntity.ok(ApiResponse.success("Product created successfully", saved));
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<Product>>> getAllProducts(){
        return ResponseEntity.ok(ApiResponse.success(
                "Products fetched successfully", productService.getAllProducts()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.success(
                "Product fetched successfully", productService.getProductById(id)));
    }
}
