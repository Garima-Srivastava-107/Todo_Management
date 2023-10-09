package com.example.todo_management.service;

import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.entity.Todo;

public interface TodoService {
    TodoDto addTodo(TodoDto todoDto);
}
