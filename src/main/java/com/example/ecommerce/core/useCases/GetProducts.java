package com.example.ecommerce.core.useCases;

import com.example.ecommerce.core.infrastructure.ProductRepository;

import java.util.List;

public class GetProducts {
    private final ProductRepository productRepository;

    public GetProducts(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<String> exec() {
        return productRepository.getAll();
    }
}
