package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.core.RepositoryProvider;
import com.example.ecommerce.core.domain.TokenGenerator.StubbedTokenGenerator;
import com.example.ecommerce.core.infrastructure.persistence.inmemory.InMemoryRepositoryProvider;
import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.User.InvalidLoginError;
import com.example.ecommerce.domain.User.User;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoginTest {
    private static final String TOKEN_TEST = "token Test";

    @Test
    public void loginOk() throws InvalidLoginError, UserAlreadyExistsError {
        Login.Request request = new Login.Request("user123", "pass123");
        repositories.users().save(new User(repositories.users().nextId(), "user123", "pass123"));

        String token = login.exec(request);

        assertEquals(token, TOKEN_TEST);
    }

    @Test
    public void failIfUsernameOrPasswordIncorrect() throws UserAlreadyExistsError{
        Login.Request request = new Login.Request("incorrectUserName", "pass123");
        repositories.users().save(new User(repositories.users().nextId(), "user123", "pass123"));

        assertThrows(InvalidLoginError.class, () -> login.exec(request));
    }


    @BeforeEach
    public void setup(){
        login = new Login(repositories, tokenGenerator);
    }

    private Login login;
    private final RepositoryProvider repositories = new InMemoryRepositoryProvider();
    private final TokenGenerator tokenGenerator = new StubbedTokenGenerator(TOKEN_TEST);
}
