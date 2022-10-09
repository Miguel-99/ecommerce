package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;

public class Main {
    public static void main(String[] args) {
        Core core = new Core();
        Env env = new Env();
        Integer PORT = env.getPortOrElse(8080);
        HttpApplication httpApplication = new HttpApplication(PORT, core);
        httpApplication.start();
    }
}
