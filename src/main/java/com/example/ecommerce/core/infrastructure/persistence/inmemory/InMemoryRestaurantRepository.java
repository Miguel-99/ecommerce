package com.example.ecommerce.core.infrastructure.persistence.inmemory;

import com.example.ecommerce.domain.Restaurant.Restaurant;
import com.example.ecommerce.domain.Restaurant.RestaurantRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRestaurantRepository implements RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();
    private Long id = 1L;

    @Override
    public Restaurant save(Restaurant restaurant) {
        restaurants.add(restaurant);
        return restaurant;
    }

    @Override
    public Long nextId() { return id++; }

    @Override
    public List<Restaurant> getAll() {
        return restaurants;
    }

    @Override
    public Restaurant getById(Long id) {
        return restaurants
                .stream()
                .filter(restaurant -> restaurant.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }
}
