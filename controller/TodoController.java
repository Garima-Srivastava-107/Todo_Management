package com.example.todo_management.controller;

import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
    @Autowired
    private TodoService todoService;
//    Build add todo Rest API
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
       TodoDto savedtodoDto=todoService.addTodo(todoDto);
       return new ResponseEntity<>(savedtodoDto, HttpStatus.CREATED);
    }
    //Build get todo REST API
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){
        TodoDto reqTodo=todoService.getTodo(id);
        return new ResponseEntity<>(reqTodo,HttpStatus.OK);
    }
    //Build get All todo REST API
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        List<TodoDto> reqTodo=todoService.getAllTodo();
        //return new ResponseEntity<>(reqTodo,HttpStatus.OK);
        //another way of writing the same thing
        return ResponseEntity.ok(reqTodo);
    }
    //Build update todo REST API
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable Long id){
        TodoDto todo=todoService.updateTodo(todoDto,id);
        return ResponseEntity.ok(todo);
    }
}
