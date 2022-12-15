package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.infrastructure.InMemoryProductRepository;
import com.example.ecommerce.core.infrastructure.InMemoryRestaurantRepository;
import com.example.ecommerce.core.infrastructure.InMemoryUserRepository;
import com.example.ecommerce.domain.Product.ProductRepository;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;
import com.example.ecommerce.domain.User.UserRepository;

public class Main {
    public static void main(String[] args) throws Exception {
        ProductRepository productRepository = new InMemoryProductRepository();
        RestaurantRepository restaurantRepository = new InMemoryRestaurantRepository();
        UserRepository userRepository = new InMemoryUserRepository();

        Core core = new Core(productRepository, restaurantRepository, userRepository);
        Env env = new Env();
        Integer PORT = env.getPortOrElse(8080);
        HttpApplication httpApplication = new HttpApplication(PORT, core);
        httpApplication.start();
    }
}
