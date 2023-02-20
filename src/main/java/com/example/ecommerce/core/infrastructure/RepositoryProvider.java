package com.example.ecommerce.core.infrastructure;

import com.example.ecommerce.core.domain.User.UserRepository;
import com.example.ecommerce.core.domain.product.ProductRepository;
import com.example.ecommerce.core.domain.restaurant.RestaurantRepository;

public interface RepositoryProvider {
    UserRepository users();
    RestaurantRepository restaurants();
    ProductRepository products();
}
