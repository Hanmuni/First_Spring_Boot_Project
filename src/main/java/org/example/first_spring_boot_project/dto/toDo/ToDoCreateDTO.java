package org.example.first_spring_boot_project.dto.toDo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class ToDoCreateDTO {

    @NotBlank
    @Size(min = 1, max = 200)
    private String toDo;

    @NotBlank
    @Size(min = 1, max = 15)
    private String author;
}
