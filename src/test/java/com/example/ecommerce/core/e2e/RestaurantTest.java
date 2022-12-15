package com.example.ecommerce.core.e2e;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.infrastructure.InMemoryProductRepository;
import com.example.ecommerce.core.infrastructure.InMemoryRestaurantRepository;
import com.example.ecommerce.core.infrastructure.InMemoryUserRepository;
import com.example.ecommerce.domain.Product.ProductRepository;
import com.example.ecommerce.domain.Restaurant.Restaurant;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;
import com.example.ecommerce.domain.User.UserRepository;
import com.example.ecommerce.http.Env;
import com.example.ecommerce.http.HttpApplication;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class RestaurantTest {

    @Test
    public void noRestaurantsRegisteredShouldReturnEmptyList() {
        when()
                .get("/restaurant")
        .then()
                .statusCode(200)
                .body("restaurants.size()", equalTo(0));
    }

    @Test
    public void shouldReturnRestaurantsRegistered() {
        restaurantRepository.save(someRestaurant);

        when()
                .get("/restaurant")
        .then()
                .statusCode(200)
                .body("restaurants.size()", equalTo(1));
    }

    @Test
    public void shouldReturnRestaurants() {
        Restaurant someRestaurant2 = new Restaurant(restaurantRepository.nextId(), "someName2", "asd 123", "123");

        restaurantRepository.save(someRestaurant);
        restaurantRepository.save(someRestaurant2);

        when()
                .get("/restaurant")
        .then()
                .statusCode(200)
                .body("restaurants.size()", equalTo(2))
                .body("restaurants[1].name", equalTo("someName2"))
                .body("restaurants[0].name", equalTo("someName"));
    }

    @Test
    public void shouldReturnSpecificRestaurantById() {
        restaurantRepository.save(someRestaurant);

        when()
                .get("/restaurant/1")
        .then()
                .statusCode(200)
                .body("name", equalTo("someName"))
                .body("id", equalTo(1));
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
    UserRepository userRepository = new InMemoryUserRepository();
    Core core = new Core(productRepository, restaurantRepository, userRepository);
    Env env = new Env();
    Integer PORT = env.getPortOrElse(8080);
    HttpApplication httpApplication = new HttpApplication(PORT, core);
    Restaurant someRestaurant = new Restaurant(restaurantRepository.nextId(), "someName", "someAddress 123", "somePhone123");
}
