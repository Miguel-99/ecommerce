package com.example.ecommerce.http;

public class Env {
    public Env() {
    }

    public Integer getPortOrElse(Integer defaultValue) {
        return System.getenv("PORT") == null ? defaultValue : Integer.parseInt(System.getenv("PORT"));
    }
}
