package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.core.domain.TokenGenerator.StubbedTokenGenerator;
import com.example.ecommerce.core.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.core.domain.User.InvalidLoginError;
import com.example.ecommerce.core.domain.User.User;
import com.example.ecommerce.core.domain.User.UserAlreadyExistsError;
import com.example.ecommerce.core.infrastructure.RepositoryProvider;
import com.example.ecommerce.core.infrastructure.persistence.inmemory.InMemoryRepositoryProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.ecommerce.core.useCases.authentication.Login.Request;
import static com.example.ecommerce.core.useCases.authentication.Login.Response;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LoginTest {
    private static final String TOKEN_TEST = "token Test";

    @Test
    public void loginOk() throws InvalidLoginError, UserAlreadyExistsError {
        Request request = new Request("user123", "pass123");
        repositories.users().add(new User(repositories.users().nextId(), "user123", "pass123"));

        Response response = login.exec(request);

        assertEquals(response.token(), TOKEN_TEST);
    }

    @Test
    public void failIfUsernameOrPasswordIncorrect() throws UserAlreadyExistsError{
        Request request = new Request("incorrectUserName", "pass123");
        repositories.users().add(new User(repositories.users().nextId(), "user123", "pass123"));

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
