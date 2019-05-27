package com.todo.services;

import com.todo.models.TodoItem;
import com.todo.repositories.TodoItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TodoItemsService {

    private final Logger logger = LoggerFactory.getLogger(TodoItemsService.class);

    private TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemsService(final TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public Page<TodoItem> getItems(int offset, int limit) {
        logger.info("getItems: page {}, size {}", offset, limit);
        return todoItemRepository.findAll(PageRequest.of(offset, limit));
    }

    public TodoItem createItem(TodoItem item) {
        logger.info("createItem: item {}", item);
        return todoItemRepository.save(item);
    }

    public List<TodoItem> markAsCompleted(List<TodoItem> items) {
        logger.info("markAsCompleted: items {}", items);
        for (int i = 0; i < items.size(); i++) {
            TodoItem item = items.get(i);
            item.setCompleted(true);
        }
        return todoItemRepository.saveAll(items);
    }
}
