package com.example.ecommerce.core.useCases.restaurant;

import com.example.ecommerce.core.domain.restaurant.Restaurant;
import com.example.ecommerce.core.infrastructure.RepositoryProvider;

import java.util.List;

public class GetRestaurants {
    private final RepositoryProvider repositories;

    public GetRestaurants(RepositoryProvider repositories) {
        this.repositories = repositories;
    }

    public Response exec(String sessionId) {
        validateAuthentication(sessionId);
        List<Restaurant> restaurants = repositories.restaurants().getAll();
        return new Response(restaurants);
    }

    private void validateAuthentication(String sessionId) {
        repositories.users().findBySessionId(sessionId);
    }

    public record Response(List<Restaurant> restaurants) {}
}
