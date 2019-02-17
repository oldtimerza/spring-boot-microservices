package com.todo.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "todo-items")
public class TodoItem {
    public TodoItem(){
    }

    public TodoItem(String message, boolean completed){
       this.message = message;
       this.completed = completed;
    }
    @Id
    private String message;
    private boolean completed;
}
