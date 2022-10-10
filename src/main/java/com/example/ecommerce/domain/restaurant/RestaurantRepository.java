package com.example.ecommerce.domain.restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    Long nextId();
    List<Restaurant> getAll();
    Restaurant getById(Long id);
}
