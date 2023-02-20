package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.core.domain.TokenGenerator.StubbedTokenGenerator;
import com.example.ecommerce.core.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.core.domain.User.UserAlreadyExistsError;
import com.example.ecommerce.core.infrastructure.RepositoryProvider;
import com.example.ecommerce.core.infrastructure.persistence.inmemory.InMemoryRepositoryProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.example.ecommerce.core.useCases.authentication.SignUp.Request;
import static com.example.ecommerce.core.useCases.authentication.SignUp.Response;

public class SignUpTest {


    @Test
    public void signUpOk() throws Throwable {
        Request request = new Request("user123", "pass123");

        Response response = signUp.exec(request);

        Assertions.assertEquals(response.token(), "tokenTest");
    }

    @Test
    public void failIfUserAlreadyExist() throws Throwable {
        Request request = new Request("user123", "pass123");
        signUp.exec(request);

        Assertions.assertThrows(UserAlreadyExistsError.class, () -> signUp.exec(request));
    }

    @BeforeEach
    public void setup(){
        signUp = new SignUp(repositories, tokenGenerator);
    }

    private SignUp signUp;
    private final TokenGenerator tokenGenerator = new StubbedTokenGenerator("tokenTest");
    private final RepositoryProvider repositories = new InMemoryRepositoryProvider();
}
