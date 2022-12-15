package com.example.ecommerce.core;

import com.example.ecommerce.core.useCases.AddRestaurant;
import com.example.ecommerce.core.useCases.GetRestaurant;
import com.example.ecommerce.domain.Product.ProductRepository;
import com.example.ecommerce.core.useCases.GetProducts;
import com.example.ecommerce.core.useCases.GetRestaurants;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;
import com.example.ecommerce.domain.User.UserRepository;

public class Core {
    private final ProductRepository productRepository ;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public Core(ProductRepository productRepository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public GetProducts getProducts() { return new GetProducts(productRepository); }

    public GetRestaurants getRestaurants() { return new GetRestaurants(restaurantRepository, userRepository); }

    public AddRestaurant addRestaurant() { return new AddRestaurant(restaurantRepository); }

    public GetRestaurant getRstaurant() { return new GetRestaurant(restaurantRepository); }
}
