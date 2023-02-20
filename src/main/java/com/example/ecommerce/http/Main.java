package com.example.ecommerce.http;

import com.example.ecommerce.core.Core;
import com.example.ecommerce.core.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.core.infrastructure.RepositoryProvider;
import com.example.ecommerce.core.infrastructure.persistence.inmemory.InMemoryRepositoryProvider;
import com.example.ecommerce.core.infrastructure.token.UUIDTokenGenerator;

public class Main {
    public static void main(String[] args) {
        RepositoryProvider repositoryProvider = new InMemoryRepositoryProvider();
        TokenGenerator tokenGenerator = new UUIDTokenGenerator();
        Core core = new Core(repositoryProvider, tokenGenerator);
        Env env = new Env();
        Integer PORT = env.getPortOrElse(8080);

        HttpApplication httpApplication = new HttpApplication(PORT, core);
        httpApplication.start();
    }
}
