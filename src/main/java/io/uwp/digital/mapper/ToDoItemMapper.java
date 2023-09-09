package io.uwp.digital.mapper;

import io.uwp.digital.dto.ToDoItemDTO;
import io.uwp.digital.dto.ToDoItemSavedDTO;
import io.uwp.digital.entity.ToDoItem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ToDoItemMapper {
    List<ToDoItemSavedDTO> toDoItemsToDTOS(List<ToDoItem> toDoItems);
    ToDoItemSavedDTO entityToDTO(ToDoItem toDoItem);
    ToDoItem dtoToEntity(ToDoItemDTO toDoItemDTO);
}
