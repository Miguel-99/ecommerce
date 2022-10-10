package com.example.ecommerce.core.useCases;

import com.example.ecommerce.core.infrastructure.InMemoryRestaurantRepository;
import com.example.ecommerce.domain.restaurant.RestaurantRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GetRestaurantsTest {
    @Test
    public void getRestaurantsShouldNotThrowException() {
        Assertions.assertDoesNotThrow(() -> getRestaurants.exec());
    }

    @BeforeEach
    public void setup() {
        restaurantRepository = new InMemoryRestaurantRepository();
        getRestaurants = new GetRestaurants(restaurantRepository);
    }

    private RestaurantRepository restaurantRepository;
    private GetRestaurants getRestaurants;
}
