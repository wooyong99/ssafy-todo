package com.example.hober.todo.controller;

import com.example.hober.todo.dto.TodoCreateDto;
import com.example.hober.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public ResponseEntity getTodo(){
        return ResponseEntity.ok(todoService.findAll());
    }

    @PostMapping
    public ResponseEntity saveTodo(@RequestBody TodoCreateDto todoCreateDto){
        todoService.save(todoCreateDto);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity updateTodo(@PathVariable long todoId){
        todoService.update(todoId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity deleteTodo(@PathVariable long todoId){
        todoService.delete(todoId);

        return ResponseEntity.noContent().build();
    }
}
