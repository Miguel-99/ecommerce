package com.example.ecommerce.core.useCases;

import com.example.ecommerce.core.infrastructure.InMemoryRestaurantRepository;
import com.example.ecommerce.core.useCases.restaurant.AddRestaurant;
import com.example.ecommerce.core.useCases.restaurant.AddRestaurant.Request;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
        restaurantRepository = new InMemoryRestaurantRepository();
        addRestaurant = new AddRestaurant(restaurantRepository);
    }

    private RestaurantRepository restaurantRepository;
    private AddRestaurant addRestaurant;
}
