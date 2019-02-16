package com.todo.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "todo-items")
public class TodoItem {
    private String message;
    private boolean completed;
}
