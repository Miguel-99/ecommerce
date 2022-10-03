package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;
import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.List;

public class HttpApplication {
    Javalin app = Javalin.create();
    private Integer port;
    private Core core;

    {
        app.get("/", this::getProducts);
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
}
