package com.todo.controllers;

import com.todo.exceptions.FailedToCreateException;
import com.todo.exceptions.FailedToRetrieveException;
import com.todo.exceptions.FailedToUpdateException;
import com.todo.models.TodoItem;
import com.todo.services.TodoItemsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Page<TodoItem> getPage(@RequestParam("offset") int offset, @RequestParam("limit") int limit) {
        try {
            return todoItemsService.getItems(offset, limit);
        } catch (Exception e) {
            logger.error("getPage: page {}, size {}", offset, limit);
            throw new FailedToRetrieveException(RESOURCE_TYPE);
        }
    }

    @RequestMapping(value = "/todos", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TodoItem createItem(@RequestBody TodoItem item){
        try{
           return todoItemsService.createItem(item);
        }catch (Exception e){
            logger.error("createItem: item {}", item);
            throw new FailedToCreateException(item);
        }
    }

    @RequestMapping(value = "/todos", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public List<TodoItem> updateItems(@RequestBody List<TodoItem> items){
        try{
            return todoItemsService.markAsCompleted(items);
        }catch(Exception e){
            logger.error("updateItems: items {}", items);
            throw new FailedToUpdateException(items);
        }
    }
}
