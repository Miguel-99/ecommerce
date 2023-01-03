package com.example.ecommerce.core;

import com.example.ecommerce.domain.Product.ProductRepository;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;
import com.example.ecommerce.domain.User.UserRepository;

public interface RepositoryProvider {
    UserRepository users();
    RestaurantRepository restaurants();
    ProductRepository products();
}
