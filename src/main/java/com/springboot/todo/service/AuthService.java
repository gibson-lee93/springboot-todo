package com.springboot.todo.service;

import com.springboot.todo.payload.UserCredentialsDto;

public interface AuthService {
    void registerUser(UserCredentialsDto userCredentialsDto);

    String authenticateUser(UserCredentialsDto userCredentialsDto);
}
