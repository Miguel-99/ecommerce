package com.example.ecommerce.domain.Restaurant;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    Long nextId();
    List<Restaurant> getAll();
    Restaurant getById(Long id);
}
