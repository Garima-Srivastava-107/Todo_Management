package com.example.todo_management.service;

import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.entity.Todo;

import java.util.List;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
    TodoDto getTodo(Long id);
    List<TodoDto> getAllTodo();
    TodoDto updateTodo(TodoDto todoDto,Long id);

}
