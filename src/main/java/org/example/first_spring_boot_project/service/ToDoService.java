package org.example.first_spring_boot_project.service;

import org.example.first_spring_boot_project.entity.ToDo;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ToDoService {
    ToDo createToDo(ToDo toDo);

    List<ToDo> getToDo();

    List<ToDo> getCompletedToDos();

    List<ToDo> getOpenToDos();

    ToDo getToDoById(Long id);

    void removeToDo(Long id);

    ToDo updateToDo(ToDo toDo);

    Long numberOfCompletedToDos();

    Long numberOfOpenToDos();


}
