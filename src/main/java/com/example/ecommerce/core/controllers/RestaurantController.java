package com.example.ecommerce.core.controllers;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.useCases.AddRestaurant;
import com.example.ecommerce.core.useCases.GetRestaurants;
import com.example.ecommerce.domain.Restaurant.Restaurant;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class RestaurantController {
    private final Javalin app;
    private final Core core;

    public RestaurantController(Javalin javalin, Core core) {
        this.app = javalin;
        this.core = core;
        this.registerEndPoints();
    }
    private void registerEndPoints() {
        app.get("/restaurant", this::getAll);
        app.post("/restaurant", this::add);
        app.get("/restaurant/{Id}", this::getById);
    }

    private void getById(Context ctx) {
        ctx.json(core.getRstaurant().exec(Long.parseLong(ctx.pathParam("Id"))));
    }

    private void add(Context ctx) {
        AddRestaurant.Request request = ctx.bodyAsClass(AddRestaurant.Request.class);
        Restaurant restaurant = core.addRestaurant().exec(request);
        ctx.json(restaurant);
    }

    private void getAll(Context ctx) {
        GetRestaurants.Response restaurants = core.getRestaurants().exec();
        ctx.json(restaurants);
    }
}
