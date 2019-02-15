package com.todo.repositories;

import com.todo.models.TodoItem;
import com.todo.repositories.config.RepositoryTestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class TodoItemRepositoryTests {

    @Autowired
    private TodoItemRepository todoItemRepository;

    @Test
    public void shouldGetAllTodoItemsPaged(){
        Page<TodoItem> items = todoItemRepository.findAll(PageRequest.of(0,10 ));
        Assert.assertEquals(10, items.getSize());
    }
}
