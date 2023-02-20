package com.example.ecommerce.core.infrastructure.persistence.inmemory;

import com.example.ecommerce.core.domain.product.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryProductRepository implements ProductRepository {

    private final List<String> products = new ArrayList<>();

    @Override
    public List<String> getAll() { return products; }
}
