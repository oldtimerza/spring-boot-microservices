package com.todo.repositories;

import com.todo.models.TodoItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends MongoRepository<TodoItem, Long> {
}
