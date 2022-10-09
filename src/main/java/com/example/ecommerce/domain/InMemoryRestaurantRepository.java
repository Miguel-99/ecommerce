package com.example.ecommerce.domain;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRestaurantRepository implements RestaurantRepository {
    private final List<Restaurant> restaurants = new ArrayList<>();

    @Override
    public Restaurant save(Restaurant restaurant) {
        restaurants.add(restaurant);
        return restaurant;
    }

    @Override
    public Long nextId() {
        return 1L;
    }

    @Override
    public List<Restaurant> getAll() {
        return restaurants;
    }
}
