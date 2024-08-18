package com.example.hober.todo.dao;

import com.example.hober.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoDao extends JpaRepository<Todo, Long> {

}
