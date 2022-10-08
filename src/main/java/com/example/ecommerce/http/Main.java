package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;

public class Main {
    public static void main(String[] args) {
        Core core = new Core();
        HttpApplication httpApplication = new HttpApplication(Integer.valueOf(System.getenv("PORT")), core);
        httpApplication.start();
    }
}
