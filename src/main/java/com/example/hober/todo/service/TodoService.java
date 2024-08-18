package com.example.hober.todo.service;


import com.example.hober.todo.dao.TodoDao;
import com.example.hober.todo.domain.Todo;
import com.example.hober.todo.dto.TodoCreateDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoDao todoDao;

    @Transactional
    public void save(@RequestBody TodoCreateDto todoCreateDto) {
        Todo todo = Todo.builder().content(todoCreateDto.getContent()).build();
        todoDao.save(todo);
    }

    @Transactional
    public void update(long todoId) {
        Todo todo = todoDao.findById(todoId).orElseThrow(() -> new IllegalArgumentException());
        todo.updateComplete();
        todoDao.save(todo);
    }

    @Transactional
    public void delete(long todoId){
        todoDao.deleteById(todoId);
    }

    @Transactional(readOnly = true)
    public List<Todo> findAll() {
        return todoDao.findAll();
    }
}
