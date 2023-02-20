package com.example.ecommerce.core.controllers;

import com.example.ecommerce.core.Core;
import io.javalin.Javalin;


public class AuthenticationController {
    private final Javalin app;
    private final Core core;

    public AuthenticationController(Javalin javalin, Core core) {
        this.app = javalin;
        this.core = core;
        this.registerEndPoints();
    }

    private void registerEndPoints() {
        app.post("/login", core.login());
        app.post("/signup", core.signUp());
    }
}
