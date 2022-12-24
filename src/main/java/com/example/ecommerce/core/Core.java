package com.example.ecommerce.core;

import com.example.ecommerce.core.useCases.authentication.Login;
import com.example.ecommerce.core.useCases.authentication.SignUp;
import com.example.ecommerce.core.useCases.restaurant.AddRestaurant;
import com.example.ecommerce.core.useCases.restaurant.GetRestaurant;
import com.example.ecommerce.core.useCases.restaurant.GetRestaurants;
import com.example.ecommerce.domain.Product.ProductRepository;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;
import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.User.UserRepository;

public class Core {
    private final ProductRepository productRepository ;
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;

    public Core(ProductRepository productRepository, RestaurantRepository restaurantRepository, UserRepository userRepository, TokenGenerator tokenGenerator) {
        this.productRepository = productRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
    }

    public GetRestaurants getRestaurants() { return new GetRestaurants(restaurantRepository, userRepository); }

    public AddRestaurant addRestaurant() { return new AddRestaurant(restaurantRepository); }

    public GetRestaurant getRstaurant() { return new GetRestaurant(restaurantRepository); }

    public SignUp signUp() { return new SignUp(userRepository, tokenGenerator); }

    public Login login() { return new Login(userRepository, tokenGenerator); }
}
