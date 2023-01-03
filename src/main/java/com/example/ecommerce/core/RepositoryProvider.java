package com.example.ecommerce.core;

import com.example.ecommerce.domain.User.UserRepository;
import com.example.ecommerce.domain.product.ProductRepository;
import com.example.ecommerce.domain.restaurant.RestaurantRepository;

public interface RepositoryProvider {
    UserRepository users();
    RestaurantRepository restaurants();
    ProductRepository products();
}
