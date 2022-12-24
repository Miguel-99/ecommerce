package com.example.ecommerce.core.useCases;

import com.example.ecommerce.core.infrastructure.InMemoryRestaurantRepository;
import com.example.ecommerce.core.useCases.restaurant.GetRestaurant;
import com.example.ecommerce.domain.Restaurant.Restaurant;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetRestaurantTest {

    @Test
    public void getRestaurantByIdShouldNotFail() {
        Restaurant restaurant = new Restaurant(restaurantRepository.nextId(), "restaurant01", "av123", "123456");
        restaurantRepository.save(restaurant);
        assertDoesNotThrow(() -> getRestaurant.exec(1L));
    }

    @Test
    public void failsIfRestaurantDoesNotExist() {
        assertThrows(NoSuchElementException.class, () -> getRestaurant.exec(1L));
    }

    @BeforeEach
    public void setup() {
        restaurantRepository = new InMemoryRestaurantRepository();
        getRestaurant = new GetRestaurant(restaurantRepository);
    }

    private RestaurantRepository restaurantRepository;
    private GetRestaurant getRestaurant;
}
