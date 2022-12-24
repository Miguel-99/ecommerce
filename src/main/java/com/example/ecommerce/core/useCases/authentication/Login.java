package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.User.InvalidLoginError;
import com.example.ecommerce.domain.User.User;
import com.example.ecommerce.domain.User.UserRepository;

public class Login {
    private final UserRepository userRepository;
    private final TokenGenerator tokenGenerator;

    public Login(UserRepository userRepository, TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
    }

    public String exec(Request request) throws InvalidLoginError {
        User user = userRepository.getBy(request.username, request.password);
        String token = tokenGenerator.generate();
        user.setSessionId(token);
        return token;
    }
    public record Request(String username, String password){};
}
