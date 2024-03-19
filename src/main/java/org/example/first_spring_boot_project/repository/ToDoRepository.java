package org.example.first_spring_boot_project.repository;

import org.example.first_spring_boot_project.entity.ToDo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ToDoRepository extends CrudRepository<ToDo, Long> {
    List<ToDo> findAllByCompletedIsTrue();
    List<ToDo> findAllByCompletedIsFalse();

    Long countAllByCompletedIsTrue();

    Long countAllByCompletedIsFalse();
}
