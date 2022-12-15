package com.example.ecommerce.domain.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    Long nextId();
    List<User> getAll();
    User getById(Long id);
    User findBySessionId(String session);
}
