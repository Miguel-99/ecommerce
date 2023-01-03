package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.core.RepositoryProvider;
import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.User.InvalidLoginError;
import com.example.ecommerce.domain.User.User;

public class Login {
    private final RepositoryProvider repositories;
    private final TokenGenerator tokenGenerator;

    public Login(RepositoryProvider repositories, TokenGenerator tokenGenerator) {
        this.repositories = repositories;
        this.tokenGenerator = tokenGenerator;
    }

    public String exec(Request request) throws InvalidLoginError {
        User user = repositories.users().getBy(request.username, request.password);
        String token = tokenGenerator.generate();
        user.setSessionId(token);
        return token;
    }

    public record Request(String username, String password){}
}
