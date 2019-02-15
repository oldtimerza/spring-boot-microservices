package com.todo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class TodoItem {
    @Id
    private Long id;
    private String message;
    private boolean completed;
}
