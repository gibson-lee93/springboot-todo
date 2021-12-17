package com.springboot.todo.service.impl;


import com.springboot.todo.entity.User;
import com.springboot.todo.exception.TodoAPIException;
import com.springboot.todo.payload.UserCredentialsDto;
import com.springboot.todo.repository.UserRepository;
import com.springboot.todo.security.JwtTokenProvider;
import com.springboot.todo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public void registerUser(UserCredentialsDto userCredentialsDto) {
        if (userRepository.existsByUsername(userCredentialsDto.getUsername())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Username already exists");
        }

        User user = new User();
        user.setUsername(userCredentialsDto.getUsername());
        user.setPassword(passwordEncoder.encode(userCredentialsDto.getPassword()));

        try {
            userRepository.save(user);
        } catch (Exception exception) {
            throw new TodoAPIException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal Server Error");
        }
    }

    @Override
    public String authenticateUser(UserCredentialsDto userCredentialsDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userCredentialsDto.getUsername(), userCredentialsDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return tokenProvider.generateToken(authentication);
    }
}
