package com.example.ecommerce.core.controllers;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import org.jetbrains.annotations.NotNull;

public abstract class RequestHandler<R, TResult> implements Handler {
    private final Class<R> requestType;

    public RequestHandler(Class<R> requestType) {
        this.requestType = requestType;
    }

    @Override
    public void handle(@NotNull Context ctx) {
        R request = ctx.bodyAsClass(requestType);
        try {
            TResult result = exec(request);
            if (result != null) ctx.json(result);
        } catch (Throwable error) {
            ctx.json(new ErrorResponse(error.getMessage())).status(400);
            error.printStackTrace();
        }
    }

    public abstract TResult exec(R request) throws Throwable;

    protected record ErrorResponse(String message){}

}
