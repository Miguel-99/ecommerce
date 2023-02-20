package com.example.ecommerce.core.domain.User;

public interface UserRepository {
    User add(User user) throws UserAlreadyExistsError;
    Long nextId();
    User getById(Long id);
    User update(User user);
    User findBySessionId(String session);
    User getBy(String username, String password) throws InvalidLoginError;
}
