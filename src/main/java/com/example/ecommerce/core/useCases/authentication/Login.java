package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.core.controllers.RequestHandler;
import com.example.ecommerce.core.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.core.domain.User.InvalidLoginError;
import com.example.ecommerce.core.domain.User.User;
import com.example.ecommerce.core.infrastructure.RepositoryProvider;

import static com.example.ecommerce.core.useCases.authentication.Login.Request;
import static com.example.ecommerce.core.useCases.authentication.Login.Response;


public class Login extends RequestHandler<Request, Response> {
    private final RepositoryProvider repositories;
    private final TokenGenerator tokenGenerator;

    protected record Request(String username, String password){}
    protected record Response(String token){}

    @Override
    public Response exec(Request request) throws InvalidLoginError {
        User user = repositories.users().getBy(request.username, request.password);
        String token = tokenGenerator.generate();
        setUserSession(user, token);
        return new Response(token);
    }

    private void setUserSession(User user, String token) {
        user.setSessionId(token);
        repositories.users().update(user);
    }

    public Login(RepositoryProvider repositories, TokenGenerator tokenGenerator) {
        super(Request.class);
        this.repositories = repositories;
        this.tokenGenerator = tokenGenerator;
    }
}
