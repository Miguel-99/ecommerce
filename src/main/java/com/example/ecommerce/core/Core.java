package com.example.ecommerce.core;

import com.example.ecommerce.core.domain.TokenGenerator.TokenGenerator;
import com.example.ecommerce.core.infrastructure.RepositoryProvider;
import com.example.ecommerce.core.useCases.authentication.Login;
import com.example.ecommerce.core.useCases.authentication.SignUp;
import com.example.ecommerce.core.useCases.restaurant.AddRestaurant;
import com.example.ecommerce.core.useCases.restaurant.GetRestaurant;
import com.example.ecommerce.core.useCases.restaurant.GetRestaurants;

public class Core {
    private final RepositoryProvider repositories;
    private final TokenGenerator tokenGenerator;

    public Core(RepositoryProvider repositories, TokenGenerator tokenGenerator) {
        this.repositories = repositories;
        this.tokenGenerator = tokenGenerator;
    }

    public GetRestaurants getRestaurants() { return new GetRestaurants(repositories); }

    public AddRestaurant addRestaurant() { return new AddRestaurant(repositories); }

    public GetRestaurant getRestaurant() { return new GetRestaurant(repositories); }

    public SignUp signUp() { return new SignUp(repositories, tokenGenerator); }

    public Login login() { return new Login(repositories, tokenGenerator); }
}
