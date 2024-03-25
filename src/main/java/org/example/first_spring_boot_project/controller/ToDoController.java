package org.example.first_spring_boot_project.controller;


import jakarta.validation.Valid;
import org.example.first_spring_boot_project.dto.toDo.ToDoCreateDTO;
import org.example.first_spring_boot_project.dto.toDo.ToDoUpdateDTO;
import org.example.first_spring_boot_project.entity.ToDo;
import org.example.first_spring_boot_project.service.toDo.ToDoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo")
public class ToDoController {


    private final ToDoService toDoService;
    private final ModelMapper modelMapper;

    public ToDoController(ToDoService toDoService, ModelMapper modelMapper) {
        this.toDoService = toDoService;
        this.modelMapper = modelMapper;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> createToDo(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO) {
        return new ResponseEntity<>(modelMapper.map(toDoCreateDTO, ToDo.class), HttpStatus.CREATED);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TODO_UPDATE')")
    public ResponseEntity<ToDo> updateToDo(@Valid @RequestBody ToDoUpdateDTO toDoUpdateDTO) {
        return new ResponseEntity<>(toDoService.updateToDo(modelMapper.map(toDoUpdateDTO, ToDo.class)), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('TODO_DELETE')")
    public ResponseEntity<Void> removeToDo(@PathVariable Long id) {
        toDoService.removeToDo(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TODO_READ_ALL')")
    public List<ToDo> getToDo() {
        return toDoService.getToDo();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ToDo> getToDoById(@PathVariable Long id) {
        return new ResponseEntity<>(toDoService.getToDoById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/completed", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TODO_READ_COUNT_DONE')")
    public ResponseEntity<List<ToDo>> getCompletedToDos() {
        return new ResponseEntity<>(toDoService.getCompletedToDos(), HttpStatus.OK);
    }

    @GetMapping(value = "/open", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('TODO_READ_COUNT_OPEN')")
    public ResponseEntity<List<ToDo>> getOpenToDos() {
        return new ResponseEntity<>(toDoService.getOpenToDos(), HttpStatus.OK);
    }

    @GetMapping(value = "completed/number", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> getNumberOfCompletedToDos() {
        return new ResponseEntity<>(toDoService.numberOfCompletedToDos(), HttpStatus.OK);
    }

    @GetMapping(value = "/open/number", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> getNumberOfOpenToDos() {
        return new ResponseEntity<>(toDoService.numberOfOpenToDos(), HttpStatus.OK);
    }

}
