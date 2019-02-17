package com.todo.controllers;

import com.todo.exceptions.FailedToRetrieveException;
import com.todo.models.TodoItem;
import com.todo.services.TodoItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController()
public class TodoItemsController {

    private Logger logger = LoggerFactory.getLogger(TodoItemsController.class);
    private final String RESOURCE_TYPE = "TodoItems";

    private TodoItemsService todoItemsService;

    @Autowired
    public TodoItemsController(TodoItemsService todoItemsService) {
        this.todoItemsService = todoItemsService;
    }

    @RequestMapping(value = "/todos", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Page<TodoItem> getPage(@RequestParam("page") int page, @RequestParam("size") int size) {
        try {
            return todoItemsService.getItems(page, size);
        } catch (Exception e) {
            logger.error("getPage: page {}, size {}", page, size);
            throw new FailedToRetrieveException(RESOURCE_TYPE);
        }
    }
}
