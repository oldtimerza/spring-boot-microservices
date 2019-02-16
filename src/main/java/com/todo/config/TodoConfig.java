package com.todo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class TodoConfig {
    Logger logger = LoggerFactory.getLogger(TodoConfig.class);

    @PostConstruct
    private void postConstruct() {
        logger.info("Todo config setup");
    }

}
