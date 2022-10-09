package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.useCases.GetRestaurants;
import com.example.ecommerce.domain.Restaurant;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class HttpApplication {
    Javalin app = Javalin.create();
    private Integer port;
    private Core core;

    {
        app.get("/", this::getProducts);
        app.get("/restaurant", this::getRestaurants);
    }

    private void getRestaurants(Context ctx) {
        GetRestaurants.Response restaurants = core.getRestaurants().exec();
        ctx.json(restaurants);
    }

    public void getProducts(Context ctx) {
        List<String> products = core.getProducts().exec();
        ctx.json(products);
    }

    public HttpApplication(Integer port, Core core) {
        this.port = port;
        this.core = core;
    }

    public void start() {
        app.start(port);
    }

    public void stop() {
        app.stop();
    }
}
