package com.example.todo_management.controller;

import com.example.todo_management.dto.TodoDto;
import com.example.todo_management.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/todos")
@AllArgsConstructor
public class TodoController {
    @Autowired
    private TodoService todoService;
//    Build add todo Rest API
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto){
       TodoDto savedtodoDto=todoService.addTodo(todoDto);
       return new ResponseEntity<>(savedtodoDto, HttpStatus.CREATED);
    }
    //Build get todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/{id}")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){
        TodoDto reqTodo=todoService.getTodo(id);
        return new ResponseEntity<>(reqTodo,HttpStatus.OK);
    }
    //Build get All todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodo(){
        List<TodoDto> reqTodo=todoService.getAllTodo();
        //return new ResponseEntity<>(reqTodo,HttpStatus.OK);
        //another way of writing the same thing
        return ResponseEntity.ok(reqTodo);
    }
    //Build update todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable Long id){
        TodoDto todo=todoService.updateTodo(todoDto,id);
        return ResponseEntity.ok(todo);
    }
    //Build delete todo REST API
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<TodoDto> deleteTodo(@PathVariable Long id){
      TodoDto deletedTodo=todoService.deleteTodo(id);
        return ResponseEntity.ok(deletedTodo);
    }
    //Build Complete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PatchMapping("/comp/{id}")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto compTodo=todoService.completeTodo(id);
        return ResponseEntity.ok(compTodo);
    }
    //Build inComplete Todo REST API
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
//    all the complete method and incomplete method has the patch mapping
// IMPORTANT :-->   PUT is a technique of altering resources when the client transmits data that revamps the whole resource.
// PATCH is a technique for transforming the resources when the client transmits partial data that will be updated without changing the whole data.
    @PatchMapping("/incomplete/{id}")
    public ResponseEntity<TodoDto> incompleteTodo(@PathVariable Long id){
        TodoDto compTodo=todoService.incompleteTodo(id);
        return ResponseEntity.ok(compTodo);
    }
}
