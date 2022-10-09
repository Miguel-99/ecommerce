package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.infrastructure.ProductRepository;
import com.example.ecommerce.domain.InMemoryProductRepository;
import com.example.ecommerce.domain.InMemoryRestaurantRepository;
import com.example.ecommerce.domain.RestaurantRepository;

public class Main {
    public static void main(String[] args) {
        ProductRepository productRepository = new InMemoryProductRepository();
        RestaurantRepository restaurantRepository = new InMemoryRestaurantRepository();

        Core core = new Core(productRepository, restaurantRepository);
        Env env = new Env();
        Integer PORT = env.getPortOrElse(8080);
        HttpApplication httpApplication = new HttpApplication(PORT, core);
        httpApplication.start();
    }
}
