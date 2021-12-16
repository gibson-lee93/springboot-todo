package com.springboot.todo.service;

import com.springboot.todo.payload.TodoDto;

public interface TodoService {
    TodoDto createTodo(TodoDto todoDto);
}
