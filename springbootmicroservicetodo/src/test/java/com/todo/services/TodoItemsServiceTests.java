package com.todo.services;

import com.todo.models.TodoItem;
import com.todo.repositories.TodoItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoItemsServiceTests {

    private TodoItemsService todoItemsService;

    private TodoItemRepository todoItemRepository;

    private Page<TodoItem> itemsPage;

    private List<TodoItem> items;

    @Before
    public void setup() {
        items = new ArrayList<TodoItem>();
        TodoItem item = new TodoItem();
        item.setCompleted(false);
        item.setMessage("Some todo item");
        items.add(item);
        itemsPage = new PageImpl<TodoItem>(items);

        todoItemRepository = mock(TodoItemRepository.class);

        when(todoItemRepository.findAll(any(PageRequest.class))).thenReturn(itemsPage);

        todoItemsService = new TodoItemsService(todoItemRepository);
    }

    @Test
    public void shouldGetPagedResultOfItems() {
        int page = 0;
        int size = 1;
        Page<TodoItem> resultsPage = todoItemsService.getItems(page, size);
        verify(todoItemRepository).findAll(eq(PageRequest.of(page, size)));
        Assert.assertEquals(itemsPage, resultsPage);
    }
}
