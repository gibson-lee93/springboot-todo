package com.springboot.todo.service.impl;

import com.springboot.todo.entity.Todo;
import com.springboot.todo.payload.TodoDto;
import com.springboot.todo.repository.TodoRepository;
import com.springboot.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

    private TodoRepository todoRepository;
    private ModelMapper mapper;

    @Autowired
    public TodoServiceImpl(TodoRepository todoRepository, ModelMapper mapper) {
        this.todoRepository = todoRepository;
        this.mapper = mapper;
    }

    @Override
    public TodoDto createTodo(TodoDto todoDto) {
        Todo todo = mapToEntity(todoDto);
        Todo newTodo = todoRepository.save(todo);
        return mapToDto(newTodo);
    }

    private TodoDto mapToDto(Todo todo) {
        return mapper.map(todo, TodoDto.class);
    }

    private Todo mapToEntity(TodoDto todoDto) {
        return mapper.map(todoDto, Todo.class);
    }
}
