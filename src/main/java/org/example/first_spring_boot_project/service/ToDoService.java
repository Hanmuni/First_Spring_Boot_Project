package org.example.first_spring_boot_project.service;

import org.example.first_spring_boot_project.entity.ToDo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ToDoService {
    ToDo createToDo(ToDo toDo);

    List<ToDo> getToDo();

    void removeToDo(Long id);

    ToDo updateToDo(Long id, ToDo toDo);

    List<ToDo> getCompletedToDos();

    List<ToDo> getOpenToDos();


}
