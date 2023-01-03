package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.core.RepositoryProvider;
import com.example.ecommerce.core.domain.TokenGenerator.StubbedTokenGenerator;
import com.example.ecommerce.core.infrastructure.persistence.inmemory.InMemoryRepositoryProvider;
import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SignUpTest {


    @Test
    public void signUpOk() throws UserAlreadyExistsError {
        SignUp.Request request = new SignUp.Request("user123", "pass123");

        String token = signUp.exec(request);

        Assertions.assertEquals(token, "tokenTest");
    }

    @Test
    public void failIfUserAlreadyExist() throws UserAlreadyExistsError {
        SignUp.Request request = new SignUp.Request("user123", "pass123");
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
