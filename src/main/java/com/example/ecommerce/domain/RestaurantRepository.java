package com.example.ecommerce.domain;

import java.util.List;

public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    Long nextId();
    List<Restaurant> getAll();
}
