package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.core.RepositoryProvider;
import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.User.User;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;

public class SignUp {
    private final RepositoryProvider repositories;
    private final TokenGenerator tokenGenerator;

    public SignUp(RepositoryProvider repositories, TokenGenerator tokenGenerator) {
        this.repositories = repositories;
        this.tokenGenerator = tokenGenerator;
    }

    public String exec(Request request) throws UserAlreadyExistsError {
        User user = new User(repositories.users().nextId(), request.username, request.password);
        String token = tokenGenerator.generate();
        user.setSessionId(token);
        repositories.users().save(user);
        return token;
    }

    public record Request(String username, String password){}
}
