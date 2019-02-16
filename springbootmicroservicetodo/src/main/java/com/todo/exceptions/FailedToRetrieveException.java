package com.todo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FailedToRetrieveException  extends RuntimeException{
    public FailedToRetrieveException(String resourceType){
        super(String.format("Failed to retrieve: %s", resourceType));
    }
}
