package com.example.ecommerce.core.domain.User;

public class UserAlreadyExistsError extends Throwable {
    public UserAlreadyExistsError(String message) {
        super(message);
    }
}
