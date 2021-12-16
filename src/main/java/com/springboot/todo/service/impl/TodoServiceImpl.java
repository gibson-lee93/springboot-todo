package com.springboot.todo.service.impl;

import com.springboot.todo.repository.TodoRepository;
import com.springboot.todo.service.TodoService;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }
}
