package com.springboot.todo.service.impl;

import com.springboot.todo.entity.Todo;
import com.springboot.todo.entity.User;
import com.springboot.todo.exception.TodoAPIException;
import com.springboot.todo.payload.TodoDto;
import com.springboot.todo.payload.TodoResponse;
import com.springboot.todo.repository.TodoRepository;
import com.springboot.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public TodoDto createTodo(TodoDto todoDto, User user) {
        Todo todo = mapToEntity(todoDto);
        todo.setUser(user);
        return mapToDto(todoRepository.save(todo));
    }

    @Override
    public TodoDto getTodoById(Long id, User user) {
        Todo todos = todoRepository.findByIdAndUserId(id, user.getId());
        if (todos == null) {
            throw new TodoAPIException(HttpStatus.NOT_FOUND, "Can't find todo with id: " + id);
        }
        return mapToDto(todos);
    }

    @Override
    public TodoResponse getAllTodos(int pageNo, int pageSize, User user) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Todo> todos = todoRepository.findAll(pageable);
        List<Todo> listOfTodos = todos.getContent();
        List<TodoDto> content = listOfTodos.stream().map((post) -> mapToDto(post)).collect(Collectors.toList());

        TodoResponse todoResponse = new TodoResponse();
        todoResponse.setContent(content);
        todoResponse.setPageNo(todos.getNumber());
        todoResponse.setPageSize(todos.getSize());
        todoResponse.setTotalElements(todos.getTotalElements());
        todoResponse.setTotalPages(todos.getTotalPages());
        todoResponse.setLast(todos.isLast());

        return todoResponse;
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id, User user) {
        Todo todo = mapToEntity(getTodoById(id, user));

        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setUser(user);

        return mapToDto(todoRepository.save(todo));
    }

    @Override
    public void deleteTodo(Long id, User user) {
        Todo todo = mapToEntity(getTodoById(id, user));
        todoRepository.delete(todo);
    }

    private TodoDto mapToDto(Todo todo) {
        TodoDto todoDto = mapper.map(todo, TodoDto.class);
        todoDto.setUsername(todo.getUser().getUsername());
        return todoDto;
    }

    private Todo mapToEntity(TodoDto todoDto) {
        return mapper.map(todoDto, Todo.class);
    }
}
