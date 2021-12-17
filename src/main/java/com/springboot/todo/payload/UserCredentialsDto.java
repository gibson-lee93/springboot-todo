package com.springboot.todo.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class UserCredentialsDto {

    @NotEmpty
    @Size(min = 4, message = "username should have at least 4 characters")
    private String username;


    @NotEmpty
    @Size(min = 4, message = "password should have at least 4 characters")
    private String password;
}
