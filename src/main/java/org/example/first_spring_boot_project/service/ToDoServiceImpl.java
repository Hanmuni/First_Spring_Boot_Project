package org.example.first_spring_boot_project.service;


import org.example.first_spring_boot_project.entity.ToDo;
import org.example.first_spring_boot_project.repository.ToDoRepository;

import java.util.List;


public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }


    @Override
    public ToDo createToDo(ToDo toDo) {
        return toDoRepository.save(toDo);
    }

    @Override
    public List<ToDo> getToDo() {
        return (List<ToDo>) toDoRepository.findAll();
    }

    @Override
    public void removeToDo(Long id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public ToDo updateToDo(Long id, ToDo toDo) {
        if (toDoRepository.existsById(id)) {
            return toDoRepository.save(toDo);
        } else {
            return null;
        }
    }

    @Override
    public List<ToDo> getCompletedToDos() {
        return toDoRepository.findAllByCompletedIsTrue();
    }

    @Override
    public List<ToDo> getOpenToDos() {
        return toDoRepository.findAllByCompletedIsFalse();
    }

}

