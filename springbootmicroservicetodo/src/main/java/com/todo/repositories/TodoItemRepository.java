package com.todo.repositories;

import com.todo.models.TodoItem;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends PagingAndSortingRepository<TodoItem, Long> {
}
