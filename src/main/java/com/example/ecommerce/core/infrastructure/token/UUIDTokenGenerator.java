package com.example.ecommerce.core.infrastructure.token;

import com.example.ecommerce.core.domain.TokenGenerator.TokenGenerator;

import java.util.UUID;

public class UUIDTokenGenerator implements TokenGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
