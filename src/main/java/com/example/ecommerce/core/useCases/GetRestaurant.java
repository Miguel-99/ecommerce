package com.example.ecommerce.core.useCases;

import com.example.ecommerce.domain.restaurant.Restaurant;
import com.example.ecommerce.domain.restaurant.RestaurantRepository;

public class GetRestaurant {
    private RestaurantRepository restaurantRepository;

    public GetRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant exec(Long id) {
        return restaurantRepository.getById(id);
    }
}
