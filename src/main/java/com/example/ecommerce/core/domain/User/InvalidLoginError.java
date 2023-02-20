package com.example.ecommerce.core.domain.User;

public class InvalidLoginError extends Throwable {
    public InvalidLoginError(String message) {
        super(message);
    }
}
