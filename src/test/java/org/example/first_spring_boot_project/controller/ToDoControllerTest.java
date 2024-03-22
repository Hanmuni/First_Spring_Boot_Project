package org.example.first_spring_boot_project.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.first_spring_boot_project.entity.ToDo;
import org.example.first_spring_boot_project.service.toDo.ToDoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class ToDoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ToDoServiceImpl toDoServiceImpl;

    ToDo toDo1 = new ToDo(null, "Zähne putzen", "Hong Hanh", true);
    ToDo toDo2 = new ToDo(null, "Haare kämmen", "Hong Hanh", true);
    ToDo toDo3 = new ToDo(null, "Kleidung trocknen", "Hong Hanh", false);
    ToDo toDo4 = new ToDo(null, "Kleidung falten", "Hong Hanh", false);

    List<ToDo> completed = Arrays.asList(toDo1, toDo2);
    List<ToDo> open = Arrays.asList(toDo3, toDo4);

    @Test
    void createToDo() throws Exception {
        when(toDoServiceImpl.createToDo(any(ToDo.class))).thenReturn(toDo1);

        mockMvc.perform(post("/todo").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDo1))).andExpect(status().isCreated());
    }

    @Test
    void updateToDo() throws Exception {
        when(toDoServiceImpl.updateToDo(any(ToDo.class))).thenReturn(toDo1);

        mockMvc.perform(put("/todo").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(toDo1))).andExpect(status().isAccepted());
    }

    @Test
    void removeToDo() throws Exception {
        Long id = 1L;
        doNothing().when(toDoServiceImpl).removeToDo(id);

        mockMvc.perform(delete("/todo/{id}", id)).andExpect(status().isGone());

        verify(toDoServiceImpl).removeToDo(id);
    }

    @Test
    void getToDo() throws Exception {
        List<ToDo> mockToDoList = Arrays.asList(toDo1, toDo2, toDo3, toDo4);
        when(toDoServiceImpl.getToDo()).thenReturn(mockToDoList);


        mockMvc.perform(get("/todo").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(4))).andExpect(jsonPath("$[0].title", is("Zähne putzen"))).andExpect(jsonPath("$[1].title", is("Haare kämmen"))).andExpect(jsonPath("$[2].title", is("Kleidung trocknen"))).andExpect(jsonPath("$[3].title", is("Kleidung falten")));

        verify(toDoServiceImpl).getToDo();

    }

    @Test
    void getToDoById() throws Exception {
        when(toDoServiceImpl.getToDoById(1L)).thenReturn(toDo1);

        mockMvc.perform(get("/todo/{id}", 1L)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$.id", is(1)));

        verify(toDoServiceImpl, times(1)).getToDoById(1L);
    }

    @Test
    void getCompletedToDos() throws Exception {
        List<ToDo> open = Arrays.asList(toDo1, toDo2);
        List<ToDo> finished = null;
        when(toDoServiceImpl.getCompletedToDos()).thenReturn(finished);

        mockMvc.perform(get("/todo/finished")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].completed", is(true))).andExpect(jsonPath("$[1].completed", is(true)));

        verify(toDoServiceImpl, times(1)).getCompletedToDos();
    }

    @Test
    void getOpenToDos() throws Exception {
        when(toDoServiceImpl.getOpenToDos()).thenReturn(open);

        mockMvc.perform(get("/todo/open")).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$[0].completed", is(false))).andExpect(jsonPath("$[1].completed", is(false))).andExpect(jsonPath("$[2].completed", is(false)));
        verify(toDoServiceImpl, times(1)).getOpenToDos();
    }

    @Test
    void getNumberOfCompletedToDos() throws Exception {
        when(toDoServiceImpl.numberOfCompletedToDos()).thenReturn(4L);

        mockMvc.perform(get("/todo/completed"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(4)));

        verify(toDoServiceImpl, times(1)).numberOfCompletedToDos();
    }

    @Test
    void getNumberOfOpenToDos() throws Exception {
        when(toDoServiceImpl.numberOfOpenToDos()).thenReturn(8L);

        mockMvc.perform(get("/todo/open"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", is(8)));

        verify(toDoServiceImpl, times(1)).numberOfOpenToDos();
    }
}