package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.User.User;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;
import com.example.ecommerce.domain.User.UserRepository;

public class SignUp {
    private final UserRepository userRepository;
    private TokenGenerator tokenGenerator;

    public SignUp(UserRepository userRepository, TokenGenerator tokenGenerator) {
        this.userRepository = userRepository;
        this.tokenGenerator = tokenGenerator;
    }

    public String exec(String username, String password) throws UserAlreadyExistsError {
        User user = new User(userRepository.nextId(), username, password);
        String token = tokenGenerator.generate();
        user.setSessionId(token);
        userRepository.save(user);
        return token;
    }
}
