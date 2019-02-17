package com.todo.exceptions;

import com.todo.models.TodoItem;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedToCreateException extends RuntimeException{
        public FailedToCreateException(TodoItem item){
            super(String.format("Failed to create: %s", item));
        }
}
