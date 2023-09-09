package io.uwp.digital.service.impl;

import io.uwp.digital.dto.ToDoItemDTO;
import io.uwp.digital.dto.ToDoItemSavedDTO;
import io.uwp.digital.dto.ToDoItemUpdateDTO;
import io.uwp.digital.entity.ToDoItem;

import java.util.List;

public interface ToDoItemService {
    List<ToDoItemSavedDTO> getAllToDos(Long userId);
    ToDoItem getToDoItemById(Long id);
    ToDoItemSavedDTO saveToDoItem(ToDoItemDTO toDoItem);
    void updateToDoItem(ToDoItemUpdateDTO toDoItemDTO);
    void deleteToDoItem(Long toDoItemId);

    void deleteAllToDos();
}
