package com.example.ecommerce.core.infrastructure;

import com.example.ecommerce.domain.User.User;
import com.example.ecommerce.domain.User.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();
    private Long id = 1L;

    @Override
    public User save(User user) {
        users.add(user);
        return user;
    }

    @Override
    public Long nextId() { return id++; }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public User getById(Long id) {
        return users
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public User findBySessionId(String sessionId) {
        return users
                .stream()
                .filter(user -> user.getSessionId().equals(sessionId))
                .findFirst()
                .orElseThrow();
    }
}
