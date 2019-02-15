package com.todo.repositories.config;

import com.mongodb.MongoClient;
import de.flapdoodle.embed.mongo.tests.MongodForTestsFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Configuration
@EnableMongoRepositories
public class RepositoryTestConfig extends AbstractMongoConfiguration {

    private final Logger logger = LoggerFactory.getLogger(RepositoryTestConfig.class);
    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "test_db";

    @PostConstruct
    protected void postConstruct() {
        logger.info("Mongo Test config setup");
    }

    @Override
    public MongoClient mongoClient() {
        MongodForTestsFactory mongo = null;
        try {
            mongo = new MongodForTestsFactory();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        MongoClient mongoClient = null;
        try {
            mongoClient = mongo.newMongo();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return mongoClient;
    }

    @Bean
    protected MongoTemplate mongoTemplate(MongoClient mongoClient) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoClient, getDatabaseName());
        return mongoTemplate;
    }

    @Override
    protected String getDatabaseName() {
        return MONGO_DB_NAME;
    }
}
