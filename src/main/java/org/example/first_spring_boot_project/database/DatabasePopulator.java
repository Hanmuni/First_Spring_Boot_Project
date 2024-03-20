package org.example.first_spring_boot_project.database;

import org.example.first_spring_boot_project.entity.ToDo;
import org.example.first_spring_boot_project.repository.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


import java.util.Arrays;

@Component
public class DatabasePopulator implements CommandLineRunner {

    private final ToDoRepository toDoRepository;

    public DatabasePopulator(@Autowired ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        ToDo toDo1 = new ToDo(null, "Geschirr spülen", "Hong Hanh", false);
        ToDo toDo2 = new ToDo(null, "Fleisch kaufen", "Hong Hanh", false);
        ToDo toDo3 = new ToDo(null, "Fahrradreifen wechseln", "Hong Hanh", true);

        toDoRepository.saveAll(Arrays.asList(toDo1, toDo2, toDo3));

    }
}
