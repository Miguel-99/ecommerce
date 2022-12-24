package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.core.domain.TokenGenerator.StubbedTokenGenerator;
import com.example.ecommerce.core.infrastructure.InMemoryUserRepository;
import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.User.InvalidLoginError;
import com.example.ecommerce.domain.User.User;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;
import com.example.ecommerce.domain.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoginTest {
    private static final String TOKEN_TEST = "token Test";

    @Test
    public void loginOk() throws InvalidLoginError, UserAlreadyExistsError {
        Login.Request request = new Login.Request("user123", "pass123");
        userRepository.save(new User(userRepository.nextId(), "user123", "pass123"));

        String token = login.exec(request);

        assertEquals(token, TOKEN_TEST);
    }

    @Test
    public void failIfUsernameOrPasswordIncorrect() throws UserAlreadyExistsError{
        Login.Request request = new Login.Request("incorrectUserName", "pass123");
        userRepository.save(new User(userRepository.nextId(), "user123", "pass123"));

        assertThrows(InvalidLoginError.class, () -> login.exec(request));
    }


    @BeforeEach
    public void setup(){
        userRepository = new InMemoryUserRepository();
        tokenGenerator = new StubbedTokenGenerator(TOKEN_TEST);
        login = new Login(userRepository, tokenGenerator);
    }

    private Login login;
    private UserRepository userRepository;
    private TokenGenerator tokenGenerator;
}
