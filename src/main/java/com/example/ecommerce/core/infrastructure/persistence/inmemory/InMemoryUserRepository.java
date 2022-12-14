package com.example.ecommerce.core.infrastructure.persistence.inmemory;

import com.example.ecommerce.domain.User.InvalidLoginError;
import com.example.ecommerce.domain.User.User;
import com.example.ecommerce.domain.User.UserRepository;
import com.example.ecommerce.domain.User.UserAlreadyExistsError;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository implements UserRepository {
    private final List<User> users = new ArrayList<>();
    private Long id = 1L;

    @Override
    public User save(User user) throws UserAlreadyExistsError {
        if (users.stream().anyMatch(u -> u.getUsername().equalsIgnoreCase(user.getUsername()))) {
            throw new UserAlreadyExistsError();
        }
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

    @Override
    public User getBy(String username, String password) throws InvalidLoginError {
        return users
                .stream()
                .filter(user -> user.getUsername().equalsIgnoreCase(username) && user.getPassword().equals(password))
                .findFirst()
                .orElseThrow(() -> new InvalidLoginError("El nombre de usuario y/o contraseña son incorrectos"));
    }
}
