package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.controllers.RestaurantController;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class HttpApplication {
    private final Javalin app = Javalin.create();
    private final Integer port;
    private final Core core;

    public HttpApplication(Integer port, Core core) {
        this.port = port;
        this.core = core;
        this.init();
    }

    private void init() {
        app.get("/", this::getProducts);
        new RestaurantController(app, core);
    }

    public void getProducts(Context ctx) {
        List<String> products = core.getProducts().exec();
        ctx.json(products);
    }

    public void start() {
        app.start(port);
    }

    public void stop() {
        app.stop();
    }
}
