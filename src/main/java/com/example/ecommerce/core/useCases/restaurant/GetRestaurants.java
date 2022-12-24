package com.example.ecommerce.core.useCases.restaurant;

import com.example.ecommerce.domain.Restaurant.Restaurant;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;
import com.example.ecommerce.domain.User.UserRepository;

import java.util.List;

public class GetRestaurants {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public GetRestaurants(RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Response exec(String sessionId) {
        validateAuthentication(sessionId);
        List<Restaurant> restaurants = restaurantRepository.getAll();
        return new Response(restaurants);
    }

    private void validateAuthentication(String sessionId) {
        userRepository.findBySessionId(sessionId);
    }

    public record Response(List<Restaurant> restaurants) {}
}
