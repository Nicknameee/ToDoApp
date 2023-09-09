package io.uwp.digital.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ToDoItemDTO {
    @NotBlank(message = "Title is invalid")
    private String title;
    private String description;
    private ZonedDateTime finishBy;
}
