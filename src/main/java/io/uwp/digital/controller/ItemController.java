package io.uwp.digital.controller;

import io.uwp.digital.dto.ToDoItemDTO;
import io.uwp.digital.dto.ToDoItemUpdateDTO;
import io.uwp.digital.service.UserServiceImpl;
import io.uwp.digital.service.impl.ToDoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
    private final ToDoItemService toDoItemService;

    @GetMapping
    public ResponseEntity<?> getAllToDos() {
        return ResponseEntity.ok(toDoItemService.getAllToDos(UserServiceImpl.getCurrentlyAuthenticatedUserId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getToDoById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(toDoItemService.getToDoItemById(id));
    }

    @PostMapping
    public ResponseEntity<?> addToDo(@RequestBody @Valid ToDoItemDTO toDoItemDTO) {
        return ResponseEntity.ok(toDoItemService.saveToDoItem(toDoItemDTO));
    }

    @PutMapping
    public ResponseEntity<?> updateToDo(@RequestBody @Valid ToDoItemUpdateDTO toDoItemDTO) {
        toDoItemService.updateToDoItem(toDoItemDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable("id") Long id) {
        toDoItemService.deleteToDoItem(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAllToDos() {
        toDoItemService.deleteAllToDos();
        return ResponseEntity.ok().build();
    }
}
