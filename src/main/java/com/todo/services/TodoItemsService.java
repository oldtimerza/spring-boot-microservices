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

@Transactional
@Service
public class TodoItemsService {

    private final Logger logger = LoggerFactory.getLogger(TodoItemsService.class);

    private TodoItemRepository todoItemRepository;

    @Autowired
    public TodoItemsService(final TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public Page<TodoItem> getItems(int page, int size) {
        logger.info("getItems: page {}, size {}", page, size);
        return todoItemRepository.findAll(PageRequest.of(page, size));
    }
}
