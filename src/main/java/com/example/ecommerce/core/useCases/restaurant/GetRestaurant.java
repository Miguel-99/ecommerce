package com.example.ecommerce.core.useCases.restaurant;

import com.example.ecommerce.core.RepositoryProvider;
import com.example.ecommerce.domain.Restaurant.Restaurant;

public class GetRestaurant {
    private RepositoryProvider repositories;

    public GetRestaurant(RepositoryProvider repositories) {
        this.repositories = repositories;
    }

    public Restaurant exec(Long id) {
        return repositories.restaurants().getById(id);
    }
}
