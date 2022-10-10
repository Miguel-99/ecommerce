package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.domain.product.ProductRepository;
import com.example.ecommerce.core.infrastructure.InMemoryProductRepository;
import com.example.ecommerce.core.infrastructure.InMemoryRestaurantRepository;
import com.example.ecommerce.domain.restaurant.RestaurantRepository;

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
