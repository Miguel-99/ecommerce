package com.example.ecommerce.core.controllers;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.useCases.authentication.Login;
import com.example.ecommerce.domain.User.InvalidLoginError;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;
import io.javalin.Javalin;
import io.javalin.http.Context;

import static com.example.ecommerce.core.useCases.authentication.SignUp.Request;


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
        Login.Request request = ctx.bodyAsClass(Login.Request.class);
        try {
            String token = core.login().exec(request);
            ctx.json(new Response(token));
        } catch (InvalidLoginError error) {
            ctx.json(new ErrorResponse(error.getMessage())).status(400);
        }
    }

    private void signUp(Context ctx) {
        Request request = ctx.bodyAsClass(Request.class);
        try {
            String token = core.signUp().exec(request);
            ctx.json(new Response(token));
        } catch (UserAlreadyExistsError error) {
            ctx.json(new ErrorResponse("nombre de usuario ya existe")).status(400);
        }
    }

    record ErrorResponse(String message){}
    record Response(String token){}
}
