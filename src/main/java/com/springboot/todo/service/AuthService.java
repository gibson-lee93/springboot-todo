package com.springboot.todo.service;

import com.springboot.todo.payload.SignUpDto;

public interface AuthService {
    void registerUser(SignUpDto signUpDto);
}
