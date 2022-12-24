package com.example.ecommerce.core.controllers;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.domain.User.InvalidLoginError;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class AuthenticationController {
    private final Javalin app;
    private final Core core;

    public AuthenticationController(Javalin javalin, Core core) {
        this.app = javalin;
        this.core = core;
        this.registerEndPoints();
    }
    private void registerEndPoints() {
        app.post("/signup", this::signUp);
        app.post("/login", this::login);
    }

    private void login(Context ctx) {
        Request request = ctx.bodyAsClass(Request.class);
        try {
            String token = core.login().exec(request.username, request.password);
            ctx.json(new Response(token));
        } catch (InvalidLoginError error) {
            ctx.json(new ErrorResponse(error.getMessage())).status(400);
        }
    }

    private void signUp(Context ctx) {
        Request request = ctx.bodyAsClass(Request.class);
        try {
            String token = core.signUp().exec(request.username, request.password);
            ctx.json(new Response(token));
        } catch (UserAlreadyExistsError error) {
            ctx.json(new ErrorResponse("nombre de usuario ya existe"));
        }
    }

    record ErrorResponse(String message){}
    record Request(String username, String password){}
    record Response(String token){}
}
