package org.example.first_spring_boot_project.service.toDo;

import org.example.first_spring_boot_project.entity.ToDo;
import org.example.first_spring_boot_project.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ToDoServiceImplTest {
    ToDo toDo1 = new ToDo(null, "Zähne putzen", "Hong Hanh", true);
    ToDo toDo2 = new ToDo(null, "Haare kämmen", "Hong Hanh", true);
    ToDo toDo3 = new ToDo(null, "Kleidung trocknen", "Hong Hanh", false);
    ToDo toDo4 = new ToDo(null, "Kleidung falten", "Hong Hanh", false);

    List<ToDo> completed = Arrays.asList(toDo1, toDo2);
    List<ToDo> open = Arrays.asList(toDo3, toDo4);
    List<ToDo> all = Arrays.asList(toDo1, toDo2, toDo3, toDo4);

    @Mock
    private ToDoRepository toDoRepository;

    @InjectMocks
    private ToDoServiceImpl toDoService;

    @Test
    void createToDo() {
        ToDo notPersistedToDo = new ToDo(null, "Zähne putzen", "Hong Hanh", null);
        ToDo persistedToDo = new ToDo(1L, "Zähne putzen", "Hong Hanh", false);
        when(toDoRepository.save(any(ToDo.class))).thenReturn(persistedToDo);
        assertEquals(persistedToDo, toDoService.createToDo(notPersistedToDo));
    }

    @Test
    void getToDo() {
        when(toDoRepository.findAll()).thenReturn(all);
        assertEquals(all, toDoService.getToDo());
    }

    @Test
    void getCompletedToDos() {
        when(toDoRepository.findAllByCompletedIsTrue()).thenReturn(completed);
        assertEquals(completed, toDoService.getCompletedToDos());
    }

    @Test
    void getToDoById() {
        ToDo toDo1 = new ToDo(1L, "Zähne putzen", "Hong Hanh", false);
        when(toDoRepository.findById(any(Long.class))).thenReturn(Optional.of(toDo1));
        assertEquals(toDo1, toDoService.getToDoById(1L));
    }

    @Test
    void getOpenToDos() {
        when(toDoRepository.findAllByCompletedIsFalse()).thenReturn(open);
        assertEquals(open, toDoService.getOpenToDos());
    }

    @Test
    void removeToDo() {
        assertDoesNotThrow(() -> toDoService.removeToDo(1L));
    }

    @Test
    void updateToDo() {
        ToDo initialToDo = new ToDo(1L, "Zähne putzen", "Hong Hanh", true);
        ToDo updatedToDo = new ToDo(1L, "Zähne putzen", "Hong Hanh", true);

        when(toDoRepository.save(any(ToDo.class))).thenReturn(updatedToDo);
        assertEquals(updatedToDo, toDoService.updateToDo(initialToDo));
    }

    @Test
    void numberOfCompletedToDos() {
        when(toDoRepository.countAllByCompletedIsTrue()).thenReturn(Long.valueOf(completed.size()));
        assertEquals(Long.valueOf(completed.size()), toDoService.numberOfCompletedToDos());
    }

    @Test
    void numberOfOpenToDos() {
        when(toDoRepository.countAllByCompletedIsFalse()).thenReturn(Long.valueOf(open.size()));
        assertEquals(Long.valueOf(open.size()), toDoService.numberOfOpenToDos());
    }
}