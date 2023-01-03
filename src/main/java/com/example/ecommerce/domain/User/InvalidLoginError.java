package com.example.ecommerce.domain.User;

public class InvalidLoginError extends Throwable {
    public InvalidLoginError(String message) {
        super(message);
    }
}
