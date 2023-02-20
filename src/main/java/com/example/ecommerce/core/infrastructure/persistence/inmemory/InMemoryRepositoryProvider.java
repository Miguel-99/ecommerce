package com.example.ecommerce.core.infrastructure.persistence.inmemory;

import com.example.ecommerce.core.domain.User.UserRepository;
import com.example.ecommerce.core.domain.product.ProductRepository;
import com.example.ecommerce.core.domain.restaurant.RestaurantRepository;
import com.example.ecommerce.core.infrastructure.RepositoryProvider;

public class InMemoryRepositoryProvider implements RepositoryProvider {
    private final UserRepository userRepository = new InMemoryUserRepository();
    private final ProductRepository productRepository = new InMemoryProductRepository();
    private final RestaurantRepository restaurantRepository = new InMemoryRestaurantRepository();

    @Override
    public UserRepository users() {
        return userRepository;
    }

    @Override
    public RestaurantRepository restaurants() {
        return restaurantRepository;
    }

    @Override
    public ProductRepository products() {
        return productRepository;
    }
}
