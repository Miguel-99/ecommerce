package com.example.ecommerce.core.e2e;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.RepositoryProvider;
import com.example.ecommerce.core.infrastructure.persistence.inmemory.InMemoryRepositoryProvider;
import com.example.ecommerce.core.infrastructure.token.UUIDTokenGenerator;
import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.User.User;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;
import com.example.ecommerce.domain.restaurant.Restaurant;
import com.example.ecommerce.http.Env;
import com.example.ecommerce.http.HttpApplication;
import io.restassured.http.Header;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class RestaurantTest {
    @Test
    public void shouldReturnRestaurants() {
        Restaurant someRestaurant2 = new Restaurant(repositories.restaurants().nextId(), "someName2", "asd 123", "123");
        repositories.restaurants().save(someRestaurant);
        repositories.restaurants().save(someRestaurant2);

        given()
                .header(new Header("Authorization", user.getSessionId()))
        .when()
                .get("/restaurants")
        .then()
                .statusCode(200)
                .body("restaurants.size()", equalTo(2))
                .body("restaurants[1].name", equalTo("someName2"))
                .body("restaurants[0].name", equalTo("someName"));
    }

    @Test
    public void shouldReturnSpecificRestaurantById() {
        repositories.restaurants().save(someRestaurant);
        when()
                .get("/restaurant/1")
        .then()
                .statusCode(200)
                .body("name", equalTo("someName"))
                .body("id", equalTo(1));
    }

    @BeforeEach
    void setUp() throws UserAlreadyExistsError {
        httpApplication.start();

        user.setSessionId("sessionToken");
        repositories.users().save(user);
    }

    @AfterEach
    void tearDown() {
        httpApplication.stop();
    }

    private RepositoryProvider repositories = new InMemoryRepositoryProvider();
    private TokenGenerator tokenGenerator = new UUIDTokenGenerator();
    private Core core = new Core(repositories, tokenGenerator);
    private Env env = new Env();
    private Integer PORT = env.getPortOrElse(8080);
    private HttpApplication httpApplication = new HttpApplication(PORT, core);
    private Restaurant someRestaurant = new Restaurant(repositories.restaurants().nextId(), "someName", "someAddress 123", "somePhone123");
    private User user = new User(repositories.users().nextId(), "someUsername", "somePassword");

}
