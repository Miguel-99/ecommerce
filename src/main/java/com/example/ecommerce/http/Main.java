package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.infrastructure.InMemoryProductRepository;
import com.example.ecommerce.core.infrastructure.InMemoryRestaurantRepository;
import com.example.ecommerce.core.infrastructure.InMemoryUserRepository;
import com.example.ecommerce.domain.Product.ProductRepository;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;
import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.UUIDTokenGenerator;
import com.example.ecommerce.domain.User.UserRepository;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new InMemoryProductRepository();
        RestaurantRepository restaurantRepository = new InMemoryRestaurantRepository();
        UserRepository userRepository = new InMemoryUserRepository();
        TokenGenerator tokenGenerator = new UUIDTokenGenerator();

        Core core = new Core(productRepository, restaurantRepository, userRepository, tokenGenerator);
        Env env = new Env();
        Integer PORT = env.getPortOrElse(8080);
        HttpApplication httpApplication = new HttpApplication(PORT, core);
        httpApplication.start();
    }
}
