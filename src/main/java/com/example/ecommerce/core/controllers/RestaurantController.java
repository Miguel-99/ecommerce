package com.example.ecommerce.core.controllers;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.useCases.restaurant.AddRestaurant;
import com.example.ecommerce.core.useCases.restaurant.GetRestaurants;
import com.example.ecommerce.domain.restaurant.Restaurant;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.NoSuchElementException;

public class RestaurantController {
    private final Javalin app;
    private final Core core;

    public RestaurantController(Javalin javalin, Core core) {
        this.app = javalin;
        this.core = core;
        this.registerEndPoints();
    }
    private void registerEndPoints() {
        app.get("/restaurants", this::getAll);
        app.post("/restaurant", this::add);
        app.get("/restaurant/{Id}", this::getById);
    }

    private void getById(Context ctx) {
        ctx.json(core.getRestaurant().exec(Long.parseLong(ctx.pathParam("Id"))));
    }

    private void add(Context ctx) {
        AddRestaurant.Request request = ctx.bodyAsClass(AddRestaurant.Request.class);
        Restaurant restaurant = core.addRestaurant().exec(request);
        ctx.json(restaurant);
    }

    private void getAll(Context ctx) {
        String authorization = ctx.header("Authorization");
        System.out.println(authorization);
        if (authorization != null) {
            try {
                GetRestaurants.Response restaurants = core.getRestaurants().exec(authorization);
                ctx.json(restaurants).status(200);
            } catch (NoSuchElementException error) {
                ctx.json(new ErrorResponse("token incorrecto o expirado"));
            }
        }
        else {
            ctx.json(new ErrorResponse("no estás autenticado")).status(401);
        }
    }

    record ErrorResponse(String message){}

}
