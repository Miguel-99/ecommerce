package com.example.ecommerce.core.controllers;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.infrastructure.ProductRepository;
import com.example.ecommerce.domain.InMemoryProductRepository;
import com.example.ecommerce.domain.InMemoryRestaurantRepository;
import com.example.ecommerce.domain.Restaurant;
import com.example.ecommerce.domain.RestaurantRepository;
import com.example.ecommerce.http.Env;
import com.example.ecommerce.http.HttpApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class RestaurantTest {

    @Test
    public void no_restaurants_registered_should_return_empty_list() {
        when()
                .get("/restaurant")
        .then()
                .statusCode(200)
                .body("restaurants.size()", equalTo(0));
    }

    @Test
    public void should_return_restaurants_registered() {
        Restaurant someRestaurant = new Restaurant(restaurantRepository.nextId(), "someName", "asd 123", "123");
        restaurantRepository.save(someRestaurant);

        when()
                .get("/restaurant")
        .then()
                .statusCode(200)
                .body("restaurants.size()", equalTo(1));
    }

    @BeforeEach
    void setUp() {
        httpApplication.start();
    }

    @AfterEach
    void tearDown() {
        httpApplication.stop();

    }

    ProductRepository productRepository = new InMemoryProductRepository();
    RestaurantRepository restaurantRepository = new InMemoryRestaurantRepository();
    Core core = new Core(productRepository, restaurantRepository);
    Env env = new Env();
    Integer PORT = env.getPortOrElse(8080);
    HttpApplication httpApplication = new HttpApplication(PORT, core);
}
