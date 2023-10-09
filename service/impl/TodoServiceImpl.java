package com.example.todo_management.service.impl;

import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.entity.Todo;
import com.example.todo_management.repository.TodoRepository;
import com.example.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;
    private ModelMapper modelMapper;
    @Override
    public TodoDto addTodo(TodoDto todoDto){
        //logic to convert to todoDto to jpa entity todo
        Todo todo=modelMapper.map(todoDto,Todo.class);
        //save method return s the saved todo
        Todo todo1=todoRepository.save(todo);
        //logic to convert jpa entity dto
        TodoDto todoDto2=modelMapper.map(todo1,TodoDto.class);
        return todoDto2;
    }
    @Override
    public TodoDto getTodo(Long id){
        Todo todo=todoRepository.getById(id);
        //logic to convert jpa entity dto
        TodoDto todoDto2=modelMapper.map(todo,TodoDto.class);
        return todoDto2;
    }
    @Override
    public List<TodoDto> getAllTodo(){
        List<Todo> todo=todoRepository.findAll();
        //logic to convert jpa entity dto
        return todo.stream().map((todos)-> modelMapper.map(todos,TodoDto.class)).collect(Collectors.toList());
    }


}
