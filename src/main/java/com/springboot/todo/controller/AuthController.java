package com.springboot.todo.controller;

import com.springboot.todo.payload.JWTAuthResponse;
import com.springboot.todo.payload.UserCredentialsDto;
import com.springboot.todo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserCredentialsDto userCredentialsDto) {
        authService.registerUser(userCredentialsDto);
        return new ResponseEntity<>("User sign up successful", HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<JWTAuthResponse> authenticateUser(@RequestBody UserCredentialsDto userCredentialsDto) {
        String token = authService.authenticateUser(userCredentialsDto);
        return ResponseEntity.ok(new JWTAuthResponse(token));
    }
}
