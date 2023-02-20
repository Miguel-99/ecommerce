package com.example.ecommerce.core.useCases.restaurant;

import com.example.ecommerce.core.domain.restaurant.Restaurant;
import com.example.ecommerce.core.infrastructure.RepositoryProvider;

public class GetRestaurant {
    private final RepositoryProvider repositories;

    public GetRestaurant(RepositoryProvider repositories) {
        this.repositories = repositories;
    }

    public Restaurant exec(Long id) {
        return repositories.restaurants().getById(id);
    }
}
