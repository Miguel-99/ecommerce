package com.example.ecommerce.core.useCases;

import com.example.ecommerce.core.infrastructure.InMemoryRestaurantRepository;
import com.example.ecommerce.core.infrastructure.InMemoryUserRepository;
import com.example.ecommerce.core.useCases.restaurant.GetRestaurants;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;
import com.example.ecommerce.domain.User.User;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;
import com.example.ecommerce.domain.User.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetRestaurantsTest {
    @Test
    public void getRestaurantsShouldNotThrowException() throws UserAlreadyExistsError {
        User user = userRepository.save(new User(userRepository.nextId(), "some username", "some password"));
        user.setSessionId("some session");

        Assertions.assertDoesNotThrow(() -> {
            getRestaurants.exec(user.getSessionId());
        });
    }

    @Test
    public void getRestaurantsThrowExceptionIfNotAuthenticated() {
        assertThrows(NoSuchElementException.class, () -> getRestaurants.exec("unexistentSessionId"));
    }

    @BeforeEach
    public void setup() {
        userRepository = new InMemoryUserRepository();
        restaurantRepository = new InMemoryRestaurantRepository();
        getRestaurants = new GetRestaurants(restaurantRepository, userRepository);
    }

    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private GetRestaurants getRestaurants;
}
