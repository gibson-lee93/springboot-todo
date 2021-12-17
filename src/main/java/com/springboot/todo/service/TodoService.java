package com.springboot.todo.service;

import com.springboot.todo.entity.User;
import com.springboot.todo.payload.TodoDto;
import com.springboot.todo.payload.TodoResponse;

public interface TodoService {
    TodoDto createTodo(TodoDto todoDto, User user);

    TodoDto getTodoById(Long id, User user);

    TodoResponse getAllTodos(int pageNo, int pageSize, User user);

    TodoDto updateTodo(TodoDto todoDto, Long id, User user);

    void deleteTodo(Long id, User user);
}
