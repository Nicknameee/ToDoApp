package io.uwp.digital.service.impl;

import io.uwp.digital.dto.ToDoItemDTO;
import io.uwp.digital.dto.ToDoItemSavedDTO;
import io.uwp.digital.dto.ToDoItemUpdateDTO;
import io.uwp.digital.entity.ToDoItem;
import io.uwp.digital.exceptions.NotFoundException;
import io.uwp.digital.mapper.ToDoItemMapper;
import io.uwp.digital.repository.ToDoItemRepository;
import io.uwp.digital.service.ToDoItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ToDoItemServiceImpl implements ToDoItemService {
    private final ToDoItemRepository toDoItemRepository;
    private final ToDoItemMapper toDoItemMapper;

    @Override
    public List<ToDoItemSavedDTO> getAllToDos(Long userId) {
        List<ToDoItem> toDoItems = toDoItemRepository.getToDoItemsByUserId(userId);

        return toDoItemMapper.toDoItemsToDTOS(toDoItems);
    }

    @Override
    public ToDoItem getToDoItemById(Long id) {
        Long currentUserId = UserServiceImpl.getCurrentlyAuthenticatedUserId();

        return toDoItemRepository.getToDoItemByIdAndUserId(id, currentUserId)
                .orElseThrow(() -> new NotFoundException(String.format("Item with ID: %s and user: %s was not found", id, currentUserId)));
    }

    @Override
    public ToDoItemSavedDTO saveToDoItem(ToDoItemDTO toDoItemDTO) {
        ToDoItem toDoItem = toDoItemMapper.dtoToEntity(toDoItemDTO);

        toDoItem.setUserId(UserServiceImpl.getCurrentlyAuthenticatedUserId());

        toDoItem = toDoItemRepository.save(toDoItem);

        return toDoItemMapper.entityToDTO(toDoItem);
    }

    @Override
    public void updateToDoItem(ToDoItemUpdateDTO toDoItemUpdateDTO) {
        Long userId = UserServiceImpl.getCurrentlyAuthenticatedUserId();

        if (toDoItemRepository.existsByIdAndUserId(toDoItemUpdateDTO.getId(), userId)) {
            ToDoItem toDoItem = toDoItemRepository.getReferenceById(toDoItemUpdateDTO.getId());

            toDoItem.setTitle(toDoItemUpdateDTO.getTitle());
            toDoItem.setDescription(toDoItemUpdateDTO.getDescription());
            toDoItem.setFinishedAt(toDoItemUpdateDTO.getFinishedAt());

            toDoItemRepository.save(toDoItem);
        } else {
            throw new NotFoundException(String.format("Item was not found by ID: %s for user: %s",
                    toDoItemUpdateDTO.getId(), userId));
        }
    }

    @Override
    public void deleteToDoItem(Long id) {
        Long currentUserId = UserServiceImpl.getCurrentlyAuthenticatedUserId();

        if (toDoItemRepository.existsByIdAndUserId(id, currentUserId)) {
            toDoItemRepository.deleteById(id);
        } else {
            throw new NotFoundException(String.format("Item was not found by ID: %s for user: %s",
                    id, currentUserId));
        }
    }

    @Override
    @Transactional
    public void deleteAllToDos() {
        toDoItemRepository.deleteAllByUserId(UserServiceImpl.getCurrentlyAuthenticatedUserId());
    }
}
