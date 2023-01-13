package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.controllers.AuthenticationController;
import com.example.ecommerce.core.controllers.RestaurantController;
import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;

import java.time.LocalDateTime;

public class HttpApplication {
    private final Javalin app = Javalin.create(config -> {
        config.requestLogger.http((ctx, executionTimeMs) -> System.out.printf("%s %s %s at %s\n", ctx.method(), ctx.path(), ctx.status(), LocalDateTime.now()));
        config.plugins.enableCors(cors -> {
            cors.add(CorsPluginConfig::anyHost);
        });
    });
    private final Integer port;
    private final Core core;

    public HttpApplication(Integer port, Core core) {
        this.port = port;
        this.core = core;
        this.init();
    }

    private void init() {
        new RestaurantController(app, core);
        new AuthenticationController(app, core);
    }


    public void start() {
        app.start(port);
    }

    public void stop() {
        app.stop();
    }
}
