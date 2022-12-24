package com.example.ecommerce.core.useCases.restaurant;

import com.example.ecommerce.domain.Restaurant.Restaurant;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;

public class GetRestaurant {
    private RestaurantRepository restaurantRepository;

    public GetRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant exec(Long id) {
        return restaurantRepository.getById(id);
    }
}
