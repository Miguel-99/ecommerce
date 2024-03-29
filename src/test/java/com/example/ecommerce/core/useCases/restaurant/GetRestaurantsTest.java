package com.example.ecommerce.core.useCases.restaurant;

import com.example.ecommerce.core.domain.User.User;
import com.example.ecommerce.core.domain.User.UserAlreadyExistsError;
import com.example.ecommerce.core.infrastructure.RepositoryProvider;
import com.example.ecommerce.core.infrastructure.persistence.inmemory.InMemoryRepositoryProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetRestaurantsTest {
    @Test
    public void getRestaurantsShouldNotThrowException() throws UserAlreadyExistsError {
        User user = repositories.users().add(new User(repositories.users().nextId(), "some username", "some password"));
        user.setSessionId("some session");

        Assertions.assertDoesNotThrow(() -> {
            getRestaurants.exec(user.getSessionId());
        });
    }

    @Test
    public void getRestaurantsThrowExceptionIfNotAuthenticated() {
        assertThrows(NoSuchElementException.class, () -> getRestaurants.exec("unexistentSessionId"));
    }

    @BeforeEach
    public void setup() {
        getRestaurants = new GetRestaurants(repositories);
    }

    private GetRestaurants getRestaurants;
    private final RepositoryProvider repositories = new InMemoryRepositoryProvider();

}
