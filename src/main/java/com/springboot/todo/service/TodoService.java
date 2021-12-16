package com.springboot.todo.service;

import com.springboot.todo.entity.Todo;
import com.springboot.todo.payload.TodoDto;
import com.springboot.todo.payload.TodoResponse;

public interface TodoService {
    Todo createTodo(TodoDto todoDto);

    Todo getTodoById(Long id);

    TodoResponse getAllTodos(int pageNo, int pageSize);
}
