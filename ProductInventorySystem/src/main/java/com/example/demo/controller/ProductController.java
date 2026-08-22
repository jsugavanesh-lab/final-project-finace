package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Product;

@RestController
public class ProductController {

    private List<Product> products = new ArrayList<>();

    @PostMapping("/products")
    public Product createProduct(@RequestBody @Valid Product product) {

        products.add(product);

        return product;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {

        return products;
    }

    @GetMapping("/products/{productId}")
    public Product getProductById(@PathVariable Long productId) {

        for (Product product : products) {

            if (product.getProductId().equals(productId)) {

                return product;
            }
        }

        return null;
    }

    @PutMapping("/products/{productId}")
    public Product updateProduct(
            @PathVariable Long productId,
            @RequestBody Product updatedProduct) {

        for (Product product : products) {

            if (product.getProductId().equals(productId)) {

                product.setProductName(updatedProduct.getProductName());
                product.setCategory(updatedProduct.getCategory());
                product.setStock(updatedProduct.getStock());
                product.setPrice(updatedProduct.getPrice());

                return product;
            }
        }

        return null;
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable Long productId) {

        for (Product product : products) {

            if (product.getProductId().equals(productId)) {

                products.remove(product);

                return "Product deleted successfully";
            }
        }

        return "Product not found";
    }
}