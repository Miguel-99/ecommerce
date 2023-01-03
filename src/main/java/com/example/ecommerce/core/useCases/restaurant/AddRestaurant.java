package com.example.ecommerce.core.useCases.restaurant;

import com.example.ecommerce.core.RepositoryProvider;
import com.example.ecommerce.domain.Restaurant.Restaurant;

public class AddRestaurant {
    private final RepositoryProvider repositories;

    public AddRestaurant(RepositoryProvider repositories) {
        this.repositories = repositories;
    }

    public Restaurant exec(Request requestRestaurant) {
        if(isInvalidRequest(requestRestaurant)) throw new IllegalArgumentException("Invalid Request");
        Restaurant restaurant = new Restaurant(repositories.restaurants().nextId(), requestRestaurant.name, requestRestaurant.address, requestRestaurant.phoneNumber);
        return repositories.restaurants().save(restaurant);
    }

    private Boolean isInvalidRequest(Request request) {
        return isFieldEmptyOrNull(request.address) || isFieldEmptyOrNull(request.name) || isFieldEmptyOrNull(request.phoneNumber);
    }

    private Boolean isFieldEmptyOrNull(String field) {
        return field == null || field.isBlank();
    }

    public record Request(String name, String address, String phoneNumber) {}
}
