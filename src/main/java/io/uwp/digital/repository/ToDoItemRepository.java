package io.uwp.digital.repository;

import io.uwp.digital.entity.ToDoItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ToDoItemRepository extends JpaRepository<ToDoItem, Long> {
    boolean existsByIdAndUserId(Long id, Long userId);
    List<ToDoItem> getToDoItemsByUserId(Long userId);
    Optional<ToDoItem> getToDoItemByIdAndUserId(Long id, Long userId);
    void deleteAllByUserId(Long userId);
}
