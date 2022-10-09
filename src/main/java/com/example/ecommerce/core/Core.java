package com.example.ecommerce.core;

import com.example.ecommerce.core.infrastructure.ProductRepository;
import com.example.ecommerce.core.useCases.GetProducts;
import com.example.ecommerce.core.useCases.GetRestaurants;
import com.example.ecommerce.domain.RestaurantRepository;

public class Core {
    private final ProductRepository productRepository ;
    private final RestaurantRepository restaurantRepository;

    public Core(ProductRepository productRepository, RestaurantRepository restaurantRepository) {
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public GetProducts getProducts() { return new GetProducts(productRepository); }

    public GetRestaurants getRestaurants() { return new GetRestaurants(restaurantRepository); }
}
