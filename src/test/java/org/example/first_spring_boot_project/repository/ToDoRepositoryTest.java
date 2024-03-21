package org.example.first_spring_boot_project.repository;

import org.example.first_spring_boot_project.entity.ToDo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ToDoRepositoryTest {
    ToDo toDo1 = new ToDo(null, "Zähne putzen", "Hong Hanh", true);
    ToDo toDo2 = new ToDo(null, "Haare kämmen", "Hong Hanh", true);
    ToDo toDo3 = new ToDo(null, "Kleidung trocknen", "Hong Hanh", false);
    ToDo toDo4 = new ToDo(null, "Kleidung falten", "Hong Hanh", false);


    List<ToDo> completed = Arrays.asList(toDo1, toDo2);
    List<ToDo> open = Arrays.asList(toDo3, toDo4);


    @Autowired
    private ToDoRepository toDoRepository;


    @BeforeEach
    void init() {
        toDoRepository.saveAll(Arrays.asList(toDo1, toDo2, toDo3, toDo4));
    }

    @Test
    void findAllByCompletedIsTrue() {
        List<ToDo> expected = toDoRepository.findAllByCompletedIsTrue();
        assertTrue(expected.containsAll(completed));
        assertEquals(expected.size(), completed.size());
    }

    @Test
    void findAllByCompletedIsFalse() {
        List<ToDo> expected = toDoRepository.findAllByCompletedIsFalse();
        assertTrue(expected.containsAll(open));
        assertEquals(expected.size(), open.size());
    }

    @Test
    void countAllByCompletedIsTrue() {
        Long count = toDoRepository.countAllByCompletedIsTrue();
        assertEquals(completed.size(), count);
    }

    @Test
    void countAllByCompletedIsFalse() {
        Long count = toDoRepository.countAllByCompletedIsFalse();
        assertEquals(open.size(), count);
    }
}