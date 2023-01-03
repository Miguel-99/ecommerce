package com.example.ecommerce.core.useCases;

import com.example.ecommerce.core.RepositoryProvider;
import com.example.ecommerce.core.infrastructure.persistence.inmemory.InMemoryRepositoryProvider;
import com.example.ecommerce.core.useCases.restaurant.AddRestaurant;
import com.example.ecommerce.core.useCases.restaurant.AddRestaurant.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddRestaurantTest {

    @Test
    public void addRestaurantShouldNotThrowException(){
        Request request = new Request("restaurant01", "av123", "123456");
        assertDoesNotThrow(() -> addRestaurant.exec(request));
    }

    @Test
    public void failsIfRequestIsInvalid(){
        Request request = new Request("restaurant01", null, "");
        assertThrows(IllegalArgumentException.class, () -> addRestaurant.exec(request));
    }

    @BeforeEach
    public void setup() {
        addRestaurant = new AddRestaurant(repositories);
    }

    private RepositoryProvider repositories = new InMemoryRepositoryProvider();
    private AddRestaurant addRestaurant;
}
