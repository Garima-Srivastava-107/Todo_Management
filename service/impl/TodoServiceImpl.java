package com.example.todo_management.service.impl;

import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.entity.Todo;
import com.example.todo_management.exceptions.ResourceNotFoundException;
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
//        if todo method doesn't exists in class
        Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id : "+id));;
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
    @Override
    public TodoDto updateTodo(TodoDto todoDto,Long id){
//        First--> Retrieve existing todo message
        Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id : "+id));
        todo.setTitle((todoDto.getTitle()));
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.getCompleted());
        //save method perform both insert and update operation if primary key is there then update
         Todo todo1=todoRepository.save(todo);
        TodoDto todoDto2=modelMapper.map(todo1,TodoDto.class);
        return todoDto2;
    }
    @Override
    public TodoDto deleteTodo(Long id){
//        First--> Retrieve existing todo message
        Todo todo=todoRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Todo not found with id : "+id));
        todoRepository.deleteById(id);
        TodoDto todoDto2=modelMapper.map(todo,TodoDto.class);
        return todoDto2;
    }
    @Override
    public TodoDto completeTodo(Long id){
        //First -->Retrieve the existing todo message
        Todo todo=todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id :"+id));
        todo.setCompleted(Boolean.TRUE);
        Todo todo1=todoRepository.save(todo);
        TodoDto todoDto2=modelMapper.map(todo1,TodoDto.class);
        return todoDto2;
    }
    @Override
    public TodoDto incompleteTodo(Long id){
        //First -->Retrieve the existing todo message
        Todo todo=todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id :"+id));
        todo.setCompleted(Boolean.FALSE);
        Todo todo1=todoRepository.save(todo);
        TodoDto todoDto2=modelMapper.map(todo1,TodoDto.class);
        return todoDto2;
    }


}
