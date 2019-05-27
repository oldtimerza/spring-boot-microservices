package com.todo.exceptions;

import com.todo.models.TodoItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FailedToUpdateException extends RuntimeException {
    public FailedToUpdateException(List<TodoItem> items) {
        super(String.format("Failed to update: %s", items));
    }
}
