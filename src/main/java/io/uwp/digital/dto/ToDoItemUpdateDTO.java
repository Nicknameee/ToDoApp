package io.uwp.digital.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ToDoItemUpdateDTO {
    private Long id;
    @NotBlank(message = "Title is invalid")
    private String title;
    private String description;
    private ZonedDateTime finishedAt;
}
