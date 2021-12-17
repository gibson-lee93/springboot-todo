package com.springboot.todo.controller;

import com.springboot.todo.entity.User;
import com.springboot.todo.payload.TodoDto;
import com.springboot.todo.payload.TodoResponse;
import com.springboot.todo.repository.UserRepository;
import com.springboot.todo.service.TodoService;
import com.springboot.todo.utils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todos")
public class TodoController {

    private TodoService todoService;
    private UserRepository userRepository;

    public TodoController(TodoService todoService, UserRepository userRepository) {
        this.todoService = todoService;
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<TodoDto> createTodo(
            @RequestBody TodoDto todoDto,
            Authentication authentication
    ) {
        User user = getLoggedInUser(authentication);
        return new ResponseEntity<>(todoService.createTodo(todoDto, user), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodoById(
            @PathVariable(name = "id") Long id,
            Authentication authentication
    ) {
        User user = getLoggedInUser(authentication);
        return ResponseEntity.ok(todoService.getTodoById(id, user));
    }

    @GetMapping
    public TodoResponse getAllTodos(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            Authentication authentication
    ) {
        User user = getLoggedInUser(authentication);
        return todoService.getAllTodos(pageNo, pageSize, user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(
            @RequestBody TodoDto todoDto,
            @PathVariable(value = "id") Long id,
            Authentication authentication
    ) {
        User user = getLoggedInUser(authentication);
        return ResponseEntity.ok(todoService.updateTodo(todoDto, id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodo(
            @PathVariable(name = "id") Long id,
            Authentication authentication
    ) {
        User user = getLoggedInUser(authentication);
        todoService.deleteTodo(id, user);
        return ResponseEntity.ok("Todo deleted successfully");
    }

    private User getLoggedInUser(Authentication authentication) {
        Optional<User> optionalUser = userRepository.findByUsername(authentication.getName());
        return optionalUser.get();
    }
}
