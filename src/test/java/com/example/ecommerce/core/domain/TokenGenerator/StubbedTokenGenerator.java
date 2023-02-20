package com.example.ecommerce.core.domain.TokenGenerator;

public class StubbedTokenGenerator implements TokenGenerator {
    private String token;

    public StubbedTokenGenerator(String token) {
        this.token = token;
    }

    @Override
    public String generate() {
        return token;
    }
}
