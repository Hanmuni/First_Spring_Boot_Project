package org.example.first_spring_boot_project.controller;


import jakarta.validation.Valid;
import org.example.first_spring_boot_project.dto.toDo.ToDoCreateDTO;
import org.example.first_spring_boot_project.dto.toDo.ToDoUpdateDTO;
import org.example.first_spring_boot_project.entity.ToDo;
import org.example.first_spring_boot_project.service.ToDoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
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
    public ToDo createToDo(@Valid @RequestBody ToDoCreateDTO toDoCreateDTO) {
        return toDoService.createToDo(modelMapper.map(toDoCreateDTO, ToDo.class));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ToDo updateToDo(@Valid @RequestBody ToDoUpdateDTO toDoUpdateDTO) {
        return toDoService.updateToDo(modelMapper.map(toDoUpdateDTO, ToDo.class));
    }

    @DeleteMapping("/{id}")
    public void removeToDo(@PathVariable Long id) {
        toDoService.removeToDo(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ToDo> getToDo() {
        return toDoService.getToDo();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ToDo getToDoById(@PathVariable Long id) {
        return toDoService.getToDoById(id);
    }

    @GetMapping(value = "/completed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ToDo> getCompletedToDos() {
        return toDoService.getCompletedToDos();
    }

    @GetMapping(value = "/open", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ToDo> getOpenToDos() {
        return toDoService.getOpenToDos();
    }

    @GetMapping(value = "completed/number", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getNumberOfCompletedToDos() {
        return toDoService.numberOfCompletedToDos();
    }

    @GetMapping(value = "/open/number", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long getNumberOfOpenToDos() {
        return toDoService.numberOfOpenToDos();
    }

}
