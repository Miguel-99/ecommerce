package com.example.ecommerce.core.useCases;

import com.example.ecommerce.domain.restaurant.Restaurant;
import com.example.ecommerce.domain.restaurant.RestaurantRepository;

import java.util.List;

public class GetRestaurants {
    private final RestaurantRepository restaurantRepository;

    public GetRestaurants(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Response exec() {
        List<Restaurant> restaurants = restaurantRepository.getAll();
        return new Response(restaurants);
    }

    public record Response(List<Restaurant> restaurants) {}
}
