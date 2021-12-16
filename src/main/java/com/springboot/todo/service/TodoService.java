package com.springboot.todo.service;

import com.springboot.todo.entity.Todo;
import com.springboot.todo.payload.TodoDto;

public interface TodoService {
    Todo createTodo(TodoDto todoDto);

    Todo getTodoById(Long id);
}
