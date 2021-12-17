package com.springboot.todo.repository;

import com.springboot.todo.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    Todo findByIdAndUserId(Long id, Long userId);
    Page<Todo> findByUserId(Long userId, Pageable pageable);
}
