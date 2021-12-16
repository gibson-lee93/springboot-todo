package com.springboot.todo.controller;

import com.springboot.todo.entity.Todo;
import com.springboot.todo.payload.TodoDto;
import com.springboot.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<Todo> createTodo(@RequestBody TodoDto todoDto) {
        return new ResponseEntity<>(todoService.createTodo(todoDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(todoService.getTodoById(id));
    }
}
