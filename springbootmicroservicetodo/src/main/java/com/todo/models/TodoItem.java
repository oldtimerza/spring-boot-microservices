package com.todo.models;

import lombok.Data;

@Data
public class TodoItem {
    private String message;
    private boolean completed;
}
