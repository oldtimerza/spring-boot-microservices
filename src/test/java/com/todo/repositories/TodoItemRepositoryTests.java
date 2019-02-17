package com.todo.repositories;

import com.todo.models.TodoItem;
import com.todo.repositories.config.RepositoryTestConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class TodoItemRepositoryTests {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TodoItemRepository todoItemRepository;

    private List<TodoItem> items;

    @Before
    public void Setup(){
        Query query = new Query();
        query.addCriteria(Criteria.where("message").exists(true));
        mongoTemplate.findAllAndRemove(query,TodoItem.class);
        mongoTemplate.remove(TodoItem.class);
        items = new ArrayList<TodoItem>();
        TodoItem testItem = new TodoItem();
        testItem.setCompleted(false);
        testItem.setMessage("This is a test todo item");
        items.add(testItem);
        for(TodoItem item: items){
            mongoTemplate.save(item);
        }
    }

    @Test
    public void shouldGetAllTodoItemsPaged(){
        Page<TodoItem> itemsPage = todoItemRepository.findAll(PageRequest.of(0,10 ));
        List<TodoItem> actualItems = itemsPage.getContent();
        Assert.assertEquals(items, actualItems);
    }

    @Test
    public void shouldCreateNewTodoItem(){
        TodoItem item = new TodoItem();
        item.setMessage("Create a new Item");
        item.setCompleted(true);
        todoItemRepository.save(item);

        List<TodoItem> items = mongoTemplate.findAll(TodoItem.class);
        Assert.assertEquals(items.size(), 2);
        TodoItem newItem = items.get(1);
        Assert.assertEquals(item, newItem);
    }
}
