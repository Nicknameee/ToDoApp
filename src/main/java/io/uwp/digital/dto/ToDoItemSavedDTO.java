package io.uwp.digital.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class ToDoItemSavedDTO {
    private Long id;
    private Long userId;
    @NotBlank(message = "Title is invalid")
    private String title;
    private String description;
    private ZonedDateTime issuedAt;
    private ZonedDateTime finishBy;
    private ZonedDateTime finishedAt;
}
