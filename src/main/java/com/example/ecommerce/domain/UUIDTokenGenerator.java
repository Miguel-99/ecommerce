package com.example.ecommerce.domain;

import com.example.ecommerce.domain.TokenGenerator.TokenGenerator;

import java.util.UUID;

public class UUIDTokenGenerator implements TokenGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
