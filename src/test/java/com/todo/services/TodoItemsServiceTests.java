package com.todo.services;

import com.todo.models.TodoItem;
import com.todo.repositories.TodoItemRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoItemsServiceTests {

    @Mock
    private TodoItemRepository todoItemRepository;

    @InjectMocks
    private TodoItemsService todoItemsService;

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

        when(todoItemRepository.findAll(any(PageRequest.class))).thenReturn(itemsPage);
    }

    @Test
    public void shouldGetPagedResultOfItems() {
        int page = 0;
        int size = 1;
        Page<TodoItem> resultsPage = todoItemsService.getItems(page, size);

        verify(todoItemRepository).findAll(eq(PageRequest.of(page, size)));
        Assert.assertEquals(itemsPage, resultsPage);
    }

    @Test
    public void shouldCreateItem() {
        String message = "New item";
        boolean completed = false;

        TodoItem item = new TodoItem(message, completed);
        when(todoItemRepository.save(any(TodoItem.class))).thenReturn(item);

        todoItemsService.createItem(item);

        verify(todoItemRepository).save(eq(item));
    }

    @Test
    public void markAsCompleted(){
        TodoItem incompleteItem = new TodoItem("Incomplete", false);
        List<TodoItem> items = new ArrayList<TodoItem>();
        items.add(incompleteItem);

        todoItemsService.markAsCompleted(items);

        TodoItem expectedItem = new TodoItem("Incomplete", true);
        List<TodoItem> expectedItems = new ArrayList<TodoItem>();
        expectedItems.add(expectedItem);
        verify(todoItemRepository).saveAll(eq(expectedItems));
    }
}
