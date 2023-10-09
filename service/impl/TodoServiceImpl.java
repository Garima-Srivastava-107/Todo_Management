package com.example.todo_management.service.impl;

import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.entity.Todo;
import com.example.todo_management.repository.TodoRepository;
import com.example.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;
    @Override
    public TodoDto addTodo(TodoDto todoDto){
        //logic to convert to todoDto to jpa entity todo
        Todo todo=new Todo();
       // todo.setId(todoDto.getId());
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.getCompleted());
        //save method return s the saved todo
        Todo todo1=todoRepository.save(todo);
        //logic to convert jpa entity dto
        TodoDto todoDto2=new TodoDto();
        todoDto2.setId(todo1.getId());
        todoDto2.setTitle(todo1.getTitle());
        todoDto2.setDescription(todo1.getDescription());
        todoDto2.setCompleted(todo1.getCompleted());

        return todoDto2;
    }
}
