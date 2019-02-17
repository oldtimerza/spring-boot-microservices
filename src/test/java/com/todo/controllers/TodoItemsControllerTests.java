package com.todo.controllers;

import com.todo.models.TodoItem;
import com.todo.services.TodoItemsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class TodoItemsControllerTests {

    @Mock
    private TodoItemsService todoItemsService;

    @InjectMocks
    private TodoItemsController todoItemsController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(todoItemsController).build();
    }

    @Test
    public void shouldGetPage() throws Exception {
        String message = "A test todo item";
        boolean completed = false;
        List<TodoItem> todoItems = new ArrayList<TodoItem>();
        TodoItem item = new TodoItem();
        item.setCompleted(completed);
        item.setMessage(message);
        todoItems.add(item);
        Page<TodoItem> todoItemsPage = new PageImpl<TodoItem>(todoItems);
        when(todoItemsService.getItems(anyInt(), anyInt())).thenReturn(todoItemsPage);

        ResultActions result = mockMvc.perform(get("/todos")
                .param("offset", "0")
                .param("limit", "1")
                .accept(MediaType.APPLICATION_JSON));

        result.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.content.length()").value(todoItemsPage.getTotalElements()))
                .andExpect(jsonPath("$.content[0].message").value(message))
                .andExpect(jsonPath("$.content[0].completed").value(completed));
    }
}
