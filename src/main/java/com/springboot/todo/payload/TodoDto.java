package com.springboot.todo.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class TodoDto {

    private Long id;

    @NotEmpty
    @Size(min = 4, message = "Todo title should have at least 4 characters")
    private String title;

    private String description;
    private String username;
}
