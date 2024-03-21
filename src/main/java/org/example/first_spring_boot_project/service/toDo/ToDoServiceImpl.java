package org.example.first_spring_boot_project.service.toDo;


import jakarta.persistence.EntityNotFoundException;
import org.example.first_spring_boot_project.entity.ToDo;
import org.example.first_spring_boot_project.repository.ToDoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public List<ToDo> getCompletedToDos() {
        return toDoRepository.findAllByCompletedIsTrue();
    }

    @Override
    public ToDo getToDoById(Long id) {
        return toDoRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ToDo with the id " + id + " could not be found!"));
    }

    @Override
    public List<ToDo> getOpenToDos() {
        return toDoRepository.findAllByCompletedIsFalse();
    }

    @Override
    public void removeToDo(Long id) {
        toDoRepository.deleteById(id);
    }

    @Override
    public ToDo updateToDo(ToDo toDo) {
        return toDoRepository.save(toDo);

    }

    @Override
    public Long numberOfCompletedToDos() {
        return toDoRepository.countAllByCompletedIsTrue();
    }

    @Override
    public Long numberOfOpenToDos() {
        return toDoRepository.countAllByCompletedIsFalse();
    }

}

