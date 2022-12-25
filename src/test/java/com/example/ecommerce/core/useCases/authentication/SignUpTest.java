package com.example.ecommerce.core.useCases.authentication;

import com.example.ecommerce.core.domain.TokenGenerator.StubbedTokenGenerator;
import com.example.ecommerce.core.infrastructure.InMemoryUserRepository;
import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;
import com.example.ecommerce.domain.User.UserRepository;
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
        userRepository = new InMemoryUserRepository();
        tokenGenerator = new StubbedTokenGenerator("tokenTest");
        signUp = new SignUp(userRepository, tokenGenerator);
    }

    private SignUp signUp;
    private UserRepository userRepository;
    private TokenGenerator tokenGenerator;
}
