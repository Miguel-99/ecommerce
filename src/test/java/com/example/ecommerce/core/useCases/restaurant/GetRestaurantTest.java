package com.example.ecommerce.core.useCases.restaurant;

import com.example.ecommerce.core.domain.restaurant.Restaurant;
import com.example.ecommerce.core.infrastructure.RepositoryProvider;
import com.example.ecommerce.core.infrastructure.persistence.inmemory.InMemoryRepositoryProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetRestaurantTest {

    @Test
    public void getRestaurantByIdShouldNotFail() {
        Restaurant restaurant = new Restaurant(repositories.restaurants().nextId(), "restaurant01", "av123", "123456");
        repositories.restaurants().save(restaurant);
        assertDoesNotThrow(() -> getRestaurant.exec(1L));
    }

    @Test
    public void failsIfRestaurantDoesNotExist() {
        assertThrows(NoSuchElementException.class, () -> getRestaurant.exec(1L));
    }

    @BeforeEach
    public void setup() {
        getRestaurant = new GetRestaurant(repositories);
    }

    private GetRestaurant getRestaurant;
    private final RepositoryProvider repositories = new InMemoryRepositoryProvider();

}
