package com.todo.config;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
public class MongoConfig {

    private final Logger logger = LoggerFactory.getLogger(MongoConfig.class);
    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "todo_db";

    @PostConstruct
    private void postConstruct() {
        logger.info("Mongo config setup");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        MongodForTestsFactory mongo = new MongodForTestsFactory();
        MongoClient mongoClient = mongo.newMongo();
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }
}
