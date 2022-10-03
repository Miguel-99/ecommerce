package com.example.ecommerce.core;

import com.example.ecommerce.core.infrastructure.ProductRepository;
import com.example.ecommerce.core.useCases.GetProducts;
import com.example.ecommerce.domain.InMemoryProductRepository;

public class Core {
    private final ProductRepository productRepository = new InMemoryProductRepository();

    public GetProducts getProducts() { return new GetProducts(productRepository); }
}
