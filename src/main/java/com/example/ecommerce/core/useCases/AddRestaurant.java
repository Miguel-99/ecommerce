package com.example.ecommerce.core.useCases;

import com.example.ecommerce.domain.Restaurant.Restaurant;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;

public class AddRestaurant {
    private RestaurantRepository restaurantRepository;

    public AddRestaurant(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant exec(Request requestRestaurant) {
        if(isInvalidRequest(requestRestaurant)) throw new IllegalArgumentException("Invalid Request");
        Restaurant restaurant = new Restaurant(restaurantRepository.nextId(), requestRestaurant.name, requestRestaurant.address, requestRestaurant.phoneNumber);
        return restaurantRepository.save(restaurant);
    }

    private Boolean isInvalidRequest(Request request) {
        return isFieldEmptyOrNull(request.address) || isFieldEmptyOrNull(request.name) || isFieldEmptyOrNull(request.phoneNumber);
    }

    private Boolean isFieldEmptyOrNull(String field) {
        return field == null || field.isBlank();
    }

    public record Request(String name, String address, String phoneNumber) {}
}
